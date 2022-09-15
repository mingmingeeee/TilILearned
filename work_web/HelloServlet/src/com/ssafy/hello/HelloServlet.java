package com.ssafy.hello;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public void init() {
		name = "강민정";
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. data get
				
		// 2. logic -> Logic 처리: JDBC
				
		// 3. response page ==> 응답페이지 html 
		response.setContentType("text/html;Charset=utf-8"); // 얇은 거로 함
		PrintWriter out = response.getWriter(); // stream -> 그 다음에 data의 통로를 크게 뚫어놓은 거에 꼽음
		out.println("<html>");
		out.println("<body>");
		out.println("<h2> Hello Servlet!!!!! </h2>");
		out.println("<h2>" + name + "님 안녕하세요!!!! </h2>");
		out.println("</body>");
		out.println("</html>");
		
		
	}

}
