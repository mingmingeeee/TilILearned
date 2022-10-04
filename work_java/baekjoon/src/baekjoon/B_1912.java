package baekjoon;

import java.io.*;
import java.util.*;

public class B_1912 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		// 연속된 몇 개 수 선택해서 구할 수 있는 합 중 가장 큰 합

		int[] dp = new int[N];
		int[] numbers = new int[N];

		String[] split = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(split[i]);
		}

		dp[0] = numbers[0];

		int answer = dp[0];
		for (int i = 1; i < N; i++) {
			dp[i] = Math.max(dp[i - 1] + numbers[i], numbers[i]);
			
			answer = Math.max(answer, dp[i]);
		}
		
		System.out.println(answer);

	}

}
