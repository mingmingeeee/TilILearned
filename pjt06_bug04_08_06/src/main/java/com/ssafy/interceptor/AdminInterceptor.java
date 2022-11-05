package com.ssafy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.member.model.dto.MemberDto;

@Component
public class AdminInterceptor implements HandlerInterceptor  {
	
	private static Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("진입 경로: " + request.getServletPath());
		
		HttpSession session = request.getSession();
		
		MemberDto loginUser = (MemberDto) session.getAttribute("userinfo");
		if(loginUser.getUserId().equals("admin")) {
			return true;
		}
		else {
			response.sendRedirect(request.getContextPath() + "/");
			return false;
		}
	}

}
