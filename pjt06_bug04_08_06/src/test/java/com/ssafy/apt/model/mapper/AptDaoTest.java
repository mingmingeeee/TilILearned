package com.ssafy.apt.model.mapper;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.house.model.dto.HouseDeal;
import com.ssafy.house.model.dto.HouseInfo;

public class AptDaoTest extends AbstractTest {
	private static final Logger logger = LoggerFactory.getLogger(AptDaoTest.class);

	@Autowired
	private AptDao aptDao;

//	@Test
	public void test() {
		List<HouseInfo> favlist = aptDao.selectAll("1117010100");

		assertNotEquals(favlist.size(), 0);
	}
	
//	@Test
	public void userFavAreaInsertTest() {
		Map<String,String> map = new HashMap();
		map.put("userId", "ssafy");
		map.put("dongCode", "1132010700");
		
		int cnt = aptDao.insertFavorite(map);

		
		assertNotEquals(aptDao.getFavorite2("ssafy"), 0);
	}

	@Test
	public void getAptDealInfoTest() {
		
		List<HouseDeal> houseDeal = aptDao.getAptDealInfo("11110000000027");

		
		assertNotEquals(houseDeal, null);
	}
	
}
