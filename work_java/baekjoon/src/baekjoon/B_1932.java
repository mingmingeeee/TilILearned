package baekjoon;

import java.io.*;
import java.util.*;

public class B_1932  {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		int[][] sum = new int[N][N];
		int[] dp = new int[N];
		
		String[] split = in.readLine().split(" ");
		
		sum[0][0] = Integer.parseInt(split[0]);
		dp[0] = sum[0][0];

		for (int i = 1; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < i + 1; j++) {
				int left = 0;
				if(j >= 1)
					left = sum[i - 1][j - 1] + Integer.parseInt(split[j]);
				int right = sum[i - 1][j] + Integer.parseInt(split[j]);
				sum[i][j] = Math.max(left, right);

				dp[i] = Math.max(dp[i], sum[i][j]);
			}
		}

		System.out.println(dp[N - 1]);

	}

}
