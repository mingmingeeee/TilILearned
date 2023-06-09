package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.dao.BookDao;
import com.ssafy.ws.model.dto.Book;

public interface BookService {
	
	int insert(Book book);
	
	int update(Book book);
	
	int delete(String isbn);
	
	Book select(String isbn);
	
	List<Book> search();
	
	BookDao getBookRepo();
	
}
