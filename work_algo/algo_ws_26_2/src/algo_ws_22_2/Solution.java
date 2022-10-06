package algo_ws_22_2;

import java.io.*;
import java.util.*;

public class Solution {

	private static int N;
	private static int[][] D;
	private static final int INF = 999999;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			sb.append("#" + tc + " ");

			N = Integer.parseInt(st.nextToken());

			D = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					D[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && D[i][j] == 0)
						D[i][j] = INF;
				}
			}

			for (int k = 0; k < N; k++) { // 경유
				for (int i = 0; i < N; i++) { // 출발
					for (int j = 0; j < N; j++) { // 도착
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}

			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					if (D[i][j] != INF)
						sum += D[i][j];
				}
				answer = Math.min(answer, sum);
			}

			sb.append(answer).append("\n");

		}

		System.out.println(sb);
	}

}