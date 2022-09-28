package com.ssafy.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.board.dto.User;
import com.ssafy.board.model.dao.UserDao;
import com.ssafy.board.util.DBUtil;

public class UserService {
	
	private UserDao dao = new UserDao();

	public User login(HttpServletRequest req, HttpServletResponse resp, User loginUser) throws SQLException {
		
		Connection conn = null;
		User user = null;
		
		try {
			conn = DBUtil.getConnection();
			user = dao.select(conn, loginUser);
			
			// 해당 사용자가 존재하면
			if (user != null) {
				// 로그인 정보를 세션에 저장
				HttpSession session = req.getSession();
				session.setAttribute("user", user);
			}
		}
		finally {
			DBUtil.close(conn);
		}
		
		return user;
	}

}
