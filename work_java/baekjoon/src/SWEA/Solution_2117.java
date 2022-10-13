package SWEA;

import java.io.*;
import java.util.*;

public class Solution_2117 {

	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static int[][] map;
	private static int maxK;

	private static class Position {
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

		// 5 <= N <= 20 (도시의 크기)
		// 1 <= M <= 10 (집이 지불할 수 있는 비용)

		// 손해를 보지 않는 한 최대한 많은 집에 홈방범 서비스 제공 => 가장 많은 집들에 제공하는 서비스 영역 구하고
		// "제공받는 집들의 수 출력"
		// 이익 = 서비스 제공받는 집들 수익 - 운영 비용 (K * K + (K - 1) * (K - 1))
		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			/// 출력 ///
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 도시 크기
			int M = Integer.parseInt(st.nextToken()); // 집이 지불할 수 있는 비용

			map = new int[N][N];
			maxK = 2 * N - 1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			/// 출력 ///

			int max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					max = Math.max(max, bfs(i, j, new boolean[N][N], M, maxK));
				}
			}

			sb.append(max).append("\n");

		}
		System.out.println(sb);

	}

	private static int bfs(int x, int y, boolean[][] visited, int M, int maxK) {
		int N = visited.length;
		Queue<Position> queue = new ArrayDeque<>();
		queue.offer(new Position(x, y));
		visited[x][y] = true;

		int depth = 1;
		int max = 0;
		while (!queue.isEmpty()) {
			// 이익 = 서비스 제공받는 집들 수익 - 운영 비용 (K * K + (K - 1) * (K - 1))
			int profit = 0, home = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j] && map[i][j] == 1) {
						home++;
					}
				}
			}
			profit = M * home - (depth * depth + (depth - 1) * (depth - 1));
			if(0 <= profit) {
				max = Math.max(home, max);
			} 

			int size = queue.size();

			for (int i = 0; i < size; i++) {

				Position cur = queue.poll();

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX < 0 || testY < 0 || testX >= N || testY >= N)
						continue;
					if (visited[testX][testY])
						continue;

					visited[testX][testY] = true;
					queue.offer(new Position(testX, testY));

				}
			}

			depth++;

		}

		return max;
	}

}
