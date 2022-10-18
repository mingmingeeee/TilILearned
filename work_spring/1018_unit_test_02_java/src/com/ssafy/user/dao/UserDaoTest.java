package com.ssafy.user.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.ssafy.user.dto.User;

// @Test: 독립적으로 실행되기 때문에 
// ApplicationContext context = new ClassPathXmlApplicationContext("/com/ssafy/user/config/application.xml"); 
// 이 코드를 각 각 적어줘야 함
// Test간에 정보 공유 X, 서로 독립적이기 때문에 => 중복적인 코드 존재 
public class UserDaoTest {

	@Test // JUnit에게 테스트용 메서드임을 알려준다.
	public void addAndGet() throws SQLException { // JUnit 테스트 메서드 반드시 public void로 선언

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ssafy/user/config/application.xml");

		UserDao dao = context.getBean(UserDao.class);

		// 매 테스트 할 때, 동일한 테스트를 수행하면 동일한 결과를 얻어야 한다.
		// 그래서 아래와 같은 코드 추가. (TDD: test Driven Development) => 테스트 기반으로 운영되는 개발 방식
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);

		// 1. 사용자 추가 테스트
		User user1 = new User("ssafy", "김싸피", "비밀번호야");
		User user2 = new User("lee", "이싸피", "비밀001");
		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(), 2);

		// 2. 사용자 조회 테스트
		// 첫 번째 User의 id로 get()을 실행하면 첫 번째 User의 값을 가진 객체를 돌려주는지 테스트
		User userget1 = dao.get(user1.getId());

		// assertEquals: 두 개의 객체가 같은지 여부
		assertEquals(userget1.getName(), user1.getName());
		assertEquals(userget1.getPassword(), user1.getPassword());

		// 두 번째 User의 id로 get()을 실행하면 두 번째 User의 값을 가진 객체를 돌려주는지 테스트
		User userget2 = dao.get(user2.getId());
		assertEquals(userget2.getName(), user2.getName());
		assertEquals(userget2.getPassword(), user2.getPassword());

	}

	@Test
	public void count() throws SQLException {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ssafy/user/config/application.xml");

		UserDao dao = context.getBean(UserDao.class);
		
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
		
		User user1 = new User("ssafy", "김싸피", "비밀번호야");
		User user2 = new User("lee", "이싸피", "비밀001");
		User user3 = new User("jeong", "정싸피", "비밀002");
		
		dao.add(user1);
		assertEquals(dao.getCount(), 1);
		
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		dao.add(user3);
		assertEquals(dao.getCount(), 3);

	}
	
	/**
	 * 주의할 점: 두 개의 테스트가 어떤 순서로 실행될지는 알 수 없다.
	 * 모든 테스트는 실행 순서에 상관없이 독립적으로 항상 동일한 결과를 낼 수 있도록 한다. 
	 * */
	
	// 테스트 중에 발생할 것으로 기대하는 예외 클래스를 지정해준다.
	@Test(expected = EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ssafy/user/config/application.xml");

		UserDao dao = context.getBean(UserDao.class);
		
		/*
		 * 이 메소드 실행 중에 예외가 발생해야 한다.
		 * 예외가 발생하지 않으면 테스트가 실패한다.
		 * */
		
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
		
		dao.get("알수없는_id_값");
		
//		int result = 1 / 0;
		
	}
	
	/*
	 * 기능 설계, 코드 구현, 테스트 세 가지의 작업을 동시에 할 수 있다.  
	 * 
	 * 테스트 주도 개발 (TDD: Test Driven Development)
	 * TDD의 기본 원칙: 실패한 테스트를 성공시키기 위한 목적이 아닌 코드는 만들지 않는다. 실패한 테스트를 성공시키도록 코드를 작성한다.
	 * TDD는 테스트를 먼저 만들고 그 테스트라 성공하도록 하는 코드만 만드는 식으로 진행하기 때문에
	 * 테스트를 빼먹지 않고 꼼꼼하게 만들어낼 수 있따.
	 * */
	
	

}
