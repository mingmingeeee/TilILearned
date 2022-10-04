package baekjoon;

import java.io.*;
import java.util.*;

public class B_1912_2 {
	
	private static int[] arr;
	private static int[] dp;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		
		arr = new int[N];
		dp = new int[N];

		String[] split = in.readLine().split(" ");
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(split[i]);
			dp[i] = Integer.MIN_VALUE;
		}

		int answer = arr[0];
		dp[0] = arr[0];
		
		topDown(N - 1);
		
		for(int i = 1; i < N; i++) {
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);

	}
	
	private static int topDown(int N) {
		if(dp[N] > Integer.MIN_VALUE || N == 0) { // 값이 있거나 0이면 반환
			return dp[N];
		}
		
		return dp[N] = Math.max(topDown(N - 1) + arr[N], arr[N]);
	}

}
