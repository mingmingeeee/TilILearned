package com.ssafy.apt.model.dto;

public class AptInfo {
	 private String renewalRequest; // 갱신요구권사용
	 private String yearOfConstruction;
	 private String contractClassification ;
	 private String term;
	 private String year;
	 private String dong;
	 private String guarantee;
	 private String aptName ;
	 private String month;
	 private String monthlyRent;
	 private String day;
	 private String dedicatedArea ;
	 private String contractDeposit ;
	 private String contractMonthlyRent ;
	 private String jibun;
	 private String areaCode;
	 private String floor;
	 private String lat;
	 private String lng;
	 private int aroundOptions =0;
	public int getAroundOptions() {
		return aroundOptions;
	}
	public void setAroundOptions(int aroundOptions) {
		this.aroundOptions = aroundOptions;
	}
	public AptInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AptInfo(String renewalRequest, String yearOfConstruction, String contractClassification, String term,
			String year, String dong, String guarantee, String aptName, String month, String monthlyRent, String day,
			String dedicatedArea, String contractDeposit, String contractMonthlyRent, String jibun, String areaCode,
			String floor,int aroundOptions) {
		super();
		this.renewalRequest = renewalRequest;
		this.yearOfConstruction = yearOfConstruction;
		this.contractClassification = contractClassification;
		this.term = term;
		this.year = year;
		this.dong = dong;
		this.guarantee = guarantee;
		this.aptName = aptName;
		this.month = month;
		this.monthlyRent = monthlyRent;
		this.day = day;
		this.dedicatedArea = dedicatedArea;
		this.contractDeposit = contractDeposit;
		this.contractMonthlyRent = contractMonthlyRent;
		this.jibun = jibun;
		this.areaCode = areaCode;
		this.floor = floor;
		this.aroundOptions = aroundOptions;
	}
	public String getRenewalRequest() {
		return renewalRequest;
	}
	public void setRenewalRequest(String renewalRequest) {
		this.renewalRequest = renewalRequest;
	}
	public String getYearOfConstruction() {
		return yearOfConstruction;
	}
	public void setYearOfConstruction(String yearOfConstruction) {
		this.yearOfConstruction = yearOfConstruction;
	}
	public String getContractClassification() {
		return contractClassification;
	}
	public void setContractClassification(String contractClassification) {
		this.contractClassification = contractClassification;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getGuarantee() {
		return guarantee;
	}
	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	public String getAptName() {
		return aptName;
	}
	public void setAptName(String aptName) {
		this.aptName = aptName;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMonthlyRent() {
		return monthlyRent;
	}
	public void setMonthlyRent(String monthlyRent) {
		this.monthlyRent = monthlyRent;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getDedicatedArea() {
		return dedicatedArea;
	}
	public void setDedicatedArea(String dedicatedArea) {
		this.dedicatedArea = dedicatedArea;
	}
	public String getContractDeposit() {
		return contractDeposit;
	}
	public void setContractDeposit(String contractDeposit) {
		this.contractDeposit = contractDeposit;
	}
	public String getContractMonthlyRent() {
		return contractMonthlyRent;
	}
	public void setContractMonthlyRent(String contractMonthlyRent) {
		this.contractMonthlyRent = contractMonthlyRent;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "AptInfo [renewalRequest=" + renewalRequest + ", yearOfConstruction=" + yearOfConstruction
				+ ", contractClassification=" + contractClassification + ", term=" + term + ", year=" + year + ", dong="
				+ dong + ", guarantee=" + guarantee + ", aptName=" + aptName + ", month=" + month + ", monthlyRent="
				+ monthlyRent + ", day=" + day + ", dedicatedArea=" + dedicatedArea + ", contractDeposit="
				+ contractDeposit + ", contractMonthlyRent=" + contractMonthlyRent + ", jibun=" + jibun + ", areaCode="
				+ areaCode + ", floor=" + floor + ", lat=" + lat + ", lng=" + lng + "]";
	}

	
	
}
