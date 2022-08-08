package com.ssafy.publishing.book;

public class Book {
	private String title;  // 도서명
	private int page;  // 페이지 수

	public Book() {}
	public Book(String title, int page) {
		setTitle(title);
		setPage(page);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = name;
	}

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(getTitle()).append("\t")
		  .append(getPage());
		return sb.toString();
	}
}
