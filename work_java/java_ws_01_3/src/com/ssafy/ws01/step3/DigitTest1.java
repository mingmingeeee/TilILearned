package com.ssafy.ws01.step3;

public class DigitTest1 {
	
	public static void main(String[] args) {

		int count = 0; 
		
		for(int i=0; i<5; i++) {
			for(int j=0; j<5; j++) {
				if(j<i) {
					System.out.printf("%3s", " ");// 3칸 너비 공백 출력
					// %3: 세 칸
				}
				else {
					System.out.printf("%3d", ++count); // 3칸 너비 숫자 출력
				}
			}
			System.out.println();
		}
		
	}
	
}
