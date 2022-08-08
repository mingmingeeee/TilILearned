package com.ssafy.publishing.person;

public class Author extends Person {
	private String nickname;  // 필명
	private String ssn;  // 주민번호
	private boolean isActive;  // 활동여부
	
	public Author() {}
	public Author(String name, int age, String phone, String nickname, String ssn, boolean isActive) {
		super(name, age, phone);
		setNickname(nickname);
		setSsn(ssn);
		setActive(isActive);
	}
	
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(super.toString()).append("\t")
		  .append(getNickname()).append("\t")
		  .append(getSsn()).append("\t")
		  .append(isActive());
		return sb.toString();
	}
}