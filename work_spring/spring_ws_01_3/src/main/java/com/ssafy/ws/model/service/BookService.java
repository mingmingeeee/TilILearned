package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.dto.Book;

public interface BookService {
	
	int insert(Book book);
	
	int update(Book book);
	
	int delete(Book book);
	
	Book select(String isbn);
	
	List<Book> search();

}
