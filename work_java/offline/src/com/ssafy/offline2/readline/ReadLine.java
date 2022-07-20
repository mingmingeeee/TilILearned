package com.ssafy.offline2.readline;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadLine {
	public static void main(String[] args) throws Exception {

		// 파일을 객체화 시켜서 자바로 가지고 옴
		System.setIn(new FileInputStream("ArrayTest_30_input.txt")); // 문제에서 주어진 Input 데이터 파일명 작성

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String readLine = in.readLine();
		System.out.println(readLine);
		
		int a = Integer.parseInt(readLine);
		
		int b = Integer.parseInt(in.readLine());
		
		String readLine2 = in.readLine();
		System.out.println(readLine2);
		
		String[] s = readLine2.split(" ");
		System.out.println(s[0]);
		char c = s[0].charAt(0);
		System.out.println(c);
		
		System.setIn(new FileInputStream("ArrayTest_31_input.txt"));
		in = new BufferedReader(new InputStreamReader(System.in));
		
		String a1 = in.readLine();
		Integer.parseInt(a1);
		
		String[] s1 = in.readLine().split(" ");
		String s2 = s1[0];
		String s3 = s1[1];
		int a2 = Integer.parseInt(s2);
		int a3 = Integer.parseInt(s3);
		
		System.out.println(a2+", "+a3);
		
		String[] b1 = in.readLine().split(" ");
		String b2 = b1[0];
		String b3 = b1[1];
		String b4 = b1[2];
		
		int d1 = Integer.parseInt(b2);
		int d2 = Integer.parseInt(b3);
		int d3 = Integer.parseInt(b4);
		
		
		System.out.println(d1+", "+d2+", "+d3);
		
		
		
		
		
	}
}
