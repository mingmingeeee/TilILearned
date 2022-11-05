package com.ssafy.house.model.dto;

public class FavArea {
	private String userId;
	private String dongCode;
	private Integer no;
	public FavArea() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FavArea(String userId, String dongCode, Integer no) {
		super();
		this.userId = userId;
		this.dongCode = dongCode;
		this.no = no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	
}
