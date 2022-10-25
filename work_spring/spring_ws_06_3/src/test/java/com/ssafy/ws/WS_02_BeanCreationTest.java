package com.ssafy.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.dao.BookDao;
import com.ssafy.ws.model.dao.UserDao;
import com.ssafy.ws.model.service.BookService;
import com.ssafy.ws.model.service.UserService;

public class WS_02_BeanCreationTest extends AbstractTest {
	
	// 로깅 처리를 위해 Logger를 추가
	private static Logger logger = LoggerFactory.getLogger(WS_02_BeanCreationTest.class);
	
	// 필요한 빈들을 @Autowired를 이용하여 주입 받기
	@Autowired
	private BookDao bookRepo;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserDao userRepo;
	
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
		logger.debug("bookRepo: {}", bookRepo);
		
		logger.debug("bookService {}", bookService);
		logger.debug("bookService 클래스 이름 {}", bookService.getClass().getName());
		logger.debug("bookService의 bookRepo 클래스 이름 {}", bookService.getBookRepo().getClass().getName());
		
		// 타입이 변경(BookService -> Proxy) 되었기 때문에 아래 코드는 테스트 통과를 못함
		// bookService 객체는 현재 Proxy 타입이라 BookServiceImpl 타입으로 강제 형변환이 되지 않아
		// 형변환 예외가 발생
		//assertEquals(bookRepo, ((BookServiceImpl) bookService).getBookRepo());
		
		// 타입은 변경되었지만 본질(해당 객체가 메모리에 올라간 내용)은 변경되지 않았습니다.
		assertEquals(bookRepo, bookService.getBookRepo());
	}
	
	// 3. DataSource 객체가 잘 생성되었는지 테스트하고 출력
	@Test
	public void testDataSource() {
		logger.debug("datasource 확인: {}", dataSource);
		assertNotNull(dataSource);
	}

}
