package com.ssafy.publishing.book;

import java.util.Arrays;

public class BookMgrImpl implements BookMgr {
	private Book[] books;
	private int size;
	
	public BookMgrImpl() {
		books=new Book[100];		
	}
	
	@Override
	public void add(Book s) throws DuplicatedException{
		try {
			search(s.getTitle());
			new DuplicatedException(s.getTitle()+": 등록된 도서입니다.");
		} catch (NotFoundException e) {
			books[size++]=s;
		}
	}
	@Override
	public Book[] search() {
		return Arrays.copyOf(books,size);
	}
	@Override
	public Book search(String name) throws NotFoundException{
		for(int i=0; i<books.length; i++) {
			if(books[i].getTitle().equals(name)) return books[i];
		}
		throw new NotFoundException(name+": 미등록 도서입니다.");
	}
}
