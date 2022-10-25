package com.ssafy.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.SearchCondition;
import com.ssafy.ws.model.service.BookService;

/*
 * 현재 아래의 Test들은 WS_06_DaoTest가 모두 통과되었다는 가정 하에 진행되는 테스트 코드이다.
 * 정확하게 Service 단위의 테스트를 진행하기 위해서는 Mockup을 활용해야 한다.
 */
public class WS_07_BookServiceTest extends AbstractTest {
	
	private static final Logger logger = LoggerFactory.getLogger(WS_07_BookServiceTest.class);
	
	// 픽스처
	@Autowired
	private BookService bookService;
	
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
		
		bookService.deleteAll();
		assertEquals(0, bookService.getCount());
		
		// 1. 도서 추가 테스트
		assertEquals(1, bookService.insert(book1));
		assertEquals(1, bookService.insert(book2));
		assertEquals(2, bookService.getCount());
		
		// 2. 도서 조회 테스트
		Book bookget1 = bookService.select(book1.getIsbn());
		
		// 대표적으로 도서 제목과 가격으로 비교
		assertEquals(book1.getTitle(), bookget1.getTitle());
		assertEquals(book1.getPrice(), bookget1.getPrice());
		assertEquals(book1.getOrgImg(), bookget1.getOrgImg());
		
		Book bookget2 = bookService.select(book2.getIsbn());
		
		// 대표적으로 도서 제목과 가격으로 비교
		assertEquals(book2.getTitle(), bookget2.getTitle());
		assertEquals(book2.getPrice(), bookget2.getPrice());
		assertEquals(book2.getOrgImg(), bookget2.getOrgImg());
	}

	@Test
	public void count() {  // count 메서드(추가예정) 테스트 
		
		bookService.deleteAll();
		assertEquals(0, bookService.getCount());
		
		assertEquals(1, bookService.insert(book1));
		assertEquals(1, bookService.getCount());
		
		assertEquals(1, bookService.insert(book2));
		assertEquals(2, bookService.getCount());
		
		assertEquals(1, bookService.insert(book3));
		assertEquals(3, bookService.getCount());
	}
	
	@Test
	public void addAndUpdate() {  // 추가, 수정 테스트
		
		bookService.deleteAll();
		assertEquals(0, bookService.getCount());
		
		// 1. 도서 추가 테스트
		assertEquals(1, bookService.insert(book1));
		assertEquals(1, bookService.getCount());
		
		// 가격 변경
		book1.setPrice(15000);
		
		// 2. 도서 수정 테스트
		assertEquals(1, bookService.update(book1));
		
		// 3. 도서 조회 테스트
		Book bookget1 = bookService.select(book1.getIsbn());
		assertEquals(book1.getTitle(), bookget1.getTitle());
		assertEquals(book1.getPrice(), bookget1.getPrice());
		assertEquals(book1.getOrgImg(), bookget1.getOrgImg());
	}
	
	@Test
	public void addAndDelete() {  // 추가, 삭제 테스트
		
		bookService.deleteAll();
		assertEquals(0, bookService.getCount());
		
		// 1. 도서 추가 테스트
		assertEquals(1, bookService.insert(book1));
		assertEquals(1, bookService.getCount());
		
		// 2. 도서 삭제 테스트
		assertEquals(1, bookService.delete(book1.getIsbn()));
		
		// 3. 도서 조회 테스트
		Book bookget1 = bookService.select(book1.getIsbn());
		assertNull(bookget1);
	}
	
	@Test
	public void selectAll() {  // 모든 도서 조회 테스트
		
		bookService.deleteAll();
		assertEquals(0, bookService.getCount());
		
		// 1. 도서 3권 추가
		assertEquals(1, bookService.insert(book1));
		assertEquals(1, bookService.insert(book2));
		assertEquals(1, bookService.insert(book3));
		assertEquals(3, bookService.getCount());
		
		// 2. 모든 도서 조회 테스트
		List<Book> books = bookService.search(new SearchCondition());
		assertEquals(3, books.size());
	}
}
