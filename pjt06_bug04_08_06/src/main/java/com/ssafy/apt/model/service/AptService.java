package com.ssafy.apt.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.house.model.dto.DongCode;
import com.ssafy.house.model.dto.HouseDeal;
import com.ssafy.house.model.dto.HouseInfo;

public interface AptService {

	public List<HouseInfo> getHouses(String regionCode, String dealYmd) throws SQLException;

	public  List<DongCode> getGuGunNames(String sidoCode) throws SQLException;

	public List<HouseDeal> getAptDealInfo(String aptCode) throws SQLException;

	/**
	 * 
	 * @param code 는 기본값("__00000000")를 넣어준다.
	 * @return
	 */
	public List<DongCode> selectSidoNames(String code);
	
	public List<DongCode> selectDongNames(String code);
}
