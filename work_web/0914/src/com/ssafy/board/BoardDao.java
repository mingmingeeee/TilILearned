package com.ssafy.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Data Access Object
// DAO: DBMS에 접근해서 Data 가져오는 역할 함 -> Exception 던짐
// Service: Data 가져오면 Controller에게 전달 -> Exception 던짐 
// Controller: 출력 ^&^ -> Exception을 받음 (화면이 표현되는 영역까지 온 거임) -> Exception받으면 화면에 출력하도록 짠 것 (에러 알려줘야 하니까)

public class BoardDao {

	public List<Board> selectAll(Connection conn) throws SQLException {
		// 실행할 쿼리문 작성
		String sql = "SELECT no, title, content, id, created_at FROM board ORDER BY no DESC";

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 4-1. 쿼리문 실행
		ResultSet result = stmt.executeQuery();

		// 4-2. 실행 결과 리턴
		List<Board> list = new ArrayList<>();
		while (result.next()) {
			// JDBC 이용 -> 게시판 글 목록 가져와서 return list;
			list.add(new Board(result.getInt("no"), // database column명 써도 됨
					result.getString("title"), result.getString("content"), result.getString("id"),
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
			// JDBC 이용 -> 게시판 글 목록 가져와서 return list;
			board = new Board(result.getInt("no"), // database column명 써도 됨
					result.getString("title"), result.getString("content"), result.getString("id"),
					result.getTimestamp("created_at").getTime());
		}
		return board;
	}

	public int insert(Connection conn, Board board) throws SQLException, BoardException {
		// 실행할 쿼리문 작성
		String sql = "INSERT INTO board (title, content, id, created_at) VALUES (?, ?, ?, NOW())";

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 3-2. 물음표 부분을 채워 넣기
		stmt.setString(1, board.getTitle());
		stmt.setString(2, board.getContent());
		stmt.setString(3, board.getId());

		// 4-1. 쿼리문 실행
		int cnt = stmt.executeUpdate(); // -> query 실행하고 난 후 행의 개수 -> 1개 ^^
		// select만 executeQuery고 다른 건 executeUpdate!! -> 결과 값이 없으므로 실행결과 return X

		if (cnt == 0) { // 정상적으로 입력이 안 됐을 때
			throw new BoardException("INSERT 실행 실패");
		}

		return cnt;
	}

	public int update(Connection conn, Board board) throws SQLException, BoardException {
		// 실행할 쿼리문 작성
		String sql = "Update board SET title = ?, content = ?, created_at = NOW() WHERE no = ?";

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 3-2. 물음표 부분을 채워 넣기
		stmt.setString(1, board.getTitle());
		stmt.setString(2, board.getContent());
		stmt.setInt(3, board.getNo());

		// 4-1. 쿼리문 실행
		int cnt = stmt.executeUpdate(); // -> query 실행하고 난 후 행의 개수 -> 1개 ^^
		// select만 executeQuery고 다른 건 executeUpdate!! -> 결과 값이 없으므로 실행결과 return X

		if (cnt == 0) { // 정상적으로 입력이 안 됐을 때
			throw new BoardException("UPDATE 실행 실패");
		}

		return cnt;
	}

	public int delete(Connection conn, String no) throws SQLException, BoardException {
		// 실행할 쿼리문 작성
		String sql = "DELETE FROM board WHERE no = ?";

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 3-2. 물음표 부분을 채워 넣기
		stmt.setString(1, no);
		
		// 4-1. 쿼리문 실행
		int cnt = stmt.executeUpdate(); // -> query 실행하고 난 후 행의 개수 -> 1개 ^^
		// select만 executeQuery고 다른 건 executeUpdate!! -> 결과 값이 없으므로 실행결과 return X

		if (cnt == 0) { // 정상적으로 입력이 안 됐을 때
			throw new BoardException("DELETE 실행 실패");
		}

		return cnt;
	}

}
