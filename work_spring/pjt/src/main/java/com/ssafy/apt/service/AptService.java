package com.ssafy.apt.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ssafy.apt.dao.AptDao;
import com.ssafy.apt.model.dto.Area;

public class AptService {

	private static AptService instance = null;
	private AptDao dao = AptDao.getInstance();

	public static AptService getInstance() {
		if (instance == null) {
			instance = new AptService();
		}
		return instance;
	}

	public JSONArray getHouses(String regionCode, String dealYmd) throws SQLException {
		return dao.selectAll(regionCode, dealYmd);
	}

	public Map<String, String> selectSidoNames() throws SQLException {
		return dao.selectSidoNames();
	}

	public Map<String, String> getGuGunNames(String sidoCode) throws SQLException {

		return dao.selectGuGunNames(sidoCode);
	}

	public int saveFavorite(String userId, String code) throws SQLException {
		return dao.insertFavorite(userId, code);
	}

	public List<JSONObject> getFavorite(String userId) throws SQLException {
		return dao.getFavorite(userId);
	}
	public JSONObject getAptDealInfo(String aptCode) throws SQLException {
		return dao.getAptDealInfo(aptCode);
	}

	public List<Area> getFavorite2(String userId) throws SQLException {
		return dao.getFavorite2(userId);
	}

}
