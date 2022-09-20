package com.ssafy.board.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/boarddb?serverTimezone=Asia/Seoul&useUniCode=yes&characterEncoding=UTF-8";
	private static final String USER = "ssafy";
	private static final String PASSWORD = "ssafy";
	
	static {
		// 1. 드라이버 로딩
		try {
			Class.forName(DRIVER);
			
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		// 2. Connection 생성
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		conn.setAutoCommit(false);
		return conn;
	}
	
	public static void close(AutoCloseable obj) {
		if (obj != null) {
			try {
				// 5. DBMS와 연결 끊기
				obj.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
