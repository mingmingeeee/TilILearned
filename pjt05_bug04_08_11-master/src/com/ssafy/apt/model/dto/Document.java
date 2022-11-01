package com.ssafy.apt.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Document {
	
	public String address_name;
	public String y;
	public String x;
	public String address_type;
	public Address address;
	public RoadAddress road_address;
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Document(String address_name, String y, String x, String address_type, Address address,
			RoadAddress road_address) {
		super();
		this.address_name = address_name;
		this.y = y;
		this.x = x;
		this.address_type = address_type;
		this.address = address;
		this.road_address = road_address;
	}
	public String getAddress_name() {
		return address_name;
	}
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	public String getAddress_type() {
		return address_type;
	}
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public RoadAddress getRoad_address() {
		return road_address;
	}
	public void setRoad_address(RoadAddress road_address) {
		this.road_address = road_address;
	}
	@Override
	public String toString() {
		return "Document [address_name=" + address_name + ", y=" + y + ", x=" + x + ", address_type=" + address_type
				+ ", address=" + address + ", road_address=" + road_address + "]";
	}
	
	
}
