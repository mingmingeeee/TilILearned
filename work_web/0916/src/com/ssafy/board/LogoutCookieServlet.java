package com.ssafy.board;

import com.ssafy.was.SsafyCookie;
import com.ssafy.was.SsafyRequest;
import com.ssafy.was.SsafyResponse;

public class LogoutCookieServlet {

	public void doGet(SsafyRequest request, SsafyResponse response) {
		
		SsafyCookie[] cookies = request.getCookies();
		SsafyCookie userId = null;
		for (SsafyCookie cookie : cookies) {
			if (cookie.getName().equals("userId")) {
				userId = cookie;
				break;
			}
		}
		
		// 만약 userId 값이 없다면 로그아웃 실패
		if (userId == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("<h1>로그아웃 실패</h1>");
			sb.append("<a href='/index.html'>홈으로 이동</a>");
			
			response.print(sb.toString());
			return;
		}
		
		// 해당 쿠키 소멸 시간 설정 (로그아웃을 위해 0초 설정)
		userId.setMaxAge(0);
		
		// 해당 쿠키를 사용할 수 있는 도메인
		userId.setDomain("127.0.0.1");
		
		// 해당 쿠키를 사용할 수 있는 경로
		userId.setPath("/");
		
		// 응답에 쿠키 추가
		response.addCookie(userId);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>로그아웃 했습니다.</h1>");
		sb.append("<a href='/index.html'>홈으로 이동</a>");
		
		response.print(sb.toString());
	}

}
