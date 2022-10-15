package baekjoon;

import java.io.*;
import java.util.*;

// DFS, BFS 시간 초과? => DP 생각하기 !!!

public class Main_1937 {

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int N;
	private static int[][] map;
	private static int[][] dp;
	private static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 전 지역보다 대나무 많이 있는 곳 가야 함
		// 판다가 최대한 많은 칸을 이동하려면 어떤 경로를 통해 움직여야 하는지?
		// 어떤 지점에 풀어놓아야 하는가?
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		dp = new int[N][N]; // 시작점으로 했을 때 이동할 수 있는 최대 값

		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				dp[i][j] = -1;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (dp[i][j] == -1)
					dfs(i, j);
			}
		}

		sb.append(answer);
		System.out.println(sb);

	}

	private static int dfs(int x, int y) {

		if (dp[x][y] != -1)
			return dp[x][y];

		dp[x][y] = 1;

		int max = Integer.MIN_VALUE;
		for (int d = 0; d < 4; d++) {
			int testX = x + dx[d];
			int testY = y + dy[d];

			if (testX >= 0 && testY >= 0 && testX < N && testY < N) {
				if (map[x][y] < map[testX][testY]) {
					max = Math.max(max, dfs(testX, testY));
				}
			}
		}

		if (max != Integer.MIN_VALUE)
			dp[x][y] = 1 + max;

		answer = Math.max(answer, dp[x][y]);
		return dp[x][y];

	}

}
