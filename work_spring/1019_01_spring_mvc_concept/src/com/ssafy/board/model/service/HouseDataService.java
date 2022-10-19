package com.ssafy.board.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.board.dto.realestate.Item;
import com.ssafy.board.model.dao.HouseDataDao;
import com.ssafy.board.util.DBUtil;

public class HouseDataService {
	
	private static final String DB_NAME = "housedata2";
	
	private HouseDataDao dao = new HouseDataDao();

	public Map<String, String> selectSidoNames(HttpServletRequest req) throws SQLException {
		Connection conn = null;
		Map<String, String> map = null;
		
		try {
			conn = DBUtil.getConnection(DB_NAME);
			map = dao.selectSidoNames(conn);
		}
		finally {
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
		}
		finally {
			DBUtil.close(conn);
		}
		
		return map;
	}

	public List<Map<String, Object>> getAptTrade(String regionCode, String dealYmd) {
		List<Map<String, Object>> items = dao.requestAptTrade(regionCode, dealYmd);
		return items;
	}

	public List<Item> getRowHouseTrade(String regionCode, String dealYmd) {
		List<Item> items = dao.requestRowHouseTrade(regionCode, dealYmd);
		return items;
	}

}
