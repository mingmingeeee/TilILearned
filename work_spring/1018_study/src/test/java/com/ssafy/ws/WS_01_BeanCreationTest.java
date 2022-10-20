package com.ssafy.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.ssafy.ws.model.repo.BookRepo;
import com.ssafy.ws.model.repo.UserRepo;
import com.ssafy.ws.model.service.BookService;
import com.ssafy.ws.model.service.BookServiceImpl;
import com.ssafy.ws.model.service.UserService;

public class WS_01_BeanCreationTest extends AbstractTest {

	// debug logger
	private static Logger logger = LoggerFactory.getLogger(WS_01_BeanCreationTest.class);

	@Autowired
	private BookRepo bookRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private BookService bookService;

	@Autowired
	private UserService userService;

	@Autowired
	private DataSource dataSource;

	// 1. BookRepo, UserRepo, BookService, UserService 가 잘 생성되었는가?
	@Test
	public void testBeanCreation() {
		assertNotNull(bookRepo);
		assertNotNull(userRepo);
		assertNotNull(bookService);
		assertNotNull(userService);
	}

	// 2. 컨텍스트에서 직접 얻어온 BookRepo 와 BookService 가 가지는 repo 가 동일한가?
	@Test
	public void testSingleton() {
		assertEquals(bookRepo, ((BookServiceImpl) bookService).getBookRepo());
	}

	// 3. DataSource 객체가 잘 생성되었는지 테스트하고 출력한다
	@Test
	public void testDataSource() {
		logger.debug("dataSource 확인: {}", dataSource);
		assertNotNull(dataSource);
	}

}
