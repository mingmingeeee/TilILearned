package com.ssafy.offline2.classobject.person2;

public class Person {
	
	// 필드 (멤버 변수)
	String name;
	int age;
	String job;
	String address;
	String gender;
	
	public Person() {
		
	}
	
	public Person(String name, int age, String address, String job, String gender) {
		this.name = name;
		this.age = age;
		this.job = job;
		this.address = address;
		this.gender = gender;
		System.out.println(name + "이(가) 탄생!");
	}
	
	// 메서드 (함수) : 행동
	void walk(){
		System.out.println(name + "이(가) 걷는다.");
	}
	
	void run(){
		System.out.println(name + "이(가) 달린다.");
	}
	
	void eat(){
		System.out.println(name + "이(가) 먹는다.");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [name=").append(name).append(", age=").append(age).append(", job=").append(job)
				.append(", address=").append(address).append(", gender=").append(gender).append("]");
		return builder.toString();
	}
	
	


}
