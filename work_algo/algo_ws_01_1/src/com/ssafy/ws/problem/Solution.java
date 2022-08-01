package com.ssafy.ws.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		
//		int T;
//		T = Integer.parseInt(in.readLine());
//		for(int test_case = 1; test_case <=T; test_case++) {
//			sb.append("#" + test_case + " ");
//			
//			String newLine = in.readLine();
//			
//			int answer = 0;
//			
//			/**
//			 * 알고리즘
//			 * */
//			// 메모리의 첫번째 값이 1일 경우는 한 번 바꾼 걸로 카운트
//			if(newLine.charAt(0)=='1') {
//				answer++;
//			}
//			
//			for(int i=0; i<newLine.length() -1; i++) {
//				if(newLine.charAt(i) != newLine.charAt(i + 1)) {
//					 answer++;
//				}
//			}
//			
//			/**
//			 * 정답 출력 
//			 * */
//			sb.append(answer);
//			sb.append('\n');
//		}
//		System.out.println(sb);
		
		int T = Integer.parseInt(in.readLine());
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		System.out.println(Recursion(T));
		
	}
	
	
	static String g = "";
	public static String Recursion(int n) {
		
		System.out.print(g + "\"재귀함수가 뭔가요?\"\n");
		if(n == 0) {
			return g + "재귀함수는 자기 자신을 호출하는 함수라네.\n" + g + "라고 답변하였지.\n";
		}
		
		System.out.print(g + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\"\n"
		+ g + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n"  
		+ g + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		
		String a = g + "라고 답변하였지.\n";
		
		if(n>0) {
			g += "____";
		}
		return Recursion(n-1) + a;
		
	}

}
