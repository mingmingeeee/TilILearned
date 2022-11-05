package com.ssafy.apt.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.house.model.dto.DongCode;
import com.ssafy.house.model.dto.FavArea;
import com.ssafy.house.model.dto.HouseDeal;
import com.ssafy.house.model.dto.HouseInfo;

public interface AptDao {

	// 특정 동의 houseinfo를 가져온다
	List<HouseInfo> selectAll(String regionCode);

	// 특정 아파트의 거래 상세정보를 가져온다.
	List<HouseDeal> getAptDealInfo(String aptCode);

	public List<DongCode> selectGuGunNames(String sidoCode);

	public List<DongCode> selectSidoNames(String code);
	public List<DongCode> selectDongNames(String code);

	// 사용자의 관심지역 목록을 가져온다.
	List<FavArea> getFavorite2(String userId);
	
	// 특정 동을 관심지역으로 등록한다.
	// parameter String userId, String dongCode
	int insertFavorite(Map map);
}
