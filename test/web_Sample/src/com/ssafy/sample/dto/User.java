package com.ssafy.sample.dto;


// DTO
public class User {
	
	private String id;
	private String pass;
	
	public User() {}
	
	public User(String id, String pass) {
		super();
		this.id = id;
		this.pass = pass;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pass=" + pass + "]";
	}

}
