package com.ssafy.publishing.book;

public class Megazine extends Book {
	
	public int year;  // 발행 년도
	
	public Megazine() {}
	

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String toString(int year) {
		StringBuilder sb=new StringBuilder();
		sb.append(super.toString()).append("\t")
		  .append(getYear());
		return sb.toString();
	}
	
	
	

}
