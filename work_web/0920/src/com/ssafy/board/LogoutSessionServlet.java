package com.ssafy.board;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutSessionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request,HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		
		// 현재 사용자의 session을 완전히 소멸시킨다.
		session.invalidate();
		
		// 로그 아웃 성공 시 JSP
	}
}
