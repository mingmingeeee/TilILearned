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
			RequestDispatcher disp2 = req.getRequestDispatcher("/index.jsp");
			disp2.forward(req, resp);
			break;

		case "regist_form":
			resp.sendRedirect(req.getContextPath() + "/person/regist.jsp");
			break;

		case "regist":

			String id = req.getParameter("personId");
			System.out.println(id + " ");
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
				e.printStackTrace();
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}

			break;

		case "list":
			try {
				List<Person> list = service.selectAll();

				req.setAttribute("list", list);

				// forward
				RequestDispatcher disp = req.getRequestDispatcher("/list_result.jsp");
				disp.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}

			break;

		case "detail":
			String detailId = req.getParameter("id");
			System.out.println(detailId);
			try {
				Person detailPerson = service.select(detailId);

				req.setAttribute("person", detailPerson);

				// forward
				RequestDispatcher disp = req.getRequestDispatcher("/detail.jsp");
				disp.forward(req, resp);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
			}
			break;

		case "remove":
			try {
				String deleteId = req.getParameter("id");
				int cnt = service.delete(deleteId);

				// redirect
				resp.sendRedirect(req.getContextPath() + "/main?act=list");
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
			System.out.println(userid + " " + userpass);
			if (user != null) {
				req.setAttribute("user", user);
			} else {
				req.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			}

			// forward
			RequestDispatcher disp = req.getRequestDispatcher("/main?act=index");
			disp.forward(req, resp);

			break;

		case "logout":

			boolean isLogout = service.logout(req);

			if (isLogout) {
				req.setAttribute("msg", "로그아웃되었습니다.");
			} else {
				req.setAttribute("msg", "로그아웃 실패했습니다.");
			}

			// forward
			RequestDispatcher disp3 = req.getRequestDispatcher("/main?act=index");
			disp3.forward(req, resp);

			break;

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		doGet(req, resp);
	}

}
