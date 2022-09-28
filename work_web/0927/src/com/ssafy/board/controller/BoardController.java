package com.ssafy.board.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.dto.Board;
import com.ssafy.board.dto.User;
import com.ssafy.board.etc.BoardException;
import com.ssafy.board.model.service.BoardService;

// POJO (Plain Old Java Object) : 순수 자바 클래스
public class BoardController {
	
	private BoardService service = new BoardService();
	
	public String getBoardList(HttpServletRequest req, HttpServletResponse resp) {
		// 게시판 글 가져오기
		try {
			List<Board> list = service.list();
			
			req.setAttribute("list", list);
			
			return "/board/list_result.jsp";
		}
		catch (SQLException e) {
			e.printStackTrace();
			return "redirect:" + req.getContextPath() + "/error/error.jsp";
		}
	}
	
	public String postBoardRegist(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String id = null;
		
		// 현재 로그인한 사용자의 ID를 가져와 작성자 ID로 사용한다.
		HttpSession session = req.getSession();
		Object obj = session.getAttribute("user");
		if (obj != null && obj instanceof User) {
			id = ((User) obj).getId();
		}

		// 글 번호, 글 작성날짜는 DB에서 생성되므로 의미없는 0으로 설정
		Board board = new Board(0, title, content, id, 0L);
		try {
			int cnt = service.newBoard(board);
			
			// request 객체에 JSP로 보낼 데이터를 실어준다.
			//req.setAttribute("cnt", cnt);			
			
			return "redirect:" + req.getContextPath() + "/servlet/board/list";
		}
		catch (SQLException | BoardException e) {
			e.printStackTrace();
			return "redirect:" + req.getContextPath() + "/error/error.jsp";
		}
	}
}
