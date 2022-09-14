package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// -> 기존 Statement가져오는 방식을 SQL injection 공격에 취약했음
public class JDBCPreparedStatementTest {

	public static void main(String[] args) {
		
		
		try {
			
			// 1. 드라이버 로딩
			// Driver라는 class를 메모리상에 올려주는 작업
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로딩 완료...");
			
			// 2. Connection 객체 생성
			// jdbc:mysql:local주소/db이름?servertime설정&한글설정&ㅇㅇ;
			String url = "jdbc:mysql://127.0.0.1:3306/ssafydb?serverTimezone=Asia/Seoul&useUniCode=yes&characterEncoding=UTF-8";
			Connection conn = DriverManager.getConnection(url, "ssafy", "ssafy"); // (url, id, pass)
			System.out.println(conn.getClass().getName());
			System.out.println("Connection 완료...");
			
			// 실행할 쿼리문 작성
			// 빈칸으로 만들기 -> ? 사용 *
			String sql = "SELECT employee_id, first_name, job_id, salary FROM employees WHERE employee_id = ?";
			
			// 3-1. 쿼리문을 실행할 PreparedStatement 객체를 가져옴 *
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			// 3-2. 물음표 부분을 채워 넣기 *
			// 1번째 param을 100으로 -> 물음표만 채워넣기 때문에 재사용 가능
			stmt.setInt(1, 100);
			
			// 4-1. 쿼리문 실행 *
			ResultSet result = stmt.executeQuery(); // SELECT문일 때 executeQuery 사용

			// 4-2. 결과 출력
			while (result.next()) { // : 다음 행으로 이동해서 값을 가져옴 -> data가 있다면 cursor -> true 리턴하기 때문 
				StringBuilder sb = new StringBuilder();
				sb.append(result.getInt(1)).append("\t") // getInt(): int형으로 받아옴, getString(): string으로 받아옴
				.append(result.getString(1)).append("\t") // (1), (2), ... 1번째 컬럼, 2번째 컬럼, ...
				.append(result.getString(2)).append("\t")
				.append(result.getString(3)).append("\t")
				.append(result.getString(4));
				System.out.println(sb);
			}
			
			// 5. DBMS와 연결 끊기
			// -> 불러온 역순으로 닫아주기 
			result.close();
			stmt.close();
			conn.close();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
