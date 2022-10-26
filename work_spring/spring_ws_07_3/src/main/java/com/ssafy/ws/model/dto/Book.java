package com.ssafy.ws.model.dto;

import org.springframework.web.multipart.MultipartFile;

public class Book {
	
	private String isbn;
	private String title;
	private String author;
	private Integer price;
	private String content;
	private String img;
	private String orgImg;
	private MultipartFile upfile;
	
	public Book() {}

	public Book(String isbn, String title, String author, Integer price, String content, String img, String orgImg, MultipartFile upfile) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.content = content;
		this.img = img;
		this.orgImg = orgImg;
		this.upfile = upfile;
	}
	
	

	public Book(String isbn, String title, String author, Integer price, String content, String img, String orgImg) {
		this(isbn, title, author, price, content, img, orgImg, null);
	}

	public String getIsbn() {
		return isbn;
	}

	public MultipartFile getUpfile() {
		return upfile;
	}

	public void setUpfile(MultipartFile upfile) {
		this.upfile = upfile;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public String getOrgImg() {
		return orgImg;
	}

	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [isbn=");
		builder.append(isbn);
		builder.append(", title=");
		builder.append(title);
		builder.append(", author=");
		builder.append(author);
		builder.append(", price=");
		builder.append(price);
		builder.append(", content=");
		builder.append(content);
		builder.append(", img=");
		builder.append(img);
		builder.append(", orgImg=");
		builder.append(orgImg);
		builder.append(", upfile=");
		builder.append(upfile);
		builder.append("]");
		return builder.toString();
	}

}
