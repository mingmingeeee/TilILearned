package com.ssafy.house.model;

public class HouseDto {
	private String no; // primary key
	private String dealAmount; // 거래금액(만원)
	private String dealYear;   // 계약년도
	private String dealMonth;  // 계약월
	private String dealDay;    // 계약일
	private String area;       // 전용면적(m^2)
	private String floor;      // 층
	private String cancelDealType; // 해제여부
	private String aptCode;
	private String buildYear;
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String roadNameSeq;
	private String roadNameBasementCode;
	private String roadNameCode;
	private String dong;
	private String bonbun;
	private String bubun;
	private String sigunguCode;
	private String eubmyundongCode;
	private String dongCode;
	private String landCode;
	private String apartmentName;
	private String jibun;
	private String lng;
	private String lat;
	private int optionCount;

	public HouseDto() {
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}

	public String getDealYear() {
		return dealYear;
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear;
	}

	public String getDealMonth() {
		return dealMonth;
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth;
	}

	public String getDealDay() {
		return dealDay;
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getCancelDealType() {
		return cancelDealType;
	}

	public void setCancelDealType(String cancelDealType) {
		this.cancelDealType = cancelDealType;
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
		builder.append("HouseDto [no=");
		builder.append(no);
		builder.append(", dealAmount=");
		builder.append(dealAmount);
		builder.append(", dealYear=");
		builder.append(dealYear);
		builder.append(", dealMonth=");
		builder.append(dealMonth);
		builder.append(", dealDay=");
		builder.append(dealDay);
		builder.append(", area=");
		builder.append(area);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", cancelDealType=");
		builder.append(cancelDealType);
		builder.append(", aptCode=");
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
	}

	public int getOptionCount() {
		return optionCount;
	}

	public void setOptionCount(int optionCount) {
		this.optionCount = optionCount;
	};

}
