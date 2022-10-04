package baekjoon;

import java.io.*;
import java.util.*;

public class B_2579 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		int[] dp = new int[N + 1];
		int[] stairs = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			stairs[i] = Integer.parseInt(in.readLine());
		}

		dp[1] = stairs[1];
		if (N >= 2)
			dp[2] = dp[1] + stairs[2];

		for (int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 3] + stairs[i - 1] + stairs[i], dp[i - 2] + stairs[i]);
		}

		System.out.println(dp[N]);

	}

}
