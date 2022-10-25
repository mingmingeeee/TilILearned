package com.ssafy.ws;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.dao.UserDao;
import com.ssafy.ws.model.dto.User;

public class WS_06_UserDaoTest extends AbstractTest {
	
	private static final Logger logger = LoggerFactory.getLogger(WS_06_UserDaoTest.class);
	
	// 픽스처(fixture): 테스트를 수행하는데 필요한 정보나 객체
	@Autowired
	private UserDao userDao;
	
	private User user1;
	// 픽스처 끝
	
	@Before
	public void setUp() {
		
		this.user1 = new User("ssafy", "김싸피", "1234", null);
		
	}
	
	@Test
	public void addAndGetOne() {
		
		userDao.deleteAll();
		assertEquals(0, userDao.getCount());
		
		// 1. 사용자 추가 테스트
		
		// 2. 사용자 조회 테스트
		
	}
}
