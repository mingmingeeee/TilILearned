package com.ssafy.publishing.book;

public class DuplicatedException extends Exception{
	public DuplicatedException(String msg) {
		super(msg);
	}
	public DuplicatedException() {
		this("Duplicated예외");
	}
}
