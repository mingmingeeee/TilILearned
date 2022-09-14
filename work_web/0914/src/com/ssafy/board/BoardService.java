package com.ssafy.board;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.board.util.DBUtil;

public class BoardService {

	private BoardDao dao = new BoardDao();

	// 글 목록 가져와야 됨 -> Board 객체 여러개 -> List
	public List<Board> list() throws SQLException {

		Connection conn = null;
		List<Board> list = null;

		try {
			conn = DBUtil.getConnection();
			list = dao.selectAll(conn);
		} finally {
			DBUtil.close(conn);
		}
		return list;

	}

	// 글 하나만 가져오면 됨 -> Board 객체 하나
	public Board detail(String no) throws SQLException {

		Connection conn = null;
		Board board = null;

		try {
			conn = DBUtil.getConnection();
			board = dao.select(conn, no);
		} finally {
			DBUtil.close(conn);
		}
		return board;

	}

	public int newBoard(Board newBoard) throws SQLException, BoardException {
		
		Connection conn = null;
		int cnt = 0;
		
		try {
			// Start Transaction
			conn = DBUtil.getConnection();
			cnt = dao.insert(conn, newBoard); // 작업하다가 에러 발생 -> Rollback
			
			// Commit 반영 
			conn.commit();
		}
		catch(SQLException | BoardException e) {
			// 여기서만 conn 객체에 접근 가능하므로 롤백 처리 후
			DBUtil.rollback(conn);
			throw e; // UI에서 오류 메세지 출력하기 위해 예외 처리 위임
		}
		finally {
			DBUtil.close(conn);
		}
		
		return cnt;
		
	}

	public int modify(Board board) throws SQLException, BoardException {
		
		Connection conn = null;
		int cnt = 0;
		
		try {
			// Start Transaction
			conn = DBUtil.getConnection();
			cnt = dao.update(conn, board); // 작업하다가 에러 발생 -> Rollback
			
			// Commit 반영 
			conn.commit();
		}
		catch(SQLException | BoardException e) {
			// 여기서만 conn 객체에 접근 가능하므로 롤백 처리 후
			DBUtil.rollback(conn);
			throw e; // UI에서 오류 메세지 출력하기 위해 예외 처리 위임
		}
		finally {
			DBUtil.close(conn);
		}
		
		return cnt;
		
	}

	public int remove(String no) throws SQLException, BoardException {

		Connection conn = null;
		int cnt = 0;
		
		try {
			// Start Transaction
			conn = DBUtil.getConnection();
			// 글 번호로 delete, 수정 이유: primary key이기 때문 (유일하게 글을 식별 가능한 no)
			cnt = dao.delete(conn, no); // 작업하다가 에러 발생 -> Rollback
			
			// Commit 반영 
			conn.commit();
		}
		catch(SQLException | BoardException e) {
			// 여기서만 conn 객체에 접근 가능하므로 롤백 처리 후
			DBUtil.rollback(conn);
			throw e; // UI에서 오류 메세지 출력하기 위해 예외 처리 위임
		}
		finally {
			DBUtil.close(conn);
		}
		
		return cnt;
		
	}

	/**
	 * 시나리오
	 * 트랙잭션: 2번 글을 삭제하고, 2번 글을 수정하는 하나의 작업단위
	 * 2번 글 삭제 후 2번 글을 수정하려고 시도하면 실패한다.
	 * 문제가 발생할 경우 롤백을 하게 되는데, 하나의 작업 단위를 취소할 경우, 2번 글 삭제 작업까지 취소되어야 한다.
	 * 따라서, 2번 글이 그대로 존재하면 롤백이 제대로 이루어진 것임
	 * @throws Exception 
	 * */
	public void testRollback() throws SQLException, BoardException {
		Connection conn = null;
		
		try {
			int cnt = 0;
			conn = DBUtil.getConnection();
			cnt = dao.delete(conn, "2");
			System.out.println(cnt + "개의 글이 삭제되었습니다.");
			cnt = dao.update(conn, new Board(2, "수정 제목", "수정 내용입니다.", null, 0L));
			System.out.println(cnt + "개의 글이 수정되었습니다.");
			conn.commit();
		}
		catch(SQLException | BoardException e) {
			DBUtil.rollback(conn);
			throw e; // UI에서 오류 메세지 출력하기 위해 예외 처리 위임
		}
		finally {
			DBUtil.close(conn);
		}
	}

}
