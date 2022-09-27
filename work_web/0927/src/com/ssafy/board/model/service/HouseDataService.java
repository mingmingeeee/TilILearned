package com.ssafy.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import com.ssafy.board.model.dao.HouseDataDao;
import com.ssafy.board.util.DBUtil;

public class HouseDataService {
	
	private static final String DB_NAME = "housedata2";
	
	private HouseDataDao dao = new HouseDataDao();

	public Map<String, String> selectSidoNames() throws SQLException {
		Connection conn = null;
		Map<String, String> map = null;
		
		try {
			conn = DBUtil.getConnection(DB_NAME);
			map = dao.selectSidoNames(conn);
		} finally {
			DBUtil.close(conn);
		}
		
		return map;
	}

	public Map<String, String> getGuGunNames(String sidoCode) throws SQLException {
		Connection conn = null;
		Map<String, String> map = null;
		
		try {
			conn = DBUtil.getConnection(DB_NAME);
			map = dao.selectGuGunNames(conn, sidoCode);
		} finally {
			DBUtil.close(conn);
		}
		
		return map;
	}

	public Map<String, String> getDongNames(String sidoCode, String gugunCode) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn = null;
		Map<String, String> map = null;
		
		try {
			conn = DBUtil.getConnection(DB_NAME);
			map = dao.selectDongames(conn, sidoCode, gugunCode);
		} finally {
			DBUtil.close(conn);
		}
		
		return map;
	}

}
