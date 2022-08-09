package com.ssafy.hw.problem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2 {

	private static int N; // 과자 봉지의 개수 N
	private static int M; // 무게 합 제한 M
	private static int[] a; // 과자 봉지들의 무게 a

	private static int R = 2; // N개 중 R개 뽑기
	private static int[] numbers; // 선택한 과자 봉지들의 무게
	private static int answer; // 과자 봉지들의 무게 합 최대 값
	
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			sb.append("#" + test_case + " ");

			/**
			 * 1. 입력 파일 객체화
			 */
			// 답 초기화
			answer = -1;
			numbers = new int[R];

			// 과자 봉지의 개수 N, 무게 합 제한 M
			String[] split = in.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			M = Integer.parseInt(split[1]);

			// 과자 봉지들의 무게 a
			split = in.readLine().split(" ");
			a = new int[N];
			for (int i = 0; i < N; i++) {
				a[i] = Integer.parseInt(split[i]);
			}

			/**
			 * 2. 알고리즘 풀기
			 */
			// N개의 과자 봉지 중 2개의 봉지를 택하는 경우: nC2
			comb(0, 0);
			sb.append(answer + "\n");
		}

		/**
		 * 3. 정답 출력
		 */

		System.out.println(sb);
	}
	
	public static void comb(int cnt, int start) {
		
		if(cnt==R){
			int sum = 0;
			for(int i=0; i<R; i++)
				sum += numbers[i];
			if(answer < sum && sum <= M) 
				answer = sum;
			return;
		}
		
		for(int i=start; i<N; i++) {
			numbers[cnt] = a[i];
			comb(cnt + 1, i + 1);
		}
		
	}

}
