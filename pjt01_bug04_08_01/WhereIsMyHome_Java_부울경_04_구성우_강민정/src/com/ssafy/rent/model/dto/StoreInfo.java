package com.ssafy.rent.model.dto;

public class StoreInfo {

	// 상가 업소번호
	private int no;

	// 상호명
	private String StoreName;

	// 상권 업종 분류명
	private String classifyName;

	// 시군구코드
	private String code;

	// 법정동명
	private String dong;

	// 지번주소
	private String address;

	public StoreInfo() {

	}
	

	public StoreInfo(int no, String storeName, String classifyName, String code, String dong, String address) {
		super();
		this.no = no;
		StoreName = storeName;
		this.classifyName = classifyName;
		this.code = code;
		this.dong = dong;
		this.address = address;
	}



	public StoreInfo(int no) {
		super();
		this.no = no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getStoreName() {
		return StoreName;
	}

	public void setStoreName(String storeName) {
		StoreName = storeName;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "StoreInfo [no=" + no + ", StoreName=" + StoreName + ", classifyName=" + classifyName + ", code=" + code
				+ ", dong=" + dong + ", address=" + address + "]";
	}
	
	

}
