package com.ssafy.startcamp.day02.forwhile;

public class Label {
	/*
	 * label 사용은 권장하지 않음.
	 * 실무에서 본 적이 단 한번도 없었음.
	 */
	public static void main(String[] args) {
		a:
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (j >= 5) {
						continue a;
					}
					System.out.println(i + " " + j);
				}
				System.out.println("for(j) end");
			}
	
		System.out.println("end of 'a' label");

		b:
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (j >= 5) {
						break b;
					}
					System.out.println(i + " " + j);
				}
			}
		
		System.out.println("end of 'b' label");
	}
}