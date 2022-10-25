package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.dao.BookDao;
import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.SearchCondition;

public interface BookService {
	
	int insert(Book book);
	
	int update(Book book);
	
	int delete(String isbn);
	
	Book select(String isbn);
	
	List<Book> search(SearchCondition condition);
	
	BookDao getBookRepo();

	void deleteAll();

	int getCount();
	
}
