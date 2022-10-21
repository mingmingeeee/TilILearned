package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.dto.Book;
import com.ssafy.ws.model.repo.BookRepo;

public interface BookService {
	
	int insert(Book book);
	
	int update(Book book);
	
	int delete(String isbn);
	
	Book select(String isbn);
	
	List<Book> search();
	
	BookRepo getBookRepo();
	
}
