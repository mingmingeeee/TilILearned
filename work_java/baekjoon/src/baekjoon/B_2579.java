package baekjoon;

import java.io.*;
import java.util.*;

public class B_2579 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);

		int[] w = new int[N + 1];
		int[] v = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			split = in.readLine().split(" ");
			w[i] = Integer.parseInt(split[0]);
			v[i] = Integer.parseInt(split[1]);
		}
		
		int[] dp = new int[K + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = K; j >= 1; j--) {
				if(w[i] <= j) {
					dp[j] = Math.max(dp[j - w[i]] + v[i], dp[j]);
				}
			}
		}
		
		System.out.println(dp[K]);

	}

}
