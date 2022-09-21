package com.ssafy.sample.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.sample.dto.Product;
import com.ssafy.sample.util.DBUtil;

public class ProductDao {

	private DBUtil dbUtil = DBUtil.getInstance();

	public int insert(Product product) throws SQLException {
		// 1. 실행할 쿼리문 작성
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO product (code, model, price) \n");
		sql.append("VALUES (?, ?, ?) \n");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			// 2. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
			conn = dbUtil.getConnection();

			pstmt = conn.prepareStatement(sql.toString());

			// 3. 물음표 부분 채워 넣기
			pstmt.setString(1, product.getCode());
			pstmt.setString(2, product.getModel());
			pstmt.setString(3, product.getPrice()); // 굳이 Int로 받을 필요 없이 String으로 받음

			// 4. 쿼리문 실행 (DML은 executeUpdate 호출)
			int cnt = pstmt.executeUpdate();
			if (cnt == 0) { // 정상적 Update X
				dbUtil.close(pstmt, conn);
				throw new SQLException();
			}
			return cnt;
		} finally {
			// 4. connection들 close
			dbUtil.close(pstmt, conn);
		}

	}

	public List<Product> selectAll() throws SQLException {
		// 1. 실행할 쿼리문 작성
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT code, model, price \n");
		sql.append("FROM product \n");
		sql.append("ORDER BY code ASC \n");

		// 변수 바깥쪽으로 빼주기 -> finally에서 닫으려고
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 2. 쿼리문을 실행할 PreparedStatement 객체를 가져옴
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			// 3. 쿼리문 실행 (DQL은 executeQuery 호출)
			rs = pstmt.executeQuery();
			// 4-1. 실행 결과 리턴
			List<Product> list = new ArrayList<>();

			// 여러개 처리: while
			// 한 건만 처리: if
			while (rs.next()) {
				Product product = new Product(rs.getString("code"), rs.getString("model"), rs.getString("price"));
				list.add(product);
			}

			return list;
		} finally {
			// 4-2. connection들 close
			dbUtil.close(rs, pstmt, conn);
		}
	}

	public Product select(String code) throws SQLException {
		// 1. 실행할 쿼리문 작성
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT code, model, price \n");
		sql.append("FROM product \n");
		sql.append("WHERE code = ? \n");

		// 2. 쿼리문을 실행할 PreparedStatement 객체 가져옴
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			// 3. 물음표 부분 채워넣기
			pstmt.setString(1, code);

			// 4. 쿼리문 실행 (DQL은 excuteQuery 호출)
			rs = pstmt.executeQuery();

			// 4-1. 실행 결과 리턴
			Product product = null;
			if (rs.next()) {
				product = new Product(rs.getString("code"), rs.getString("model"), rs.getString("price"));
			}

			if (product == null) {
				throw new SQLException();
			}

			return product;

		} finally {
			dbUtil.close(rs, pstmt, conn);
		}
	}

	public int delete(String removeCode) throws SQLException {
		// 1. 실행할 쿼리문 작성
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM product \n");
		sql.append("WHERE code = ? \n");

		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			// 2. preparedStatement
			conn = dbUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			// 3. 물음표 채우기
			pstmt.setString(1, removeCode);

			// 4. 쿼리문 실행 & 결과 리턴
			cnt = pstmt.executeUpdate();
			return cnt;

		} finally {
			// 5. 닫기
			dbUtil.close(pstmt, conn);
		}
	}

}
