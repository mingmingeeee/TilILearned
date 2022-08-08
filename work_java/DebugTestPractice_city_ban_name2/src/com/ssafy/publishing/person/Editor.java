package com.ssafy.publishing.person;

public class Editor extends Person {
	private String employeeNumber;  // 직원번호
	private String field;  // 분야
	
	public Editor() {}
	public Editor(String name, int age, String phone, String employeeNumber, String field) {
		super(name, age, phone);
		setEmployeeNumber(employeeNumber);
		setField(field);
	}
	
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(super.toString()).append("\t")
		  .append(getEmployeeNumber()).append("\t")
		  .append(getField());
		return sb.toString();
	}
}	
