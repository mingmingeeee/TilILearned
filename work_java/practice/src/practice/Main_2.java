package practice;

import java.io.*;
import java.util.*;

public class Main_2 {

	private static int[] weights;
	private static int[] check_weights;
	private static boolean[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		weights = new int[N];
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
			max += weights[i];
		}
		int M = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine(), " ");
		check_weights = new int[M];
		for (int i = 0; i < M; i++) {
			check_weights[i] = Integer.parseInt(st.nextToken());
			max += check_weights[i];
		}
		
		dp = new boolean[N + 1][max + 1];
		
		dfs(0, 0);

		for (int i = 0; i < M; i++) {
			if(dp[N][check_weights[i]])
				sb.append("Y").append(" ");
			else
				sb.append("N").append(" ");
		}
		
		for(int i=0; i<N + 1; i++) {
			for(int j=0; j<max + 1; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
		
		System.out.println(sb);
		
	}
	
	private static void dfs(int cnt, int weight) {
		
		if(dp[cnt][weight])
			return;
		
		dp[cnt][weight] = true;
		
		if(cnt == weights.length)
			return;
		
		dfs(cnt + 1, weight);
		dfs(cnt + 1, weight + weights[cnt]);
		dfs(cnt + 1, Math.abs(weight - weights[cnt]));
		
	}

}
