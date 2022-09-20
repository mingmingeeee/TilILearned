package com.ssafy.board;

import com.ssafy.was.SsafyRequest;
import com.ssafy.was.SsafyResponse;
import com.ssafy.was.SsafySession;

public class LogoutSessionServlet {

	public void doGet(SsafyRequest request, SsafyResponse response) {
		
		SsafySession session = request.getSession();
		
		// 현재 사용자의 session을 완전히 소멸시킨다.
		session.invalidate();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>로그아웃 했습니다.</h1>");
		sb.append("<a href='/index.html'>홈으로 이동</a>");
		
		response.print(sb.toString());
	}
}
