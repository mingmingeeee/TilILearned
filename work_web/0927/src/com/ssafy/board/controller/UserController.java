package com.ssafy.board.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.dto.User;
import com.ssafy.board.model.service.UserService;

// POJO
public class UserController {

	private UserService service = new UserService();

	public String postUserLogin(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String id = req.getParameter("id");
			String pass = req.getParameter("pass");
			boolean isActive = true;  // 사용자 활성화

			User loginUser = new User(id, pass, isActive);

			User user = service.login(req, resp, loginUser);

			// 로그인 실패 시, 전달할 메시지 작성
			if (user == null) {
				req.setAttribute("message", "아이디 또는 패스워드가 다릅니다.");
			}

			return "/";
		}
		catch (SQLException e) {
			e.printStackTrace();
			return "redirect:" + req.getContextPath() + "/error/error.jsp";
		}
	}

	public String getUserLogout(HttpServletRequest req, HttpServletResponse resp) {

		HttpSession session = req.getSession();

		// 현재 사용자의 session을 완전히 소멸시킨다.
		session.invalidate();

		return "redirect:" + req.getContextPath() + "/";
	}
}
