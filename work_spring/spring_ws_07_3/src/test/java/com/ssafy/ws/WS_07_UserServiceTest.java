package com.ssafy.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.dto.User;
import com.ssafy.ws.model.service.UserService;

public class WS_07_UserServiceTest extends AbstractTest {

	private static final Logger logger = LoggerFactory.getLogger(WS_06_UserDaoTest.class);

	// 픽스처(fixture): 테스트를 수행하는데 필요한 정보나 객체
	@Autowired
	private UserService userService;

	private User user1;
	// 픽스처 끝

	@Before
	public void setUp() {

		this.user1 = new User("ssafy", "김싸피", "1234", null);

	}

	@Test
	public void addAndGetOne() {

		userService.deleteAll();
		assertEquals(0, userService.getCount());

		// 1. 사용자 추가 테스트
		assertEquals(1, userService.insert(user1));
		assertEquals(1, userService.getCount());

		// 2. 사용자 조회 테스트
		User userget1 = userService.select(user1.getId());
		assertEquals(user1.getId(), userget1.getId());
		assertEquals(user1.getName(), userget1.getName());
		assertEquals(user1.getPass(), userget1.getPass());
		assertEquals(user1.getRecId(), userget1.getRecId());

	}

	@Test
	public void login() {

		userService.deleteAll();
		assertEquals(0, userService.getCount());

		// 1. 사용자 추가 테스트
		assertEquals(1, userService.insert(user1));
		assertEquals(1, userService.getCount());

		// 2. 패스워드를 제외한 나머지 사용자 정보 받아오기 테스트
		User user = userService.login(user1);
		assertNotNull(user);
		assertNull(user.getPass());

		// 3. 사용자 조회 테스트
		assertEquals(user1.getId(), user.getId());
		assertEquals(user1.getName(), user.getName());
		assertEquals(user1.getRecId(), user.getRecId());
	}

}
