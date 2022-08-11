package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_5215 {

	static int max_score;
	static int[] nums; 
	static boolean[] isSelected;
	static int L;
	static int[] score;
	static int[] K;
	
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			
			max_score = Integer.MIN_VALUE;
			// 재료의 수: N
			// 제한 칼로리: L
			String[] split = in.readLine().split(" ");
			int N = Integer.parseInt(split[0]);
			L = Integer.parseInt(split[1]);
			
			// 제한 안 넘고 점수 제일 높은 경우의 수 -> 점수 구하기
			score = new int[N];
			K = new int[N];
			isSelected = new boolean[N];
			for(int i=0; i<N; i++) {
				split = in.readLine().split(" ");
				score[i] = Integer.parseInt(split[0]);
				K[i] = Integer.parseInt(split[1]);
				// 가장 맛에 대한 점수가 높은
			}
			subSet(0, N);
			sb.append(max_score).append("\n");
			
		}

		System.out.println(sb);
	}
	
	static void subSet(int index, int N) {
		
		if(index==N) {
			int sum_k = 0;
			int sum_s = 0;
			for(int i=0; i<N; i++) {
				if(isSelected[i]) {
					sum_k += K[i];
					sum_s += score[i];
				}
			}

			if(sum_k<L) {
				if(max_score < sum_s)
					max_score = sum_s;
			}
			return;
		}


		// 현재 값 선택
		isSelected[index] = true;
		subSet(index + 1, N);
		
		// 현재 값 선택 안 할 때
		isSelected[index] = false;
		subSet(index + 1, N);
		
	}

}
