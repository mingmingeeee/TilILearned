package com.ssafy.offline2.classobject.person2;

public class TestDrive {
	
	public static void main(String[] args) {
		int a = 10;
		Person minjeong = new Person("강민정", 25, "부산 강서구 명지동", "없음", "여성");
		
		minjeong.walk();
		minjeong.run();
		minjeong.eat();
		
		System.out.println(minjeong.toString());
		
		Person bokyung = new Person("김보경", 26, "하단", "교육생", "남성");

		bokyung.walk();
		bokyung.run();
		bokyung.eat();
		
		System.out.println(bokyung);
	
	}

}
