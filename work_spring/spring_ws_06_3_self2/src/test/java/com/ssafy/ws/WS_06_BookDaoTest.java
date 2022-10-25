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

public class WS_06_BookDaoTest extends AbstractTest{
	
	private static final Logger logger = LoggerFactory.getLogger(WS_06_BookDaoTest.class);
	
	@Autowired
	private BookDao bookDao;
	
	private Book book1;
	private Book book2;
	private Book book3;
	
	@Before
	public void setUp() {
		logger.debug("unit test 시작: ");
		this.book1 = new Book("111-222-3333", "홍길동", "책제목1", 10000, "좋은 책 1", null, "abc1.png");
		this.book2 = new Book("111-222-4444", "임꺽정", "책제목2", 20000, "좋은 책 2", null, "abc2.png");
		this.book3 = new Book("111-333-4444", "장길산", "책제목3", 30000, "좋은 책 3", null, "abc3.png");
	}
	
	@Test
	public void addAndGet() {
		
		bookDao.deleteAll();
		assertEquals(0, bookDao.getCount());
		
		assertEquals(1, bookDao.insert(book1));
		assertEquals(1, bookDao.insert(book2));
		assertEquals(2, bookDao.getCount());
		
		Book bookget1 = bookDao.select(book1.getIsbn());
		
		assertEquals(book1.getTitle(), bookget1.getTitle());
		assertEquals(book1.getPrice(), bookget1.getPrice());
		assertEquals(book1.getOrgImg(), bookget1.getOrgImg());
		
		Book bookget2 = bookDao.select(book2.getIsbn());

		assertEquals(book2.getTitle(), bookget2.getTitle());
		assertEquals(book2.getPrice(), bookget2.getPrice());
		assertEquals(book2.getOrgImg(), bookget2.getOrgImg());
		
	}
	
	@Test
	public void count() {
		
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
	public void addAndUpdate() {
		
		bookDao.deleteAll();
		assertEquals(0, bookDao.getCount());
		
		assertEquals(1, bookDao.insert(book1));
		assertEquals(1, bookDao.getCount());

		book1.setPrice(15000);
		
		assertEquals(1, bookDao.update(book1));
		
		Book bookget1 = bookDao.select(book1.getIsbn());
		assertEquals(book1.getTitle(), bookget1.getTitle());
		assertEquals(book1.getPrice(), bookget1.getPrice());
		assertEquals(book1.getOrgImg(), bookget1.getOrgImg());
		
	}
	
	@Test
	public void addAndDelete() {
		
		bookDao.deleteAll();
		assertEquals(0, bookDao.getCount());
		
		assertEquals(1, bookDao.insert(book1));
		assertEquals(1, bookDao.getCount());

		assertEquals(1, bookDao.delete(book1.getIsbn()));
		
		Book bookget1 = bookDao.select(book1.getIsbn());
		assertNull(bookget1);
		
	}
	
	@Test
	public void selectAll() {
		bookDao.deleteAll();
		assertEquals(0, bookDao.getCount());
		
		assertEquals(1, bookDao.insert(book1));
		assertEquals(1, bookDao.insert(book2));
		assertEquals(1, bookDao.insert(book3));
		assertEquals(3, bookDao.getCount());

		List<Book> books = bookDao.search();
		assertEquals(3, books.size());
	}

}
