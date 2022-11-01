package com.ssafy.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.dao.BookDao;
import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.SearchCondition;

public class WS_06_BookDaoTest extends AbstractTest {
	
	private static final Logger logger = LoggerFactory.getLogger(WS_06_BookDaoTest.class);
	
	// 픽스처(fixture): 테스트를 수행하는데 필요한 정보나 객체
	@Autowired
	private BookDao bookDao;
	
	private Book book1;
	private Book book2;
	private Book book3;
	// 픽스처 끝
	
	@Before
	public void setUp() {
		
		this.book1 = new Book("111-222-3333", "홍길동", "책제목1", 10000, "좋은 책 1", null, "abc1.png");
		this.book2 = new Book("111-222-4444", "임꺽정", "책제목2", 20000, "좋은 책 2", null, "abc2.png");
		this.book3 = new Book("111-333-4444", "장길산", "책제목3", 30000, "좋은 책 3", null, "abc3.png");
		
	}

	@Test
	public void addAndGet() {  // 추가, 조회 테스트
		
		bookDao.deleteAll();
		assertEquals(0, bookDao.getCount());
		
		// 1. 도서 추가 테스트
		assertEquals(1, bookDao.insert(book1));
		assertEquals(1, bookDao.insert(book2));
		assertEquals(2, bookDao.getCount());
		
		// 2. 도서 조회 테스트
		Book bookget1 = bookDao.select(book1.getIsbn());
		
		// 대표적으로 도서 제목과 가격으로 비교
		assertEquals(book1.getTitle(), bookget1.getTitle());
		assertEquals(book1.getPrice(), bookget1.getPrice());
		assertEquals(book1.getOrgImg(), bookget1.getOrgImg());
		
		Book bookget2 = bookDao.select(book2.getIsbn());
		
		// 대표적으로 도서 제목과 가격으로 비교
		assertEquals(book2.getTitle(), bookget2.getTitle());
		assertEquals(book2.getPrice(), bookget2.getPrice());
		assertEquals(book2.getOrgImg(), bookget2.getOrgImg());
	}
	
	@Test
	public void count() {  // count 메서드(추가예정) 테스트 
		
		bookDao.deleteAll();
		assertEquals(0, bookDao.getCount());
		
		assertEquals(1, bookDao.insert(book1));
		assertEquals(1, bookDao.getCount());
		
		assertEquals(1, bookDao.insert(book2));
		assertEquals(2, bookDao.getCount());
		
		assertEquals(1, bookDao.insert(book3));
		assertEquals(3, bookDao.getCount());
	}
	
	@Test
	public void addAndUpdate() {  // 추가, 수정 테스트
		
		bookDao.deleteAll();
		assertEquals(0, bookDao.getCount());
		
		// 1. 도서 추가 테스트
		assertEquals(1, bookDao.insert(book1));
		assertEquals(1, bookDao.getCount());
		
		// 가격 변경
		book1.setPrice(15000);
		
		// 2. 도서 수정 테스트
		assertEquals(1, bookDao.update(book1));
		
		// 3. 도서 조회 테스트
		Book bookget1 = bookDao.select(book1.getIsbn());
		assertEquals(book1.getTitle(), bookget1.getTitle());
		assertEquals(book1.getPrice(), bookget1.getPrice());
		assertEquals(book1.getOrgImg(), bookget1.getOrgImg());
	}
	
	@Test
	public void addAndDelete() {  // 추가, 삭제 테스트
		
		bookDao.deleteAll();
		assertEquals(0, bookDao.getCount());
		
		// 1. 도서 추가 테스트
		assertEquals(1, bookDao.insert(book1));
		assertEquals(1, bookDao.getCount());
		
		// 2. 도서 삭제 테스트
		assertEquals(1, bookDao.delete(book1.getIsbn()));
		
		// 3. 도서 조회 테스트
		Book bookget1 = bookDao.select(book1.getIsbn());
		assertNull(bookget1);
	}
	
	@Test
	public void selectAll() {  // 모든 도서 조회 테스트
		
		bookDao.deleteAll();
		assertEquals(0, bookDao.getCount());
		
		// 1. 도서 3권 추가
		assertEquals(1, bookDao.insert(book1));
		assertEquals(1, bookDao.insert(book2));
		assertEquals(1, bookDao.insert(book3));
		assertEquals(3, bookDao.getCount());
		
		// 2. 모든 도서 조회 테스트
		List<Book> books = bookDao.search(new SearchCondition());
		assertEquals(3, books.size());
	}
}
