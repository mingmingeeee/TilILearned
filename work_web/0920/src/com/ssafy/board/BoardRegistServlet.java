package com.ssafy.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/regist")
public class BoardRegistServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BoardService service = new BoardService();

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = request.getParameter("id");

		// 글 번호, 글 작성날짜는 DB에서 생성되므로 의미없는 0으로 설정
		Board board = new Board(0, title, content, id, 0L);
		try {
			int cnt = service.newBoard(board);
			
			// request 객체에 JSP로 보낼 데이터를 실어 준다.
			request.setAttribute("cnt", cnt);
			
			// ContextPath까지 이미 작성되어 있다고 생각하고 경로 작성
			// localhost:8080/0920/regist_result.jsp
			RequestDispatcher disp = request.getRequestDispatcher("/regist_result.jsp");
			disp.forward(request, response);
		}
		catch (SQLException | BoardException e) {
			e.printStackTrace();
		} 
		catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
