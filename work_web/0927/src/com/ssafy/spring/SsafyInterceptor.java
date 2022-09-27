package com.ssafy.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SsafyInterceptor {
	
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp) {
	
		// 로그인을 해야하는 페이지: /board/regist_form
		// 여기 가기 전 검사
		if ("/board/regist_form".equals(req.getPathInfo())) {
			// 여기를 들어가려면...
			
			// 로그인 된 상태 확인
			HttpSession session = req.getSession();
			Object obj = session.getAttribute("user");
			
			// 로그인 된 경우는 통과
			if(obj != null) {
				return true;
			} 
			// 로그인 안 된 경우는 통과 못 함
			else {
				return false;
			}
			
		}
		
		// 나머지 페이지는 무조건 통과
		return true;
		
	}	

}
