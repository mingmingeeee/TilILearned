package baekjoon;

import java.io.*;
import java.util.*;

public class B_11053 {
	
	private static class Position {
		List<Integer> list = new ArrayList<>();
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		int[] data = new int[N];
		int[] dp = new int[N];
		List<Integer> list = new ArrayList<>();

		String[] split = in.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			data[i] = Integer.parseInt(split[i]);
		}

		int max = 0;
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if(data[j] < data[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);

	}

}
