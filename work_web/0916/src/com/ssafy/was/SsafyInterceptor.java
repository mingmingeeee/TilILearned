package com.ssafy.was;

public class SsafyInterceptor {

	public SsafyInterceptor(SsafyRequest request, SsafyResponse response) {
		// 모든 서블릿이 동작하기 전에 현재 로그인한 사용자의 아이디를 확인하기
		SsafySession session = request.getSession();
		
		Object obj = session.getAttribute("userId");
		if (obj != null && obj instanceof String) {
			String userId = (String) obj;
			System.out.println("현재 로그인한 사용자 아이디: " + userId);
		}
	}
}
