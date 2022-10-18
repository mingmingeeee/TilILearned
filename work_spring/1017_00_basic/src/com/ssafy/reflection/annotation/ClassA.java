package com.ssafy.reflection.annotation;

public class ClassA {
	
	@Order(number = 3)
	public void first() {
		System.out.println("first 메서드 호출 ...");
	}
	
	@Order(number = 1)
	public void second() {
		System.out.println("second 메서드 호출 ...");
	}
	
	@Order(number = 2)
	public void third() {
		System.out.println("third 메서드 호출 ...");
	}
}
