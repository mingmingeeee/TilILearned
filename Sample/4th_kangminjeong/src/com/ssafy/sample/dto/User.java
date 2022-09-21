package com.ssafy.sample.dto;

public class User {
	
	private String userid;
	private String userpass;
	
	public User() {}
	
	public User(String userid, String userpass) {
		super();
		this.userid = userid;
		this.userpass = userpass;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userid=");
		builder.append(userid);
		builder.append(", userpass=");
		builder.append(userpass);
		builder.append("]");
		return builder.toString();
	}
	
	

}
