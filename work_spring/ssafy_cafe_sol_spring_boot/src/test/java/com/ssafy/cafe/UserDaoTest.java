package com.ssafy.cafe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.cafe.model.dto.User;
import com.ssafy.cafe.model.mapper.UserDao;

public class UserDaoTest extends AbstractTest {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	// 픽스처(fixture): 테스트를 수행하는데 필요한 정보나 객체
	@Autowired
	private UserDao userDao;

	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User user5;
	private User user6;
	private User user7;
	private User user8;
	private User user9;
	private User user10;
	// 픽스처 끝

	@Before
	public void setUp() {
		
		this.user1 = new User("ssafy01", "김싸피", "pass01", 5);
		this.user2 = new User("ssafy02", "이싸피", "pass02", 0);
		this.user3 = new User("ssafy03", "박싸피", "pass03", 3);
		this.user4 = new User("ssafy04", "최싸피", "pass04", 4);
		this.user5 = new User("ssafy05", "정싸피", "pass05", 5);
		this.user6 = new User("ssafy06", "강싸피", "pass06", 6);
		this.user7 = new User("ssafy07", "조싸피", "pass07", 7);
		this.user8 = new User("ssafy08", "윤싸피", "pass08", 8);
		this.user9 = new User("ssafy09", "장싸피", "pass09", 9);
		this.user10 = new User("ssafy10", "임싸피", "pass10", 20);
		
	}

	@Test
	public void addAndGet() {  // 추가, 조회 테스트
		
		userDao.deleteAll();
		assertEquals(0, userDao.getCount());
		
		// 1. 추가 테스트
		assertEquals(1, userDao.insert(user1));
		assertEquals(1, userDao.insert(user2));
		assertEquals(2, userDao.getCount());
		
		// 2. 조회 테스트
		User userget1 = userDao.select(user1.getId());
		assertEquals(user1.getId(), userget1.getId());
		assertEquals(user1.getName(), userget1.getName());
		assertEquals(user1.getPass(), userget1.getPass());
		assertEquals(user1.getStamps(), userget1.getStamps());
		
		User userget2 = userDao.select(user2.getId());
		assertEquals(user2.getId(), userget2.getId());
		assertEquals(user2.getName(), userget2.getName());
		assertEquals(user2.getPass(), userget2.getPass());
		assertEquals(user2.getStamps(), userget2.getStamps());
	}
	
	@Test
	public void count() {  // count 메서드 테스트 
		
		userDao.deleteAll();
		assertEquals(0, userDao.getCount());
		
		assertEquals(1, userDao.insert(user1));
		assertEquals(1, userDao.getCount());
		
		assertEquals(1, userDao.insert(user2));
		assertEquals(2, userDao.getCount());
		
		assertEquals(1, userDao.insert(user3));
		assertEquals(3, userDao.getCount());
	}
	
	@Test
	public void addAndUpdate() {  // 추가, 수정 테스트
		
		userDao.deleteAll();
		assertEquals(0, userDao.getCount());
		
		// 1. 추가 테스트
		assertEquals(1, userDao.insert(user1));
		assertEquals(1, userDao.getCount());
		
		// 스탬프 개수 변경
		user1.setStamps(100);
		
		// 2. 수정 테스트
		assertEquals(1, userDao.update(user1));
		
		// 3. 도서 조회 테스트
		User userget1 = userDao.select(user1.getId());
		assertEquals(user1.getId(), userget1.getId());
		assertEquals(user1.getName(), userget1.getName());
		assertEquals(user1.getPass(), userget1.getPass());
		assertEquals(user1.getStamps(), userget1.getStamps());
	}
	
	@Test
	public void addAndDelete() {  // 추가, 삭제 테스트
		
		userDao.deleteAll();
		assertEquals(0, userDao.getCount());
		
		// 1. 추가 테스트
		assertEquals(1, userDao.insert(user1));
		assertEquals(1, userDao.getCount());
		
		// 2. 삭제 테스트
		assertEquals(1, userDao.delete(user1.getId()));
		
		// 3. 조회 테스트
		User userget1 = userDao.select(user1.getId());
		assertNull(userget1);
	}
	
	@Test
	public void selectAll() {  // 모두 조회 테스트
		
		userDao.deleteAll();
		assertEquals(0, userDao.getCount());
		
		// 1. 3개 추가
		assertEquals(1, userDao.insert(user1));
		assertEquals(1, userDao.insert(user2));
		assertEquals(1, userDao.insert(user3));
		assertEquals(3, userDao.getCount());
		
		// 2. 모두 조회 테스트
		List<User> users = userDao.selectAll();
		assertEquals(3, users.size());
	}
	
}
