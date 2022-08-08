package com.ssafy.publishing.vendor;

public class Library extends Vendor{
	private String region;  // 지역
	
	public Library() {}
	public Library(String name, int capacity, String region) {
		super(name, capacity);
		setRegion(region);
	}
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(super.toString()).append("\t")
		  .append(getRegion());
		return sb.toString();
	}

}