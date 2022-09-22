package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.member.model.MemberDto;
import com.ssafy.util.DBUtil;

public class BoardDaoImpl implements BoardDao {

	private static BoardDao boardDao = new BoardDaoImpl();
	private DBUtil dbUtil;

	private BoardDaoImpl() {
		dbUtil = DBUtil.getInstance();
	}

	public static BoardDao getBoardDao() {
		return boardDao;
	}

	@Override
	public int writeArticle(BoardDto boardDto) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into board (user_id, subject, content, hit, register_time) \n"); // -> count!!!!가 있으므로
																								// 단독으로 rs.next()해도 됨
			sql.append("value (?, ?, ?, 0, now())"); // article은 자동 증가 해놓음
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getUserId());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());

			cnt = pstmt.executeUpdate();
		} finally { // 예외 잡지는 말고 연결은 닫아줘라~
			dbUtil.close(pstmt, conn);
		}

		return cnt;
	}

	@Override
	public List<BoardDto> listArticle(Map<String, String> map) throws SQLException {
		List<BoardDto> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select article_no, user_id, subject, content, hit, register_time \n");
			sql.append("from board \n");
			String key = map.get("key");
			String word = map.get("word");
			if (!word.isEmpty()) { // 검색
				// 제목일 때는 like 검색
				if ("subject".equals(key))
					sql.append("where subject like ? \n");
				// 작성자 id일 때는 equal 검색
				else
					sql.append("where user_id = ? \n");
				// sql.append("where " + key + " = ? \n");
			}
			sql.append("order by article_no desc limit ?, ?"); // 시작점, 개수
			pstmt = conn.prepareStatement(sql.toString());

			int idx = 0;
			if (!word.isEmpty()) { // 조건에 따라 컬럼 값 달라짐 -> !word.isEmpty()일 때 like ? id ?
				if ("subject".equals(key))
					pstmt.setString(++idx, "%" + word + "%"); // subject는 그 단어가 포함 되었는가 검사 -> like
				else
					pstmt.setString(++idx, word); // id는 완전히 같은 값 (equal)
			}

			// limit ?, ? -> 페이시 시작 번호, 개수 (pgno-1)*20
			pstmt.setInt(++idx, Integer.parseInt(map.get("start")));
			pstmt.setInt(++idx, Integer.parseInt(map.get("spl")));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardDto boardDto = new BoardDto();

				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setUserId(rs.getString("user_id"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setRegisterTime(rs.getString("register_time"));

				list.add(boardDto);
			}

		} finally { // 예외 잡지는 말고 연결은 닫아줘라~
			dbUtil.close(rs, pstmt, conn);
		}

		return list;
	}

	@Override
	public int totalArticleCount(Map<String, String> map) throws SQLException {
		return 0;
	}

	@Override
	public BoardDto getArticle(int articleNo) throws SQLException {
		BoardDto boardDto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("select article_no, user_id, subject, content, hit, register_time \n");
			sql.append("from board \n");
			sql.append("where article_no = ?");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, articleNo);

			rs = pstmt.executeQuery();

			if (rs.next()) { // 얻어왔다면
				boardDto = new BoardDto();

				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setUserId(rs.getString("user_id"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setRegisterTime(rs.getString("register_time"));
			}

		} finally { // 예외 잡지는 말고 연결은 닫아줘라~
			dbUtil.close(rs, pstmt, conn);
		}
		return boardDto;
	}

	@Override
	public void updateHit(int articleNo) throws SQLException {
		int cnt = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set hit = hit + 1 \n");
			sql.append("where article_no = ? \n");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, articleNo);

			cnt = pstmt.executeUpdate();
		} finally { // 예외 잡지는 말고 연결은 닫아줘라~
			dbUtil.close(pstmt, conn);
		}

	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException {
		int cnt = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set subject = ?, content = ? \n");
			sql.append("where article_no = ? \n");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setInt(3, boardDto.getArticleNo());

			cnt = pstmt.executeUpdate();
		} finally { // 예외 잡지는 말고 연결은 닫아줘라~
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from board \n");
			sql.append("where article_no = ? \n");
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setInt(1, articleNo);

			cnt = pstmt.executeUpdate();
		} finally { // 예외 잡지는 말고 연결은 닫아줘라~
			dbUtil.close(pstmt, conn);
		}

	}

}
