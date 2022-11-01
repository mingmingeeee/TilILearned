package com.ssafy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private final static String driverName = "com.mysql.cj.jdbc.Driver";
	private final static String url = "jdbc:mysql://localhost:3306/housedata2?serverTimezone=Asia/Seoul&useUniCode=yes&characterEncoding=UTF-8";
	private final static String user = "ssafy";
	private final static String pass = "ssafy";

	private static DBUtil instance = new DBUtil();

	private DBUtil() {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static DBUtil getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, pass);
	}
	
	public static Connection getConnection(String dbName) throws SQLException {
		// 2. Connection 생성
		String url = "jdbc:mysql://127.0.0.1:3306/" + dbName +"?serverTimezone=Asia/Seoul&useUniCode=yes&characterEncoding=UTF-8";
		Connection conn = DriverManager.getConnection(url, user, pass);
		conn.setAutoCommit(true);
		return conn;
	}

//	public void close(PreparedStatement pstmt, Connection conn) {
//		try {
//			if (pstmt != null)
//				pstmt.close();
//			if (conn != null)
//				conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
//		try {
//			if (rs != null)
//				rs.close();
//			if (pstmt != null)
//				pstmt.close();
//			if (conn != null)
//				conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}

	public static void close(AutoCloseable... closeables) {
		for (AutoCloseable c : closeables) {
			if (c != null) {
				try {
					c.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
