package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1952 {

	// 1일 이용권
	// 1달 이용권
	// 3달 이용권 (마지노선: 10월)
	// 1년 이용권
	private static int[] prices;
	private static int[] plans;
	
	private static int answer;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			/// 입력 ///
			prices = new int[4];
			plans = new int[12];

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < 4; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < 12; i++) {
				plans[i] = Integer.parseInt(st.nextToken());
			}
			/// 입력 ///
			
			answer = Integer.MAX_VALUE;
			/// 알고리즘 ///
			dfs(0, 0);
			/// 알고리즘 ///
			
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void dfs(int month, int sum) {
		if(answer < sum)
			return;
		
		if(month == 12) {
			answer = Math.min(answer, sum);
			return;
		}
		
		// 1일 이용권
		dfs(month + 1, sum + plans[month] * prices[0]);
		// 1달 이용권
		dfs(month + 1, sum + prices[1]);
		// 3달 이용권 (마지노선: 10월)
		if(month < 10)
			dfs(month + 3, sum + prices[2]);
		// 1년 이용권
		if(month == 0)
			dfs(month + 12, sum + prices[3]);
	}

}
