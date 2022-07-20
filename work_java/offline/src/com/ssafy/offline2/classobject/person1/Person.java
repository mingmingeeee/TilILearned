package com.ssafy.offline2.classobject.person1;

public class Person {
	
	// 필드 (멤버 변수)
	String name;
	int age;
	String job;
	String address;
	String gender;
	
	// 기본 생성자 (다른 생성자가 존재하지 않으면, 컴파일러가 자동으로 생성)
	// 다른 생성자가 존재하면 기본 생성자는 생성되지 않음.
	public Person() {
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

}
