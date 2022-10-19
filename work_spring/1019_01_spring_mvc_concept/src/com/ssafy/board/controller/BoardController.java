package com.ssafy.board.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.dto.Board;
import com.ssafy.board.dto.User;
import com.ssafy.board.etc.BoardException;
import com.ssafy.board.model.service.BoardService;
import com.ssafy.spring.reflection.SsafyRequestMapping;

public class BoardController {

	private BoardService service = new BoardService();
	
	@SsafyRequestMapping(value = "/board/regist_form")
	public String getRegistForm(HttpServletRequest req, HttpServletResponse resp) {
		return "redirect:" + req.getContextPath() + "/board/regist_form.jsp";
	}

	@SsafyRequestMapping(value = "/board/list")
	public String getList(HttpServletRequest request, HttpServletResponse response) {
		// 게시판 글 가져오기
		try {
			List<Board> list = service.list();

			request.setAttribute("list", list);

			return "/board/list.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SsafyRequestMapping(value = "/board/regist")
	public String postRegist(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String id = null;

		// 현재 로그인한 사용자의 ID를 가져와 작성자 ID로 사용한다.
		HttpSession session = request.getSession();
		Object obj = session.getAttribute("user");
		if (obj != null && obj instanceof User) {
			id = ((User) obj).getId();
		}

		// 글 번호, 글 작성날짜는 DB에서 생성되므로 의미없는 0으로 설정
		Board board = new Board(0, title, content, id, 0L);
		try {
			int cnt = service.newBoard(board);

			return "redirect:" + request.getContextPath() + "/servlet/board/list";
		} catch (SQLException | BoardException e) {
			e.printStackTrace();
			return "redirect:" + request.getContextPath() + "/error/error.jsp";
		}

	}

	@SsafyRequestMapping(value = "/board/detail")
	public String getDetail(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String no = req.getParameter("no");
			Board board = service.detail(no);

			req.setAttribute("board", board);

			return "/board/detail.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SsafyRequestMapping(value = "/board/remove")
	public String getRemove(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String no = req.getParameter("no");
			int cnt = service.remove(no);

			req.setAttribute("cnt", cnt);

			return "redirect:" + req.getContextPath() + "/servlet/board/list";
		} catch (SQLException | BoardException e) {
			e.printStackTrace();
		}

		return null;
	}
}
