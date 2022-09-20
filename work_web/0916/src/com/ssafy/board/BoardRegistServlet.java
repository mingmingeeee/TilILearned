package com.ssafy.board;

import java.sql.SQLException;

import com.ssafy.was.SsafyRequest;
import com.ssafy.was.SsafyResponse;

public class BoardRegistServlet {

	private BoardService service = new BoardService();

	public void doPost(SsafyRequest request, SsafyResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");

		// 글 번호, 글 작성날짜는 DB에서 생성되므로 의미없는 0으로 설정
		Board board = new Board(0, title, content, id, 0L);
		try {
			int cnt = service.newBoard(board);
			response.print(String.valueOf(cnt));
		}
		catch (SQLException | BoardException e) {
			e.printStackTrace();
		}
	}
}
