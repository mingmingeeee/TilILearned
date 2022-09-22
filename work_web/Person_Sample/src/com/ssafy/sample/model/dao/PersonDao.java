package com.ssafy.sample.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.sample.dto.Person;
import com.ssafy.sample.util.DBUtil;

public class PersonDao {

	DBUtil dbutil = DBUtil.getInstance();

	public int insert(Person person) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO person (id, name, department_name, pay) \n");
		sql.append("VALUES (?, ?, ?, ?) \n");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = dbutil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, person.getId());
			pstmt.setString(2, person.getName());
			pstmt.setString(3, person.getDepartmentName());
			pstmt.setString(4, person.getPay());

			int cnt = pstmt.executeUpdate();

			if (cnt == 0) {
				throw new SQLException();
			}

			return cnt;

		} finally {
			dbutil.close(pstmt, conn);
		}

	}

	public List<Person> selectAll() throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT id, name, department_name, pay \n");
		sql.append("FROM person \n");
		sql.append("ORDER BY id ASC \n");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			List<Person> list = new ArrayList<>();

			conn = dbutil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Person person = new Person(rs.getString("id"), rs.getString("name"), rs.getString("department_name"),
						rs.getString("pay"));

				list.add(person);

			}

			return list;

		} finally {
			dbutil.close(pstmt, conn);
		}
	}

	public Person select(String id) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT id, name, department_name, pay \n");
		sql.append("FROM person \n");
		sql.append("WHERE id = ? \n");

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			conn = dbutil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			Person person = null;

			if (rs.next()) {

				person = new Person(rs.getString("id"), rs.getString("name"), rs.getString("department_name"),
						rs.getString("pay"));

			}

			if (person == null) {
				throw new SQLException();
			}

			return person;

		} finally {
			dbutil.close(pstmt, conn);
		}
	}

	public int delete(String id) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM person \n");
		sql.append("WHERE id = ? \n");

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = dbutil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, id);

			int cnt = pstmt.executeUpdate();

			return cnt;

		} finally {
			dbutil.close(pstmt, conn);
		}
	}

}
