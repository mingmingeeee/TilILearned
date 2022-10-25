package com.ssafy.ws;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssafy.ws.model.dao.BookDao;
import com.ssafy.ws.model.dao.UserDao;
import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.SearchCondition;

public class WS_07_DynamicQueryTest extends AbstractTest {
	private static final Logger logger = LoggerFactory.getLogger(WS_07_DynamicQueryTest.class);

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UserDao userDao;
	
	private Book book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, 
		book11, book12, book13, book14, book15, book16, book17, book18, book19, book20;
	
	private Book[] books;

	@Before // JUnit이 제공하는 애노테이션. @Test 메소드가 실행되기 전에 먼저 실행되어야 하는 메소드를 정의한다.
	public void setUp() {
		
		this.book1 = new Book("111-222-1111", "안락한 싸피생활","일무국", 800,"안락한 싸피 생활 가이드","표지경로 01", null);
		this.book2 = new Book("111-222-2222", "즐거운 싸피생활","일무국", 900,"즐거운 싸피 생활 가이드","표지경로 02", null);
		this.book3 = new Book("111-222-3333", "행복한 싸피생활","일무국", 1200,"행복한 싸피 생활 지침서","표지경로 03", null);
		this.book4 = new Book("111-222-4444", "중요한 싸피생활","일무국", 9400,"중요한 싸피 생활 지침서","표지경로 04", null);
		this.book5 = new Book("111-222-5555", "유용한 싸피생활","일무국", 3520,"유용한 싸피 생활 지침서","표지경로 05", null);
		this.book6 = new Book("111-222-6666", "필요한 싸피생활","이무국",8500,"필요한 싸피 생활 지침서","표지경로 06", null);
		this.book7 = new Book("111-222-7777", "신나는 싸피생활","이무국", 450,"신나는 싸피 생활 지침서","표지경로 07", null);
		this.book8 = new Book("111-222-8888", "유익한 싸피생활","이무국", 850,"유익한 싸피 생활 지침서","표지경로 08", null);
		this.book9 = new Book("111-222-9999", "재밋는 싸피생활","이무국", 450,"재밋는 싸피 생활 지침서","표지경로 09", null);
		this.book10 = new Book("111-222-0000", "안전한 싸피생활","이무국", 780,"안전한 싸피 생활 지침서","표지경로 10", null);                       
		this.book11 = new Book("111-000-0001", "도서명 11","삼무국", 1500,"도서 내용 11","표지경로 11", null);
		this.book12 = new Book("111-000-0002", "도서명 12","삼무국", 2000,"도서 내용 12","표지경로 12", null);
		this.book13 = new Book("111-000-0003", "도서명 13","삼무국", 3000,"도서 내용 13","표지경로 13", null);
		this.book14 = new Book("111-000-0004", "도서명 14","삼무국", 3500,"도서 내용 14","표지경로 14", null);
		this.book15 = new Book("111-000-0005", "도서명 15","삼무국", 2500,"도서 내용 15","표지경로 15", null);
		this.book16 = new Book("111-000-0006", "도서명 16","사무국", 1800,"도서 내용 16","표지경로 16", null);
		this.book17 = new Book("111-000-0007", "도서명 17","사무국", 1900,"도서 내용 17","표지경로 17", null);
		this.book18 = new Book("111-000-0008", "도서명 18","사무국", 3500,"도서 내용 18","표지경로 18", null);
		this.book19 = new Book("111-000-0009", "도서명 19","사무국", 4700,"도서 내용 19","표지경로 19", null);
		this.book20 = new Book("111-000-0010", "도서명 20","사무국", 3300,"도서 내용 20","표지경로 20", null);
		
		books = new Book[] { book1, book2, book3, book4, book5, book6, book7, book8, book9, book10, 
				book11, book12, book13, book14, book15, book16, book17, book18, book19, book20 };
		
	}

	@Test
	public void testByNone() {
		
		bookDao.deleteAll();
		assertEquals(bookDao.getCount(), 0);
		
		for(Book book : books) {
			assertEquals(bookDao.insert(book), 1);
		}
		assertEquals(bookDao.getCount(), 20);
		
		SearchCondition condition = new SearchCondition();
		condition.setLimit(false);
		List<Book> selected = bookDao.search(condition);
		
		assertEquals(selected.size(), 20);
		
		condition.setLimit(true);
		selected = bookDao.search(condition);
		
		assertEquals(selected.size(), 10);
		
	}
	
	@Test
	public void testByAuthor() {
		
		bookDao.deleteAll();
		assertEquals(bookDao.getCount(), 0);
		
		for(Book book : books) {
			assertEquals(bookDao.insert(book), 1);
		}
		
		assertEquals(bookDao.getCount(), 20);
		
		SearchCondition condition = new SearchCondition("author", "일무국");
		List<Book> selected = bookDao.search(condition);
		
		assertEquals(selected.size(), 5);
		
	}
	
	@Test
	public void testByTitle() {
		
		bookDao.deleteAll();
		assertEquals(bookDao.getCount(), 0);
		
		for(Book book : books) {
			assertEquals(bookDao.insert(book), 1);
		}
		assertEquals(bookDao.getCount(), 20);
		
		SearchCondition condition = new SearchCondition("title", "싸피", "isbn", "desc");
		List<Book> selected = bookDao.search(condition);
		
		assertEquals(selected.size(), 10);
		assertEquals(selected.get(0).getIsbn(), "111-222-9999");
		
	}
	
	@Test
	public void testByContent() {
		bookDao.deleteAll();
		assertEquals(bookDao.getCount(), 0);

		for (Book book : books) {
			assertEquals(bookDao.insert(book), 1);
		}
		assertEquals(bookDao.getCount(), 20);
		
		SearchCondition nav = new SearchCondition("content", " ", "isbn", "desc", 1);
		List<Book> selected = bookDao.search(nav);
		assertEquals(selected.get(0).getIsbn(), "111-222-9999");
		assertEquals(selected.size(), 10);

	}
	
}
