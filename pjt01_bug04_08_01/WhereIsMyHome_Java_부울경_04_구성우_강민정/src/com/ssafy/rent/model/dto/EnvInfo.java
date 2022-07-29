package com.ssafy.rent.model.dto;

public class EnvInfo {

	// 업체 명
	private String name;

	// 지도점검일자
	private String date;

	// 점검사항
	private String content;

	// 점검결과
	private String result;

	// 소재지주소
	private String dong;

	public EnvInfo() {

	}

	public EnvInfo(String name, String date, String content, String result, String dong) {
		super();
		this.name = name;
		this.date = date;
		this.content = content;
		this.result = result;
		this.dong = dong;
	}

	@Override
	public String toString() {
		return "EnvInfo [name=" + name + ", date=" + date + ", content=" + content + ", result=" + result + ", dong="
				+ dong + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

//업체(시설)명,지도점검일자,점검사항,점검결과,소재지주소

}
