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
	private HouseDataRestController houseDataController = new HouseDataRestController();
	
	// 객체를 JSON 문자열로 변경하거나 반대로 JSON 문자열을 객체로 변경해주는 객체
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("getPathInfo: " + req.getPathInfo()); // getPathInfo(): 요청이 들어온 URL에 추가되어 있는 부가적인 경로 정보를 리턴 -> "~servlet/"에 있는 ~~~
		
		// 로그인 확인
		SsafyInterceptor interceptor = new SsafyInterceptor();
		boolean isPass = interceptor.preHandle(req, resp);
		
		String path = null; // 경로
		
		Object obj = null; // REST 방식 응답을 위한 객체 
		
		// 경로가 null값이거나 로그인해야하는 페이지인데 로그인이 되어있지 않다면 첫 페이지로 index.jsp로 이동 !!!
		if(req.getPathInfo() == null || req.getPathInfo().equals("/") || isPass == false) { 
			path = "/index.jsp";
			RequestDispatcher disp = req.getRequestDispatcher(path);
			disp.forward(req, resp);
			return;
		}
		
		switch (req.getPathInfo()) {
		
		case "/board/regist_form": // 받은 경로가 "/board/regist_form"이라면 
			path = "redirect:" + req.getContextPath() + "/board/regist.jsp"; // path 설정 
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
			path= userController.getUserLogout(req, resp);
			
		case "/house/search_form":
			path = "redirect:" + req.getContextPath() + "/house/search_form.jsp";
			break;
			
		case "/rest/house/sido":
			obj = houseDataController.getSidoNames(req, resp);
			break;
			
		case "/rest/house/gugun":
			obj = houseDataController.getGuGunNames(req, resp);
			break;
			
		case "/rest/house/dong":
			obj = houseDataController.getDongNames(req, resp);
			break;
		
		}
		
		if (path != null && path.startsWith("redirect:")) { // path가 null값이 아니고 redirect로 시작할 때
			resp.sendRedirect(path.split(":")[1]); // [1]로 이동 
		} 
		else if (path != null) {
			RequestDispatcher disp = req.getRequestDispatcher(path);
			disp.forward(req, resp);
		}
		else if (obj != null) { // JSON 문자열로 응답 보내기 (데이터 보내기)
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
