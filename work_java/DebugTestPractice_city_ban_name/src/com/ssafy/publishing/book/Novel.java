package com.ssafy.publishing.book;

public class Novel extends Book {
	
	public String author;  // 작가명
	
	public Novel(){}
	
	public Novel(String title, int page, String author) {
		super(title, page);
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		author = author;
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(super.toString()).append("\t")
		  .append(getAuthor());
		return sb.toString();
	}

}
