package test;

import java.io.*;
import java.util.*;

public class practice1 {

	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static int[][] map;
	private static int[][] dp;

	private static int M;
	private static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		int answer = dfs(0, 0);
		sb.append(answer);

		System.out.println(sb);

	}

	private static int dfs(int x, int y) {

		if (x == M - 1 && y == N - 1)
			return 1;

		if (dp[x][y] != -1)
			return dp[x][y];

		dp[x][y] = 0;

		for (int d = 0; d < 4; d++) {
			int testX = x + dx[d];
			int testY = y + dy[d];

			if (testX >= 0 && testX < M && testY >= 0 && testY < N) {
				if (map[x][y] > map[testX][testY]) {
					dp[x][y] += dfs(testX, testY);
				}
			}
		}

		return dp[x][y];

	}

}
