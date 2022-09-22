package com.ssafy.sample.dto;

public class Person {
	
	private String id;
	private String name;
	private String departmentName;
	private String pay;
	
	public Person() {}
	
	public Person(String id, String name, String departmentName, String pay) {
		super();
		this.id = id;
		this.name = name;
		this.departmentName = departmentName;
		this.pay = pay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", departmentName=");
		builder.append(departmentName);
		builder.append(", pay=");
		builder.append(pay);
		builder.append("]");
		return builder.toString();
	}
	

}
