package com.ssafy.publishing.book;

public interface BookMgr {
	void add(Book v) throws DuplicatedException;
	Book[] search();
	Book search(String name) throws NotFoundException;
}