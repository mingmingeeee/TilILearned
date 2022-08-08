package com.ssafy.hw.problem;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int max;
	static int sum;
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			sb.append("#" + test_case + " ");
			
			String[] strings = in.readLine().split(" ");
			
			int N = Integer.parseInt(strings[0]);
			int M = Integer.parseInt(strings[1]);
			
			st = new StringTokenizer(in.readLine(), " ");
			int[] kg = new int[N];
			for(int i=0; i<N; i++) {
				kg[i] = Integer.parseInt(st.nextToken()); 
			}
			
			max = Integer.MIN_VALUE;
			int[] nums = new int[2];
			sum = 0;
			comb(0, 0, 2, kg, M, nums);
			
			if(max==Integer.MIN_VALUE)
				max = -1;
			
			sb.append(max);
			sb.append("\n");
			
		}
		System.out.println(sb);
		
	}
	
	public static void comb(int cnt, int index, int R, int[] kg, int M, int[] nums) {
		
		if(cnt == R) {
			sum = 0;
			for(int i=0; i<R; i++) {
				sum += nums[i];
			}
			if(sum > max && sum <= M)
				max = sum;
			return;
		}
		
		for(int i=index; i<kg.length; i++) {
			
			nums[cnt] = kg[i]; 
			comb(cnt + 1, i + 1, R, kg, M, nums);
			
		}
		
	}

}
