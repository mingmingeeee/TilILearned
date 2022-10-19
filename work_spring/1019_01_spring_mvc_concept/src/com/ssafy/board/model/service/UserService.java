package com.ssafy.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.dto.User;
import com.ssafy.board.model.dao.UserDao;
import com.ssafy.board.util.DBUtil;

public class UserService {

	private UserDao dao = new UserDao();

	public User login(HttpServletRequest request, HttpServletResponse response, User loginUser) throws SQLException {

		Connection conn = null;
		User user = null;

		try {
			conn = DBUtil.getConnection();
			user = dao.select(conn, loginUser);

			if (user != null) {
				// 로그인 정보를 세션에 저장
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				
				// cookie 설정
				String remember = request.getParameter("remember");
				
				// 아이디 저장 체크한 경우
				if (remember != null) {
					Cookie cookie = new Cookie("savedId", user.getId());
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(60 * 60 * 24 * 365);  // 유효기간 1년
					response.addCookie(cookie);
				} 
				// 아이디 저장 체크하지 않은 경우
				else {
					// 유효하지 않은 쿠키로 설정하기
					Cookie cookie = new Cookie("savedId", user.getId());
					cookie.setPath(request.getContextPath());
					cookie.setMaxAge(0); // 유효하지 않은 쿠키
					response.addCookie(cookie);
				}
			}
		}
		finally {
			DBUtil.close(conn);
		}

		return user;
	}
}
