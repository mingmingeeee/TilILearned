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

import com.ssafy.sample.dto.Person;
import com.ssafy.sample.dto.User;
import com.ssafy.sample.model.service.PersonService;

@WebServlet("/main")
public class MainController extends HttpServlet {

	private PersonService service = new PersonService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String act = req.getParameter("act");

		switch (act) {

		case "index":
			// redirect
			resp.sendRedirect(req.getContextPath() + "/index.jsp");
			break;

		case "regist_form":
			// redirect
			resp.sendRedirect(req.getContextPath() + "/person/regist.jsp");
			break;

		case "regist":
			String id = req.getParameter("personId");
			String name = req.getParameter("name");
			String departmentName = req.getParameter("departmentName");
			String pay = req.getParameter("pay");

			Person person = new Person(id, name, departmentName, pay);

			try {
				int cnt = service.regist(person);

				req.setAttribute("cnt", cnt);

				// forward
				RequestDispatcher disp = req.getRequestDispatcher("/regist_result.jsp");
				disp.forward(req, resp);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				// redirect
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}

			break;

		case "list":
			try {
				List<Person> list = service.list();

				req.setAttribute("list", list);

				System.out.println(list.toString());

				RequestDispatcher disp = req.getRequestDispatcher("/list_result.jsp");
				disp.forward(req, resp);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// redirect
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}
			break;

		case "detail":

			String detailId = req.getParameter("id");
			try {
				Person detailPerson = service.detail(detailId);

				req.setAttribute("person", detailPerson);

				RequestDispatcher disp = req.getRequestDispatcher("/detail_result.jsp");
				disp.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}

			break;

		case "remove":

			String deleteId = req.getParameter("id");
			try {

				System.out.println(deleteId);

				int cnt = service.remove(deleteId);

				RequestDispatcher disp = req.getRequestDispatcher("/main?act=list");
				disp.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}

			break;

		case "login":

			String userid = req.getParameter("userid");
			String userpass = req.getParameter("userpass");

			User user = service.login(req, userid, userpass);

			if (user != null) {
				req.setAttribute("user", user);
			} else {
				req.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다.");
			}

			RequestDispatcher disp = req.getRequestDispatcher("/index.jsp");
			disp.forward(req, resp);

			break;

		case "logout":

			boolean isLogout = service.logout(req);

			if(isLogout) {
				req.setAttribute("message", "로그아웃 되었습니다.");
			} else {
				req.setAttribute("message", "로그아웃에 실패하였습니다.");
			}
			
			RequestDispatcher logoutDisp = req.getRequestDispatcher("/index.jsp");
			logoutDisp.forward(req, resp);
			
			break;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}

}
