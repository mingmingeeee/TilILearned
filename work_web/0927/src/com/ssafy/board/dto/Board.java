package com.ssafy.board.dto;

public class Board {
	
	// 글 번호
	private int no;
	
	// 글 제목
	private String title;
	
	// 글 내용
	private String content;
	
	// 작성자 id
	private String userId;
	
	// 글 작성날짜
	private long createdAt;
	
	public Board() {}

	public Board(int no, String title, String content, String userId, long createdAt) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.createdAt = createdAt;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Board [no=");
		builder.append(no);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append("]");
		return builder.toString();
	}
	
}
