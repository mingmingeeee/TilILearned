package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.board.dto.User;
import com.ssafy.board.util.DBUtil;

public class UserDao {

	public User select(Connection conn, User loginUser) throws SQLException {
		
		// 실행할 쿼리문 작성
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id \n");
		sql.append("FROM user \n");
		sql.append("WHERE id = ? \n");
		sql.append("AND pw = ? \n");
		sql.append("AND isActive = ? \n");
		
		PreparedStatement stmt = conn.prepareStatement(sql.toString());
		
		stmt.setString(1, loginUser.getId());
		stmt.setString(2, loginUser.getPw());
		stmt.setBoolean(3, loginUser.getIsActive());
		
		ResultSet result = stmt.executeQuery();
		
		User user = null;
		if(result.next()) {
			user = new User(result.getString("id"), null, null);
		}
		
		DBUtil.close(result); // rs 닫고
		DBUtil.close(stmt); // stmt 닫고
		
		return user;
		
		
	}

}
