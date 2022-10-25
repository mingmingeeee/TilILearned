package com.ssafy.ws.model.dao;

import java.util.List;

import com.ssafy.ws.model.dto.Book;
import com.ssafy.ws.model.dto.SearchCondition;

public interface BookDao {
	
	int insert(Book book);
	
	int update(Book book);
	
	int delete(String isbn);
	
	Book select(String isbn);
	
	List<Book> search(SearchCondition condition);

	void deleteAll();

	int getCount();
	
}
