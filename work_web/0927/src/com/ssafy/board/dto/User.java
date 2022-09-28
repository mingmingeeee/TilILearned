package com.ssafy.board.dto;

public class User {
	
	private String id;
	private String pw;
	private Boolean isActive;
	
	// 1. 기본 생성자
	public User() {}

	// 2. 모든 필드 초기화 생성자
	public User(String id, String pw, Boolean isActive) {
		super();
		this.id = id;
		this.pw = pw;
		this.isActive = isActive;
	}

	// 3. getter, setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", pw=");
		builder.append(pw);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append("]");
		return builder.toString();
	}
	
}
