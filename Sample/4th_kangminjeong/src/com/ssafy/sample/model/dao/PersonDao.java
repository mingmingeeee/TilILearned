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

	public int regist(Person person) throws SQLException {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO person (id, name, department_name, pay) \n");
			sql.append("VALUES (?, ?, ?, ?) \n");
			
			conn = dbutil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, person.getId());
			pstmt.setString(2, person.getName());
			pstmt.setString(3, person.getDept());
			pstmt.setString(4, person.getPay());
			
			int cnt = pstmt.executeUpdate();
			
			return cnt;
			
		} finally {
			dbutil.close(pstmt, conn);
		}
		
	}

	public List<Person> selectAll() throws SQLException {
		
		List<Person> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT id, name, department_name, pay \n");
			sql.append("FROM person \n");
			
			conn = dbutil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(new Person(rs.getString("id"), rs.getString("name"),
						rs.getString("department_name"), rs.getString("pay")));
			}
		
			
			return list;
			
		}finally {
			dbutil.close(rs, pstmt, conn);
		}
	}

	public Person select(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("SELECT id, name, department_name, pay \n");
			sql.append("FROM person \n");
			sql.append("WHERE id = ? \n");
			
			conn = dbutil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				Person person = new Person(rs.getString("id"), rs.getString("name"),
						rs.getString("department_name"), rs.getString("pay"));
				return person;
			}
			
			return null;
		}finally {
			dbutil.close(rs, pstmt, conn);
		}

	}

	public int delete(String id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			StringBuilder sql = new StringBuilder();
			
			sql.append("DELETE FROM person \n");
			sql.append("WHERE id = ? \n");
			
			conn = dbutil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, id);
			
			int cnt = pstmt.executeUpdate();
			
			return cnt;
			
		} finally {
			dbutil.close(rs, pstmt, conn);
		}

	}

}
