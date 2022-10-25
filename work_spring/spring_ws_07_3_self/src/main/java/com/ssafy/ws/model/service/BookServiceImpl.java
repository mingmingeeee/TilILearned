package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ws.model.dao.BookDao;
import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.SearchCondition;

@Service
public class BookServiceImpl implements BookService {
	
	private BookDao bookDao;
	
	public BookServiceImpl() {}
	
	public BookDao getBookRepo() {
		return bookDao;
	}

	@Autowired
	public void setBookRepo(BookDao repo) {
		this.bookDao = repo;
	}

	@Override
	public int insert(Book book) {
		// TODO Auto-generated method stub
		return bookDao.insert(book);
	}

	@Override
	public int update(Book book) {
		// TODO Auto-generated method stub
		return bookDao.update(book);
	}

	@Override
	public int delete(String isbn) {
		// TODO Auto-generated method stub
		return bookDao.delete(isbn);
	}

	@Override
	public Book select(String isbn) {
		// TODO Auto-generated method stub
		return bookDao.select(isbn);
	}

	@Override
	public List<Book> search(SearchCondition condition) {
		// TODO Auto-generated method stub
		return bookDao.search(condition);
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		bookDao.deleteAll();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return bookDao.getCount();
	}

}
