package baekjoon;

import java.io.*;
import java.util.*;

// 백준이 퇴사
// N + 1일째 되는 날 퇴사 => 남은 N일 동안 최대한 많은 상담 하려고 ...
// 하루 하나씩 서로 다른 사람의 상담 잡아놓음
// T: 상담 완료하는데 걸리는 기간
// P: 상담했을 때 받을 수 있는 금액
// 상담 적절히 했을 때 백준이가 얻을 수 있는 최대 수익 구하기

// 완탐 => DFS하면 될듯???]

public class Main_14502_DP {

	private static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());

		int[] dp = new int[N];
		int[][] info = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int day = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());

			info[i][0] = day;
			info[i][1] = price;
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (i + info[i][0] < N) {
				dp[i + info[i][0]] = Math.max(dp[i + info[i][0]], dp[i] + info[i][1]);
				answer = Math.max(answer, dp[i + info[i][0]]);
			}
			if (i + 1 < N) {
				dp[i + 1] = Math.max(dp[i + 1], dp[i]);
				answer = Math.max(answer, dp[i + 1]);
			}
		}

		sb.append(answer).append("\n");
		System.out.println(sb);
	}

}
