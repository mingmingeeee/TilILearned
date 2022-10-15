import java.io.*;
import java.util.*;

public class Main_1520 {

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int M;
	private static int N;
	private static int[][] map;
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

		dfs(0, 0);

		sb.append(answer);
		System.out.println(sb);
	}

	private static void dfs(int x, int y) {

		visited[x][y] = true;

		if (x == M - 1 && y == N - 1)
			answer++;

		for (int d = 0; d < 4; d++) {
			int testX = x + dx[d];
			int testY = y + dy[d];

			if (testX >= 0 && testY >= 0 && testX < M && testY < N && !visited[testX][testY]) {
				if (map[x][y] > map[testX][testY])
					dfs(testX, testY);
			}
		}

		visited[x][y] = false;

	}

}
