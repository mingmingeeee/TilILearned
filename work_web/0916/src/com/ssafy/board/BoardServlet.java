package com.ssafy.board;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.was.SsafyRequest;
import com.ssafy.was.SsafyResponse;

public class BoardServlet {
	
	private BoardService service = new BoardService();
	
	public void doGet(SsafyRequest request, SsafyResponse response) {
		// 게시판 글 가져오기
		try {
			List<Board> list = service.list();
			response.print(list.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
