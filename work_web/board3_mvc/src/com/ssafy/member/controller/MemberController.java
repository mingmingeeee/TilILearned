package com.ssafy.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.member.model.service.MemberService;
import com.ssafy.member.model.service.MemberServiceImpl;

@WebServlet("/user")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService;
	
	public void init() {
		memberService = MemberServiceImpl.getMemberService();
	}
       
	// 사용자 요구 받아 어디로 가라 ~ -> Front Controller 패턴 -> 어느 컨트롤러로 보낼지? 이런 거 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		
		String path = "/index.jsp";
		if("mvjoin".equals(act)) { // act=mvjoin이라면 회원가입 페이지로 이동시켜줘~
			path = "/user/join.jsp";
			// 단순 이동: redirect -> 다~pool경로 
			// 뭔가를 가지고 갈 때는?: forward -> 내 프로젝트 안에서만
			redirect(request, response, path);
		} else if("idcheck".equals(act)) {
			int cnt = idCheck(request, response); // ajax -> 데이터만 넘겨주면 됨 (id check만 하는 거) -> 몇갠지만 넘겨주면 됨
			response.setContentType("text/plain);charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(cnt);
		} else if("".equals(act)) {
			
		} else if("".equals(act)) {
			
		} else if("".equals(act)) {
			
		} else { // 우리가 정상적인 값을 가져왔을 때는 원하는 곳으로 ㄱ ㄱ
			// 사용자가 장난치거나 잘못된 값이면 path(index page)로 이동
			redirect(request, response, path);
		}
	}
	
	private void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
		response.sendRedirect(request.getContextPath() + path);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	// 로직 호출
	private int idCheck(HttpServletRequest request, HttpServletResponse response) {
		String userId = request.getParameter("userid");
		try {
			int count = memberService.idCheck(userId);
			return count;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 500;
		// 0, 1: 사용 ㅇㅇ
		// 500 or -1: 에러 
	}


}
