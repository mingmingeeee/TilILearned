package com.ssafy.hello;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleTest
 */
@WebServlet("/LifeCycleTest")
public class LifeCycleTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifeCycleTest() {
        super();
        System.out.println("객체 생성합니다.");
    }

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("객체 제거합니다.");
	}

	@Override
	public void init() throws ServletException { 
		super.init();
		System.out.println("객체 초기화합니다.");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		System.out.println("클라이언트 요청 서비스합니다.");
	}


}
