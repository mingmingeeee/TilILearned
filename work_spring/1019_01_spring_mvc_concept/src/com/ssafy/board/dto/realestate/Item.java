package com.ssafy.board.dto.realestate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Item {
	// 시작: 연립다세대 매매 실거래 자료
	private String landArea;
	private String houseName;
	// 끝: 연립다세대 매매 실거래 자료
	private String dealAmount;
	private String reqGbn;
	private String buildYear;
	private String dealYear;
	private String roadName;
	private String roadNameBonbun;
	private String roadNameBubun;
	private String roadNameSigunguCode;
	private String roadNameSeq;
	private String roadNameBasementCode;
	private String roadNameCode;
	private String dong;
	private String bonbun;
	private String bubun;
	private String sigunguCode;
	private String eubmyundongCode;
	private String landCode;
	private String apartmentName;
	private String dealMonth;
	private String dealDay;
	private String no;
	private String area;
	private String rDealerLawdnm;
	private String jibun;
	private String regionalCode;
	private String floor;
	private String cancelDealDay;
	private String cancelDealType;
	
	// 시작: 연립다세대 매매 실거래 자료
	@XmlElement(name = "대지권면적")		
	public String getLandArea() {
		return landArea;
	}

	@XmlElement(name = "연립다세대")
	public String getHouseName() {
		return houseName;
	}
	// 끝: 연립다세대 매매 실거래 자료

	@XmlElement(name = "거래금액")
	public String getDealAmount() {
		return dealAmount;
	}

	@XmlElement(name = "거래유형")
	public String getReqGbn() {
		return reqGbn;
	}

	@XmlElement(name = "건축년도")
	public String getBuildYear() {
		return buildYear;
	}

	@XmlElement(name = "년")
	public String getDealYear() {
		return dealYear;
	}

	@XmlElement(name = "도로명")
	public String getRoadName() {
		return roadName;
	}

	@XmlElement(name = "도로명건물본번호코드")
	public String getRoadNameBonbun() {
		return roadNameBonbun;
	}

	@XmlElement(name = "도로명건물부번호코드")
	public String getRoadNameBubun() {
		return roadNameBubun;
	}

	@XmlElement(name = "도로명시군구코드")
	public String getRoadNameSigunguCode() {
		return roadNameSigunguCode;
	}

	@XmlElement(name = "도로명일련번호코드")
	public String getRoadNameSeq() {
		return roadNameSeq;
	}

	@XmlElement(name = "도로명지상지하코드")
	public String getRoadNameBasementCode() {
		return roadNameBasementCode;
	}

	@XmlElement(name = "도로명코드")
	public String getRoadNameCode() {
		return roadNameCode;
	}

	@XmlElement(name = "법정동")
	public String getDong() {
		return dong;
	}

	@XmlElement(name = "법정동본번코드")
	public String getBonbun() {
		return bonbun;
	}

	@XmlElement(name = "법정동부번코드")
	public String getBubun() {
		return bubun;
	}

	@XmlElement(name = "법정동시군구코드")
	public String getSigunguCode() {
		return sigunguCode;
	}

	@XmlElement(name = "법정동읍면동코드")
	public String getEubmyundongCode() {
		return eubmyundongCode;
	}

	@XmlElement(name = "법정동지번코드")
	public String getLandCode() {
		return landCode;
	}

	@XmlElement(name = "아파트")
	public String getApartmentName() {
		return apartmentName;
	}

	@XmlElement(name = "월")
	public String getDealMonth() {
		return dealMonth;
	}

	@XmlElement(name = "일")
	public String getDealDay() {
		return dealDay;
	}

	@XmlElement(name = "일련번호")
	public String getNo() {
		return no;
	}

	@XmlElement(name = "전용면적")
	public String getArea() {
		return area;
	}

	@XmlElement(name = "중개사소재지")
	public String getrDealerLawdnm() {
		return rDealerLawdnm;
	}

	@XmlElement(name = "지번")
	public String getJibun() {
		return jibun;
	}

	@XmlElement(name = "지역코드")
	public String getRegionalCode() {
		return regionalCode;
	}

	@XmlElement(name = "층")
	public String getFloor() {
		return floor;
	}

	@XmlElement(name = "해제사유발생일")
	public String getCancelDealDay() {
		return cancelDealDay;
	}

	@XmlElement(name = "해제여부")
	public String getCancelDealType() {
		return cancelDealType;
	}
	
	// 시작: 연립다세대 매매 실거래 자료
	public void setLandArea(String landArea) {
		this.landArea = landArea.trim();
	}
	
	public void setHouseName(String houseName) {
		this.houseName = houseName.trim();
	}
	// 끝: 연립다세대 매매 실거래 자료
	
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount.trim();
	}

	public void setReqGbn(String reqGbn) {
		this.reqGbn = reqGbn.trim();
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear.trim();
	}

	public void setDealYear(String dealYear) {
		this.dealYear = dealYear.trim();
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName.trim();
	}

	public void setRoadNameBonbun(String roadNameBonbun) {
		this.roadNameBonbun = roadNameBonbun.trim();
	}

	public void setRoadNameBubun(String roadNameBubun) {
		this.roadNameBubun = roadNameBubun.trim();
	}

	public void setRoadNameSigunguCode(String roadNameSigunguCode) {
		this.roadNameSigunguCode = roadNameSigunguCode.trim();
	}

	public void setRoadNameSeq(String roadNameSeq) {
		this.roadNameSeq = roadNameSeq.trim();
	}

	public void setRoadNameBasementCode(String roadNameBasementCode) {
		this.roadNameBasementCode = roadNameBasementCode.trim();
	}

	public void setRoadNameCode(String roadNameCode) {
		this.roadNameCode = roadNameCode.trim();
	}

	public void setDong(String dong) {
		this.dong = dong.trim();
	}

	public void setBonbun(String bonbun) {
		this.bonbun = bonbun.trim();
	}

	public void setBubun(String bubun) {
		this.bubun = bubun.trim();
	}

	public void setSigunguCode(String sigunguCode) {
		this.sigunguCode = sigunguCode.trim();
	}

	public void setEubmyundongCode(String eubmyundongCode) {
		this.eubmyundongCode = eubmyundongCode.trim();
	}

	public void setLandCode(String landCode) {
		this.landCode = landCode.trim();
	}

	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName.trim();
	}

	public void setDealMonth(String dealMonth) {
		this.dealMonth = dealMonth.trim();
	}

	public void setDealDay(String dealDay) {
		this.dealDay = dealDay.trim();
	}

	public void setNo(String no) {
		this.no = no.trim();
	}

	public void setArea(String area) {
		this.area = area.trim();
	}

	public void setrDealerLawdnm(String rDealerLawdnm) {
		this.rDealerLawdnm = rDealerLawdnm.trim();
	}

	public void setJibun(String jibun) {
		this.jibun = jibun.trim();
	}

	public void setRegionalCode(String regionalCode) {
		this.regionalCode = regionalCode.trim();
	}

	public void setFloor(String floor) {
		this.floor = floor.trim();
	}

	public void setCancelDealDay(String cancelDealDay) {
		this.cancelDealDay = cancelDealDay.trim();
	}

	public void setCancelDealType(String cancelDealType) {
		this.cancelDealType = cancelDealType.trim();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Item [landArea=");
		builder.append(landArea);
		builder.append(", houseName=");
		builder.append(houseName);
		builder.append(", dealAmount=");
		builder.append(dealAmount);
		builder.append(", reqGbn=");
		builder.append(reqGbn);
		builder.append(", buildYear=");
		builder.append(buildYear);
		builder.append(", dealYear=");
		builder.append(dealYear);
		builder.append(", roadName=");
		builder.append(roadName);
		builder.append(", roadNameBonbun=");
		builder.append(roadNameBonbun);
		builder.append(", roadNameBubun=");
		builder.append(roadNameBubun);
		builder.append(", roadNameSigunguCode=");
		builder.append(roadNameSigunguCode);
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
		builder.append(", landCode=");
		builder.append(landCode);
		builder.append(", apartmentName=");
		builder.append(apartmentName);
		builder.append(", dealMonth=");
		builder.append(dealMonth);
		builder.append(", dealDay=");
		builder.append(dealDay);
		builder.append(", no=");
		builder.append(no);
		builder.append(", area=");
		builder.append(area);
		builder.append(", rDealerLawdnm=");
		builder.append(rDealerLawdnm);
		builder.append(", jibun=");
		builder.append(jibun);
		builder.append(", regionalCode=");
		builder.append(regionalCode);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", cancelDealDay=");
		builder.append(cancelDealDay);
		builder.append(", cancelDealType=");
		builder.append(cancelDealType);
		builder.append("]");
		return builder.toString();
	}

}
