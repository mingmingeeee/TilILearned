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
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id \n");
		sb.append("FROM user \n");
		sb.append("WHERE id = ? \n");
		sb.append("AND pw = ? \n");
		sb.append("AND isActive = ? \n");
		
		String sql = sb.toString();

		// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
		PreparedStatement stmt = conn.prepareStatement(sql);

		// 3-2. 물음표 부분을 채워 넣기
		stmt.setString(1, loginUser.getId());
		stmt.setString(2, loginUser.getPass());
		stmt.setBoolean(3, loginUser.isActive());

		// 4-1. 쿼리문 실행
		ResultSet result = stmt.executeQuery();

		// 4-2. 실행 결과 리턴
		User user = null;
		if (result.next()) {
			user = new User(result.getString("id"), null, null);
		}

		DBUtil.close(result);
		DBUtil.close(stmt);

		return user;
	}

}
