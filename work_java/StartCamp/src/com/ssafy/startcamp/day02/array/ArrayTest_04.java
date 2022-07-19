package com.ssafy.startcamp.day02.array;

public class ArrayTest_04 {
	public static void main(String[] args) {

		String org = "SSAFY";
		// @@TODOBLOCK: char []을 이용해 String org의 각 문자를 저장하고 출력하는 코드를 작성하시오.

		char[] chars = new char[org.length()];

		for (int i = 0; i < chars.length; i++) {
			chars[i] = org.charAt(i);
		}

		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
		}

		// API의 활용
		chars = org.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			System.out.print(chars[i]);
		}

		// @@END:
	}
}
