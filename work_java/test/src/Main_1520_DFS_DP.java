import java.io.*;
import java.util.*;

public class Main_1520_DFS_DP {

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int M;
	private static int N;
	private static int[][] map;
	private static int[][] dp;
	private static boolean[][] visited;
	private static int answer;

	private class Position {
		int x;
		int y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 각 칸에 높이 있음
		// 지점 사이 이동: 상하좌우
		// (0, 0) 세준이 => (N-1, N-1)까지 감
		// 목표가 더 낮은 지점으로만 이동 => 목표 지점까지 가고자 함
		// 내리막길로만 이동하는 경로의 개수
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp = new int[M][N]; // 내리막길 경우의 수
		for (int i = 0; i < M; i++) {
			Arrays.fill(dp[i], -1);
		}

		answer = dfs(0, 0);

		sb.append(answer);
		System.out.println(sb);
	}

	// dp값을 0으로 초기화 => 방문 체크
	private static int dfs(int x, int y) {

		if (x == M - 1 && y == N - 1)
			return 1;
		if (dp[x][y] != -1)
			return dp[x][y];

		dp[x][y] = 0;
		for (int d = 0; d < 4; d++) {
			int testX = x + dx[d];
			int testY = y + dy[d];

			if (testX >= 0 && testY >= 0 && testX < M && testY < N) {
				if (map[x][y] > map[testX][testY]) {
					dp[x][y] += dfs(testX, testY);
				}
			}
		}

		return dp[x][y];

	}

}
