package com.ssafy.spring;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.board.controller.BoardController;
import com.ssafy.board.controller.HouseDataRestController;
import com.ssafy.board.controller.UserController;

@WebServlet(urlPatterns = {"/servlet/*"})
public class DispatcherServlet extends HttpServlet {
	
	private BoardController boardController = new BoardController();
	private UserController userController = new UserController();
	private HouseDataRestController houseDataRestController = new HouseDataRestController();
	
	// 객체를 JSON 문자열로 변경하거나 반대로 JSON 문자열을 객체로 변경해주는 객체
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("getPathInfo: " + req.getPathInfo());
		
		SsafyInterceptor interceptor = new SsafyInterceptor();
		boolean isPass = interceptor.preHandle(req, resp);
		
		String path = null;  // 경로
		Object obj = null;  // REST 방식 응답을 위한 객체
		
		if (req.getPathInfo() == null || req.getPathInfo().equals("/") || isPass == false) {
			path = "/index.jsp";
			resp.sendRedirect(req.getContextPath() + path);
			return;
		}
		
		switch (req.getPathInfo()) {
		case "/board/regist_form":
			path = "redirect:" + req.getContextPath() + "/board/regist.jsp";
			break;
			
		case "/board/regist":
			path = boardController.postBoardRegist(req, resp);
			break;
			
		case "/board/list":
			path = boardController.getBoardList(req, resp);
			break;
			
		case "/user/login":
			path = userController.postUserLogin(req, resp);
			break;
			
		case "/user/logout":
			path = userController.getUserLogout(req, resp);
			break;
			
		case "/house/search_form":
			path = "redirect:" + req.getContextPath() + "/house/search_form.jsp";
			break;
			
		case "/rest/house/sido":
			obj = houseDataRestController.getSidoNames(req, resp);
			break;
			
		case "/rest/house/gugun":
			obj = houseDataRestController.getGuGunNames(req, resp);
			break;
			
		case "/rest/house/row-house/trade":  // 연립 다세대 매매 API 요청 처리
			obj = houseDataRestController.getRowHouseTrade(req, resp);
			break;
		}
		
		if (path != null && path.startsWith("redirect:")) {
			resp.sendRedirect(path.split(":")[1]);
		}
		else if (path != null) {
			RequestDispatcher disp = req.getRequestDispatcher(path);
			disp.forward(req, resp);
		}
		else if (obj != null) {  // JSON 문자열로 응답 보내기
			// 응답 헤더 작성 (Header)
			resp.addHeader("Content-Type", "application/json; charset=UTF-8");
			
			// 응답 Payload 작성
			// 1. 객체를 JSON 문자열로 직렬화
			String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
			
			// 2. 직렬화 한 문자열을 응답
			PrintWriter writer = resp.getWriter();
			writer.write(json);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}

}
