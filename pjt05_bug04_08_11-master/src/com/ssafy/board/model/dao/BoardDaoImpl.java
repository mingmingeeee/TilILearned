package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
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
			sql.append("insert into board (user_id, subject, content, hit, register_time) \n");
			sql.append("values (?, ?, ?, 0, now())");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getUserId());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getContent());
			cnt = pstmt.executeUpdate();
		} finally {
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

			if (!word.isEmpty()) {
				if (!"subject".equals(key))
					sql.append("where user_id = ? \n");
			}

			sql.append("order by article_no desc limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;

			if (!word.isEmpty()) {
				if (!"subject".equals(key))
					pstmt.setString(++idx, word);
			}

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

				if (word.isEmpty()) {
					list.add(boardDto);
				} else if (!word.isEmpty() && "subject".equals(key)) {
					
					/** KMP 알고리즘 제목 검색기능 */
					char[] text = boardDto.getSubject().toCharArray();
					char[] pattern = word.toCharArray();
					int tLength = text.length, pLength = pattern.length;
					int[] pi = new int[pLength];
					for (int i = 1, j = 0; i < pLength; i++) {
						// i:접미사 포인터(i=1부터 시작: 우리는 부분일치테이블를 만드는게 목적이므로 첫글자 틀리면
						// 0위치로 가야하므로.), j:접두사 포인터
						while (j > 0 && pattern[i] != pattern[j])
							j = pi[j - 1];

						if (pattern[i] == pattern[j]) pi[i] = ++j;
						else pi[i] = 0;
					}

					int cnt = 0;
					ArrayList<Integer> textlist = new ArrayList<Integer>();
					// i : 텍스트 포인터 , j: 패턴 포인터
					for (int i = 0, j = 0; i < tLength; ++i) {
						while (j > 0 && text[i] != pattern[j]) j = pi[j - 1];

						if (text[i] == pattern[j]) { // 두 글자 일치
							if (j == pLength - 1) { // j가 패턴의 마지막 인덱스라면
								cnt++; // 카운트 증가
								textlist.add(i - j);
								j = pi[j];
							} else {
								j++;
							}
						}
					}

					if (cnt > 0) {
						list.add(boardDto);
					}
				}
			}
			/** sort에 따른 정렬 */
			String sort = map.get("sort");
			if (sort.equals("form-sort-view")) { // 조회순으로 정렬
				List<BoardDto> newlist = new ArrayList<>(list.size());
				mergeSort(0, list.size() - 1, list, newlist);
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	private void mergeSort(int start, int end, List<BoardDto> list, List<BoardDto> newlist) {
		if (start < end) {

			int mid = (start + end) / 2;

			mergeSort(start, mid, list, newlist);

			mergeSort(mid + 1, end, list, newlist);

			int p = start;
			int q = mid + 1;

			int idx = p;
			while (p <= mid || q <= end) {
				if (q > end || (p <= mid && list.get(p).getHit() > list.get(q).getHit())) {
					newlist.add(idx++, list.get(p++));
				} else {
					newlist.add(idx++, list.get(q++));
				}
			}

			for (int i = start; i <= end; i++) {
				list.set(i, newlist.get(i));
			}
		}
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
			if (rs.next()) {
				boardDto = new BoardDto();
				boardDto.setArticleNo(rs.getInt("article_no"));
				boardDto.setUserId(rs.getString("user_id"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setContent(rs.getString("content"));
				boardDto.setHit(rs.getInt("hit"));
				boardDto.setRegisterTime(rs.getString("register_time"));
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return boardDto;
	}

	@Override
	public void updateHit(int articleNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set hit = hit + 1 \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void modifyArticle(BoardDto boardDto) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set subject = ?, content = ? \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setInt(3, boardDto.getArticleNo());
			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public void deleteArticle(int articleNo) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from board \n");
			sql.append("where article_no = ?");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, articleNo);

			pstmt.executeUpdate();
		} finally {
			dbUtil.close(pstmt, conn);
		}
	}

	@Override
	public int getBoardListCnt(Map<String, String> map) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dbUtil.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(article_no) cnt \n");
			sql.append("from board \n");
			String key = map.get("key");
			String word = map.get("word");
			if (!word.isEmpty()) {
				if ("subject".equals(key))
					sql.append("where subject like ? \n");
				else
					sql.append("where user_id = ? \n");
			}
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			if (!word.isEmpty()) {
				if ("subject".equals(key))
					pstmt.setString(++idx, "%" + word + "%");
				else
					pstmt.setString(++idx, word);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {

				return rs.getInt("cnt");
			}
		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
		return 0;
	}

}
