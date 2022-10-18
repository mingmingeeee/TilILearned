package com.ssafy.hello.di1;

public class HelloMain {
	
	public static void main(String[] args) {
		HelloMessageKor kor = new HelloMessageKor();
		String greet = kor.helloHangul("안효인");
//		HelloMessageEng eng = new HelloMessageEng();
//		String greet = eng.helloEng("안효인");
		System.out.println(greet);
	}
}
