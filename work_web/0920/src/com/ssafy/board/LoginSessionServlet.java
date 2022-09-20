package com.ssafy.board;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginSessionServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		if ("ssafy".equals(id) && "1234".equals(pass)) {
			
			// 로그인에 성공했을 경우 Session 객체에 사용자 아이디를 저장한다.
			HttpSession session = request.getSession();
			session.setAttribute("userId", id);
			
			// 로그인 성공시 JSP
			
			return;
		}
		
		// 로그인 실패시 JSP
		
	}

}
