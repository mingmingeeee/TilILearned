package com.ssafy.sample.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.sample.dto.Product;
import com.ssafy.sample.dto.User;
import com.ssafy.sample.model.service.ProductService;

// Controller는 Servlet과도 같다고 생각하기 ^^..

@WebServlet("/main") // WebServlet
public class MainController extends HttpServlet {

	private ProductService service = new ProductService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 클라이언트의 요청 데이터 받기
		String act = req.getParameter("act");

		switch (act) {

		case "index":
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			break;

		case "regist_form":
			resp.sendRedirect(req.getContextPath() + "/product/regist.jsp");
			break;

		// 2. 비지니스 로직 처리
		case "regist":
			// 1 -> 클라이언트의 모든 요청을 String으로 받음
			String code = req.getParameter("productCode");
			String model = req.getParameter("model");
			String price = req.getParameter("price");

			Product product = new Product(code, model, price);
			try {
				// 2 -> 비지니스 로직 처리 (service 호출)
				int cnt = service.regist(product);

				// 2-1. 서비스로부터 얻은 데이터를 JSP로 보내기 위해 req 객체에 데이터 담기
				req.setAttribute("cnt", cnt);

				// 3. 응답 페이지 작성 (forward)
				RequestDispatcher disp = req.getRequestDispatcher("/regist_result.jsp");
				disp.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}
			break;

		case "list":
			// 1 -> 클라이언트 모든 요청 받기 -> 근데 이건 요청이 없었음

			// 2. 비지니스 로직 처리
			try {
				List<Product> list = service.list();

				// 2-1. 서비스로부터 얻은 데이터를 JSP로 보내기 위해 req 객체에 데이터 담기
				req.setAttribute("list", list);
				System.out.println(list.toString());
				// 3. 응답 페이지 작성 (JSP 데이터 forward)
				RequestDispatcher disp = req.getRequestDispatcher("/list_result.jsp");
				disp.forward(req, resp);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}

			break;

		case "detail":
			// 1. 클라이언트 요청 데이터 받기
			String detailCode = req.getParameter("productCode");

			// 2. 비니지스 로직 처리
			try {
				Product detailProduct = service.detail(detailCode);

				// 2-1. 서비스로부터 얻은 데이터를 JSP로 보내기 위해 req 객체에 데이터 담기
				req.setAttribute("product", detailProduct);

				// 3. 응답 페이지 작성 (JSP 데이터 forward)
				RequestDispatcher disp = req.getRequestDispatcher("/detail_result.jsp");
				disp.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}

			break;

		case "remove":
			// 1. 클라이언트의 요청 데이터 받기
			String removeCode = req.getParameter("productCode");

			// 2. 비지니스 로직 처리
			try {
				int cnt = service.remove(removeCode);

				// 3. 응답 페이지 작성 (redirect - 목록 페이지로 이동)
				resp.sendRedirect(req.getContextPath() + "/main?act=list");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}

			break;

		case "login":
			// 1. 클라이언트의 요청 데이터 받기
			String id = req.getParameter("id");
			String pass = req.getParameter("pass");

			// 2. 비지니스 로직 처리

			User user = service.login(req, id, pass);

			// 2-1. 서비스로부터 얻은 데이터를 JSP로 보내기 위해 req 객체에 데이터 담기
			if (user != null) {
				// 로그인 성공 했을 때
				req.setAttribute("user", user);

			} else {
				// 로그인 실패 했을 때
				req.setAttribute("message", "아이디 또는 패스워드가 다릅니다.");
			}
			// 3. 응답 페이지 작성 (forward - 메인 페이지로 이동)
			RequestDispatcher disp = req.getRequestDispatcher("/index.jsp"); // dispatcher: 원래 context Path에서 시작
			disp.forward(req, resp);
			
			break;

		case "logout":
			boolean isLogout = service.logout(req);
			
			if(isLogout) {
				req.setAttribute("message", "로그아웃 되었습니다.");
			}
			else {
				req.setAttribute("message", "로그아웃 실패 했습니다.");
			}
			// 3. 응답 페이지 작성 (forward - 메인 페이지로 이동)
			RequestDispatcher logoutDisp = req.getRequestDispatcher("/index.jsp"); // dispatcher: 원래 context Path에서 시작
			logoutDisp.forward(req, resp);

			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 인코딩 하고
		doGet(req, resp); // doGet으로 넘기기
	}

}

//session id로 구별, cookie로 전달