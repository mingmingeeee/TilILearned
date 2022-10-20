package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1563 {

	private static int[][][] dp;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());

		// int[학기][출석일수][지각][결석]
		dp = new int[N][2][3];

		int answer = dfs(0, 0, 0);
		sb.append(answer);
		System.out.println(sb);
	}

	
	private static int dfs(int day, int L, int A) {
		if (L == 2 || A == 3) // 지각이 2번, 결석 연속 3번이면 개근X
			return 0;
		
		if (day >= N)
			return 1;
		
		if(dp[day][L][A] != 0)
			return dp[day][L][A];

		int result = dfs(day + 1, L, 0) + dfs(day + 1, L + 1, 0)
			+ dfs(day + 1, L, A + 1);
		
		dp[day][L][A] = result;
		
		return result % 1000000;
	}

}
