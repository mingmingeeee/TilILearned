package com.ssafy.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.repo.BookRepo;
import com.ssafy.ws.model.repo.UserRepo;
import com.ssafy.ws.model.service.BookService;
import com.ssafy.ws.model.service.BookServiceImpl;
import com.ssafy.ws.model.service.UserService;

public class WS_01_BeanCreationTest extends AbstractTest {
	
	// 로깅 처리를 위해 Logger를 추가
	private static Logger logger = LoggerFactory.getLogger(WS_01_BeanCreationTest.class);
	
	// 필요한 빈들을 @Autowired를 이용하여 주입 받기
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DataSource dataSource;
	
	// 1. BookRepo, UserRepo, BookService, UserService가 잘 생성되었는지 테스트
	@Test
	public void testBeanCreation() {
		assertNotNull(bookRepo);
		assertNotNull(bookService);
		assertNotNull(userRepo);
		assertNotNull(userService);
	}
	
	// 2. 컨텍스트에서 직접 얻어온 BookRepo와 BookService가 가지는 BookRepo가 동일한지 테스트
	@Test
	public void testSingleton() {
		// AOP => Proxy 거쳐서 제공하는 서비스로 바뀌었기 때문에 타입이 바뀌어서 다른 객체로 나옴
		// 외부에서는 Proxy 타입으로 접근하도록 바뀜 => 둘이 다른 객체가 되어버림 (bookRepo, bookService)
//		logger.debug("boorRepo: {}", bookRepo.getClass().getName());
//		
//		logger.debug("bookService 클래스 이름: {}", bookService.getClass().getName());
//		
//		logger.debug("bookService의 bookRepo 클래스 이름: {}", bookService.getBookRepo().getClass().getName());

		assertEquals(bookRepo, ((BookServiceImpl) bookService).getBookRepo());
	}
	
	// 3. DataSource 객체가 잘 생성되었는지 테스트하고 출력
	@Test
	public void testDataSource() {
		logger.debug("datasource 확인: {}", dataSource);
		assertNotNull(dataSource);
	}

}

