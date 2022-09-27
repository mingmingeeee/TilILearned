package com.ssafy.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.TreeMap;

import com.ssafy.board.util.DBUtil;

public class HouseDataDao {

	public Map<String, String> selectSidoNames(Connection conn) throws SQLException {
		// 실행할 쿼리문 작성
		// 1. 시 뽑기: SELECT dongCode, sidoName FROM dongcode WHERE dongcode LIKE
		// '__00000000';
		String sql = "SELECT dongCode, sidoName FROM dongcode WHERE dongcode LIKE ?";

		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, "__00000000");

		ResultSet result = stmt.executeQuery();

		Map<String, String> map = new TreeMap<>(); // TreeMap을 사용하는 이유는 키 값 기준 오름차순 정렬을 위해
		while (result.next()) {
			map.put(result.getString("dongCode"), result.getString("sidoName"));
		}

		DBUtil.close(result);
		DBUtil.close(stmt);

		return map;
	}

	public Map<String, String> selectGuGunNames(Connection conn, String sidoCode) throws SQLException {
		// 실행할 쿼리문 작성
		// 1. 시 뽑기: SELECT dongCode, sidoName FROM dongcode WHERE dongcode LIKE
		// '__00000000';
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT dongCode, gugunName \n");
		sql.append("FROM dongcode \n");
		sql.append("WHERE dongCode LIKE ? \n");
		sql.append("AND gugunName IS NOT NULL \n");

		PreparedStatement stmt = conn.prepareStatement(sql.toString());

		stmt.setString(1, sidoCode + "___00000");
		
		ResultSet result = stmt.executeQuery();
		
		Map<String, String> map = new TreeMap<>();
		while(result.next()) {
			map.put(result.getString("dongCode"), result.getString("gugunName"));
		}

		DBUtil.close(result);
		DBUtil.close(stmt);

		return map;
	}

	public Map<String, String> selectDongames(Connection conn, String sidoCode, String gugunCode) throws SQLException {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT dongCode, dongName \n");
		sql.append("FROM dongcode \n");
		sql.append("WHERE dongCode LIKE ? \n");
		sql.append("AND dongName IS NOT NULL \n");

		PreparedStatement stmt = conn.prepareStatement(sql.toString());

		stmt.setString(1, sidoCode + gugunCode + "_____");
		
		ResultSet result = stmt.executeQuery();
		
		Map<String, String> map = new TreeMap<>();
		while(result.next()) {
			map.put(result.getString("dongCode"), result.getString("dongName"));
		}

		DBUtil.close(result);
		DBUtil.close(stmt);

		return map;
	}

}
