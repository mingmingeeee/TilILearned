package hwalgo12_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main2 {

//	<< 다른 클래스 안에 클래스(객체) 쓰는 법 >> 	
//	static class Person{
//		String name;
//		int age;
//	}
//	<< main 안에는 >>	
//	Person person = new Person();
//	Or Person person = new Main().new Person();
	
	static class Chemical{
		public int x; // 최저 보관 온도
		public int y; // 최고 보관 온도 
		
		public Chemical(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		
		Chemical[] C = new Chemical[N];
		for(int i=0; i<N; i++) {
			String[] split = in.readLine().split(" ");
			C[i] = new Chemical(Integer.parseInt(split[0]), Integer.parseInt(split[1])); 
		}
		
		// 1. 최고 보관 온도 기준 오름차순 정렬
		Arrays.sort(C, new Comparator<Chemical>() {

			@Override
			public int compare(Chemical o1, Chemical o2) {
				return o1.y - o2.y;
			}
		});
		
		// 현재까지 보관할 수 있는 최고 보관 온도를 max 변수에 담기
		int max = C[0].y;
		
		int answer = 1; // 냉장고의 개수, 냉장고 1개는 기본으로 필요
		for(int i=1; i<N; i++) {
			// 만약 냉장고의 최고 보관 온도보다 높은 최저 보관 온도의 화학 물질이 있다면
			if(C[i].x > max) {
				
				// 그 물질의 최고 보관 온도로 맞춘 냉장고 추가
				max = C[i].y;
				answer++;

				
			}
		}
		
		System.out.println(answer);
		
	}

}
