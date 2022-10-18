package com.ssafy.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ssafy.dto.User;

@Repository
public class UserDao {
	
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;
	
	public void add(User user) throws SQLException {
		// 1. Connection 객체 얻어오기
		Connection conn = this.dataSource.getConnection();
		
		// 2. PreparedStatement 객체 얻어오기
		PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users(id, name, password) VALUES (?, ?, ?)");
		
		// 3. 빈 칸 채우기
		pstmt.setString(1, user.getId());
		pstmt.setString(2, user.getName());
		pstmt.setString(3, user.getPassword());
		
		// 4. sql문 실행
		pstmt.executeUpdate();
		
		// 5. 연결 닫기
		pstmt.close();
		conn.close();
	}
	
	public User get(String id) throws SQLException {
		// 1. Connection 객체 얻어오기
		Connection conn = this.dataSource.getConnection();
		
		// 2. PreparedStatement 객체 얻어오기
		PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
		
		// 3. 빈 칸 채우기
		pstmt.setString(1, id);
		
		// 4. sql문 실행
		ResultSet rs = pstmt.executeQuery();
		
		User user = new User();
		if(rs.next()) {
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		// 5. 연결 닫기
		rs.close();
		pstmt.close();
		conn.close();
		
		return user;
	}

	public void deleteAll() throws SQLException {
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("DELETE FROM users");
		pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
	}

	public Object getCount() throws SQLException {
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement("SELECT COUNT(*) FROM users");
		
		ResultSet rs = pstmt.executeQuery();
		int count = -1;
		
		if(rs.next()) {
			count = rs.getInt(1);
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return count;
	}

}
