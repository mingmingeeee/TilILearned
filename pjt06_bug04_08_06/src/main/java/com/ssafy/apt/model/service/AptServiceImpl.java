package com.ssafy.apt.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ssafy.apt.model.mapper.AptDao;
import com.ssafy.house.model.dto.DongCode;
import com.ssafy.house.model.dto.HouseDeal;
import com.ssafy.house.model.dto.HouseInfo;

@Service
public class AptServiceImpl implements AptService {

	@Autowired
	private AptDao dao;

	public List<HouseInfo> getHouses(String regionCode, String dealYmd) throws SQLException {
		return dao.selectAll(regionCode);
	}

//	public int saveFavorite(String userId, String code) throws SQLException {
//		return dao.insertFavorite(userId, code);
//	}
//
//	public List<JSONObject> getFavorite(String userId) throws SQLException {
//		return dao.getFavorite(userId);
//	}

	public List<HouseDeal> getAptDealInfo(String aptCode) throws SQLException {
		return dao.getAptDealInfo(aptCode);
	}

//	public List<Area> getFavorite2(String userId) throws SQLException {
//		return dao.getFavorite2(userId);
//	}

	public List<DongCode> getGuGunNames(String sidoCode) {
		return dao.selectGuGunNames(sidoCode);
	}

	public List<DongCode> selectDongNames(String code) {
		return dao.selectDongNames(code);
	}

	@Override
	public List<DongCode> selectSidoNames(String code) {
		// TODO Auto-generated method stub
		return dao.selectSidoNames(code);
	}

}
