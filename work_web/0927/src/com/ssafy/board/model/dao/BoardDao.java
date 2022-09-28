package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.dto.Board;
import com.ssafy.board.etc.BoardException;

public class BoardDao {

	public List<Board> selectAll(Connection conn) throws SQLException {
		// 실행할 쿼리문 작성
		String sql = "SELECT no, title, content, user_id, created_at FROM board ORDER BY no DESC";

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 4-1. 쿼리문 실행
		ResultSet result = stmt.executeQuery();

		// 4-2. 실행 결과 리턴
		List<Board> list = new ArrayList<>();
		while (result.next()) {
			list.add(new Board(result.getInt("no"),
					result.getString("title"),
					result.getString("content"),
					result.getString("user_id"),
					result.getTimestamp("created_at").getTime()));
		}

		return list;
	}

	public Board select(Connection conn, String no) throws SQLException {
		// 실행할 쿼리문 작성
		String sql = "SELECT no, title, content, id, created_at FROM board WHERE no = ?";

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 3-2. 물음표 부분을 채워 넣기
		stmt.setString(1, no);

		// 4-1. 쿼리문 실행
		ResultSet result = stmt.executeQuery();

		// 4-2. 실행 결과 리턴
		Board board = null;
		if (result.next()) {
			board = new Board(result.getInt("no"),
					result.getString("title"),
					result.getString("content"),
					result.getString("id"),
					result.getTimestamp("created_at").getTime());
		}

		return board;
	}

	public int insert(Connection conn, Board board) throws SQLException, BoardException {

		// 실행할 쿼리문 작성
		String sql = "INSERT INTO board (title, content, user_id, created_at) VALUES (?, ?, ?, NOW())";

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 3-2. 물음표 부분을 채워 넣기
		stmt.setString(1, board.getTitle());
		stmt.setString(2, board.getContent());
		stmt.setString(3, board.getUserId());

		// 4-1. 쿼리문 실행
		int cnt = stmt.executeUpdate();
		if (cnt == 0) {
			throw new BoardException("INSERT 실행 실패");
		}

		return cnt;
	}

	public int update(Connection conn, Board board) throws SQLException, BoardException {
		// 실행할 쿼리문 작성
		String sql = "UPDATE board SET title = ?, content = ?, created_at = NOW() WHERE no = ?";

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 3-2. 물음표 부분을 채워 넣기
		stmt.setString(1, board.getTitle());
		stmt.setString(2, board.getContent());
		stmt.setInt(3, board.getNo());

		// 4-1. 쿼리문 실행
		int cnt = stmt.executeUpdate();
		if (cnt == 0) {
			throw new BoardException("UPDATE 실행 실패");
		}

		return cnt;
	}

	public int delete(Connection conn, String no) throws BoardException, SQLException {
		// 실행할 쿼리문 작성
		String sql = "DELETE FROM board WHERE no = ?";

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 3-2. 물음표 부분을 채워 넣기
		stmt.setString(1, no);

		// 4-1. 쿼리문 실행
		int cnt = stmt.executeUpdate();
		if (cnt == 0) {
			throw new BoardException("DELETE 실행 실패");
		}

		return cnt;
	}

}
