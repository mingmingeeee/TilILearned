package com.ssafy.ws.model.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.SearchCondition;

public interface BookService {
	
	int insert(Book book) throws IllegalStateException, IOException;
	
	int update(Book book);
	
	int delete(String isbn);
	
	Book select(String isbn);
	
	List<Book> search(SearchCondition condition);

	void deleteAll();

	int getCount();
	
}
