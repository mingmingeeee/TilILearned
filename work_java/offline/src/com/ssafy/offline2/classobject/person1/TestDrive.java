package com.ssafy.offline2.classobject.person1;

public class TestDrive {
	
	public static void main(String[] args) {
		int a = 10;
		Person minjeong = new Person();
		
		minjeong.name = "강민정";
		minjeong.age = 25;
		minjeong.gender = "여성";
		minjeong.job = "없음";
		minjeong.address = "부산 강서구 명지동";
		
		minjeong.walk();
		minjeong.run();
		minjeong.eat();
		
		System.out.println(minjeong.age + " / " + minjeong.gender + " / " + minjeong.address);
		
		Person bokyung = new Person();
		bokyung.name = "김보경";
		bokyung.age = 26;
		bokyung.gender = "남성";
		bokyung.job = "교육생";
		bokyung.address = "하단";
		
		bokyung.walk();
		bokyung.run();
		bokyung.eat();
		
		System.out.println(bokyung.age + " / " + bokyung.gender + " / " + bokyung.address);
	}

}
