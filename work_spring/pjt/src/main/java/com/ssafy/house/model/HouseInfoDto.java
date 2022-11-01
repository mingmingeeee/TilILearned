package com.ssafy.house.model;

public class HouseInfoDto {
	private String aptCode;   // primary key
	private String buildYear; // 건축년도
	private String roadName;  // 도로명
	private String roadNameBonbun; // 도로명건물본번호코드
	private String roadNameBubun;  // 도로명건물부번호코드
	private String roadNameSeq;    // 도로명일련번호코드
	private String roadNameBasementCode; // 도로명지상지하코드
	private String roadNameCode;   // 도로명코드
	private String dong;           // 법정동
	private String bonbun;         // 법정동본번코드
	private String bubun;          // 법정동부번코드
	private String sigunguCode;    // 법정동시군구코드
	private String eubmyundongCode;// 법정동읍면동코드
	private String dongCode;
	private String landCode;       // 법정동지번코드
	private String apartmentName;  // 아파트
	private String jibun;          // 지번
	private String lng;  // 경도
	private String lat;  // 위도

	public HouseInfoDto() {
	}

	public HouseInfoDto(String aptCode, String buildYear, String roadName, String roadNameBonbun, String roadNameBubun,
			String roadNameSeq, String roadNameBasementCode, String roadNameCode, String dong, String bonbun,
			String bubun, String sigunguCode, String eubmyundongCode, String dongCode, String landCode,
			String apartmentName, String jibun, String lng, String lat) {
		super();
		this.aptCode = aptCode;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.roadNameBonbun = roadNameBonbun;
		this.roadNameBubun = roadNameBubun;
		this.roadNameSeq = roadNameSeq;
		this.roadNameBasementCode = roadNameBasementCode;
		this.roadNameCode = roadNameCode;
		this.dong = dong;
		this.bonbun = bonbun;
		this.bubun = bubun;
		this.sigunguCode = sigunguCode;
		this.eubmyundongCode = eubmyundongCode;
		this.dongCode = dongCode;
		this.landCode = landCode;
		this.apartmentName = apartmentName;
		this.jibun = jibun;
		this.lng = lng;
		this.lat = lat;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}

	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun;
	}

	public String getRoadNameBubun() {
		return roadNameBubun;
	}

	public void setRoadNameBubun(String roadNameBubun) {
		this.roadNameBubun = roadNameBubun;
	}

	public String getRoadNameSeq() {
		return roadNameSeq;
	}

	public void setRoadNameSeq(String roadNameSeq) {
		this.roadNameSeq = roadNameSeq;
	}

	public String getRoadNameBasementCode() {
		return roadNameBasementCode;
	}

	public void setRoadNameBasementCode(String roadNameBasementCode) {
		this.roadNameBasementCode = roadNameBasementCode;
	}

	public String getRoadNameCode() {
		return roadNameCode;
	}

	public void setRoadNameCode(String roadNameCode) {
		this.roadNameCode = roadNameCode;
	}

	public String getDong() {
		return dong;
	}

	public void setDong(String dong) {
		this.dong = dong;
	}

	public String getBonbun() {
		return bonbun;
	}

	public void setBonbun(String bonbun) {
		this.bonbun = bonbun;
	}

	public String getBubun() {
		return bubun;
	}

	public void setBubun(String bubun) {
		this.bubun = bubun;
	}

	public String getSigunguCode() {
		return sigunguCode;
	}

	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode;
	}

	public String getEubmyundongCode() {
		return eubmyundongCode;
	}

	public void setEubmyundongCode(String eubmyundongCode) {
		this.eubmyundongCode = eubmyundongCode;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getLandCode() {
		return landCode;
	}

	public void setLandCode(String landCode) {
		this.landCode = landCode;
	}

	public String getApartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}

	public String getJibun() {
		return jibun;
	}

	public void setJibun(String jibun) {
		this.jibun = jibun;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HouseInfoDto [aptCode=");
		builder.append(aptCode);
		builder.append(", buildYear=");
		builder.append(buildYear);
		builder.append(", roadName=");
		builder.append(roadName);
		builder.append(", roadNameBonbun=");
		builder.append(roadNameBonbun);
		builder.append(", roadNameBubun=");
		builder.append(roadNameBubun);
		builder.append(", roadNameSeq=");
		builder.append(roadNameSeq);
		builder.append(", roadNameBasementCode=");
		builder.append(roadNameBasementCode);
		builder.append(", roadNameCode=");
		builder.append(roadNameCode);
		builder.append(", dong=");
		builder.append(dong);
		builder.append(", bonbun=");
		builder.append(bonbun);
		builder.append(", bubun=");
		builder.append(bubun);
		builder.append(", sigunguCode=");
		builder.append(sigunguCode);
		builder.append(", eubmyundongCode=");
		builder.append(eubmyundongCode);
		builder.append(", dongCode=");
		builder.append(dongCode);
		builder.append(", landCode=");
		builder.append(landCode);
		builder.append(", apartmentName=");
		builder.append(apartmentName);
		builder.append(", jibun=");
		builder.append(jibun);
		builder.append(", lng=");
		builder.append(lng);
		builder.append(", lat=");
		builder.append(lat);
		builder.append("]");
		return builder.toString();
	};

}
