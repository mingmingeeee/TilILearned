package com.ssafy.sample.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.sample.dto.Product;
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
		// 2. 비지니스 로직 처리
		case "regist":
			String code = req.getParameter("productCode");
			String model = req.getParameter("model");
			String price = req.getParameter("price");
			
			Product product = new Product(code, model, price);
			int cnt = service.regist(product);
			break;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF -8"); // 인코딩 하고
		doGet(req, resp); // doGet으로 넘기기
	}

}
