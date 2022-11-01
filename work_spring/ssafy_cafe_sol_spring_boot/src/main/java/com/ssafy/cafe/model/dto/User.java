package com.ssafy.cafe.model.dto;

public class User {

	private String id;
	private String name;
	private String pass;
	private Integer stamps;
	
	public User() {}
	
	public User(String id, String name, String pass, Integer stamps) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.stamps = stamps;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getStamps() {
		return stamps;
	}

	public void setStamps(Integer stamps) {
		this.stamps = stamps;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", pass=");
		builder.append(pass);
		builder.append(", stamps=");
		builder.append(stamps);
		builder.append("]");
		return builder.toString();
	}
	
}
