package com.ssafy.board;

import com.ssafy.was.SsafyRequest;
import com.ssafy.was.SsafyResponse;
import com.ssafy.was.SsafySession;

public class LoginSessionServlet {

	public void doPost(SsafyRequest request, SsafyResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		if ("ssafy".equals(id) && "1234".equals(pass)) {
			
			// 로그인에 성공했을 경우 Session 객체에 사용자 아이디를 저장한다.
			SsafySession session = request.getSession();
			session.setAttribute("userId", id);
			
			StringBuilder sb = new StringBuilder();
			sb.append("<h1>로그인 했습니다.</h1>");
			sb.append("<a href='/index.html'>홈으로 이동</a>");
			
			response.print(sb.toString());
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>로그인 실패했습니다.</h1>");
		sb.append("<a href='/index.html'>홈으로 이동</a>");
		
		response.print(sb.toString());
	}

}
