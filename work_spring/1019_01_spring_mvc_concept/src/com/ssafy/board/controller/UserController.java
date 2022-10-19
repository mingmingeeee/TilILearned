package com.ssafy.board.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.dto.User;
import com.ssafy.board.model.service.UserService;
import com.ssafy.spring.reflection.SsafyRequestMapping;

public class UserController {

	private UserService service = new UserService();

	@SsafyRequestMapping(value="/user/login_form")
	public String getLoginForm(HttpServletRequest req, HttpServletResponse resp) {
		return "redirect:" + req.getContextPath() + "/user/login.jsp";
	}

	@SsafyRequestMapping(value = "/user/login")
	public String postLogin(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			String pass = request.getParameter("pw");
			boolean isActive = true;

			User loginUser = new User(id, pass, isActive);

			// 2. 비지니스 로직 처리
			User user = service.login(request, response, loginUser);

			// 2-1. 서비스로부터 얻은 데이터를 JSP로 보내기 위해 req 객체에 데이터 담기
			if (user == null) {
				request.setAttribute("message", "아이디 또는 패스워드가 다릅니다.");
			}

			return "/";
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SsafyRequestMapping(value = "/user/logout")
	public String getLogout(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		// 현재 사용자의 session을 완전히 소멸시킨다.
		session.invalidate();

		// 로그 아웃 성공 시 JSP
		return "/";
	}

}
