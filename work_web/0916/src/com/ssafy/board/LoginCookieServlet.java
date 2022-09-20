package com.ssafy.board;

import com.ssafy.was.SsafyCookie;
import com.ssafy.was.SsafyRequest;
import com.ssafy.was.SsafyResponse;

public class LoginCookieServlet {

	public void doPost(SsafyRequest request, SsafyResponse response) {
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		if ("ssafy".equals(id) && "1234".equals(pass)) {
			SsafyCookie userId = new SsafyCookie("userId", id);
			
			// 해당 쿠키 소멸 시간 설정 (1일 후 소멸, 초 단위)
			userId.setMaxAge(60 * 60 * 24);
			
			// 해당 쿠키를 사용할 수 있는 도메인
			userId.setDomain("127.0.0.1");
			
			// 해당 쿠키를 사용할 수 있는 경로
			userId.setPath("/");
			
			// 응답에 쿠키 추가
			response.addCookie(userId);
			
			// 클라이언트로 보낼 응답 작성
			StringBuilder sb = new StringBuilder();
			sb.append("<h1>로그인 했습니다.</h1>");
			sb.append("<a href='/index.html'>홈으로 이동</a>");
			
			response.print(sb.toString());
			return;
		}
		
		// 로그인 실패했을 때 응답 작성
		StringBuilder sb = new StringBuilder();
		sb.append("<h1>로그인 실패했습니다.</h1>");
		sb.append("<a href='/index.html'>홈으로 이동</a>");
		
		response.print(sb.toString());
	}

}
