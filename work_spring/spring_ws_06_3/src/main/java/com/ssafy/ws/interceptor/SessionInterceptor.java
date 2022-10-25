package com.ssafy.ws.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		// 세션에 로그인 정보가 있다면 그대로 진행한다.
		if (session.getAttribute("loginUser") != null) {
			return true;  // 컨트롤러에게 요청 정보(객체)를 그대로 전달한다.
		}
		else {
			// 로그인 정보가 세션에 없다면 index 페이지로 리다이렉트 한다.
			response.sendRedirect(request.getContextPath() + "/index");
			return false;  // 컨트롤러에게 요청 정보(객체)를 전달하지 않고 끝낸다.
		}
	}

}
