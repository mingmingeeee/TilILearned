package hwalgo23_부울경_04반_강민정;

import java.io.*;
import java.util.*;

// 복구 시간이 가장 짧은 경로 

public class Solution {

	private static int[] dx = { 1, -1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };
	private static int[][] depth;
	private static StringBuilder sb = new StringBuilder();

	private static class Position {

		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(in.readLine());

		for (int i = 1; i <= T; i++) {
			sb.append("#" + i + " ");

			int N = Integer.parseInt(in.readLine());
			depth = new int[N][N];
			for (int x = 0; x < N; x++)
				Arrays.fill(depth[x], Integer.MAX_VALUE);
			depth[0][0] = 0;
			
			int[][] map = new int[N][N];
			for (int x = 0; x < N; x++) {
				String[] split = in.readLine().split("");
				for (int y = 0; y < N; y++) {
					map[x][y] = Integer.parseInt(split[y]);
				}
			}

			bfs(N, map);
		}

		System.out.println(sb);

	}

	private static void bfs(int N, int[][] map) {
		
		Queue<Position> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		queue.offer(new Position(0, 0));
		visited[0][0] = true;

		int min = Integer.MAX_VALUE;

		while (!queue.isEmpty()) {

			int size = queue.size();

			for (int i = 0; i < size; i++) {

				Position cur = queue.poll();

				if (cur.x == N - 1 && cur.y == N - 1) {
					if (depth[N - 1][N - 1] < min) {
						min = depth[N - 1][N - 1];
					}
				}

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if ((testX >= 0 && testX < N && testY >= 0 && testY < N) && 
							(!visited[testX][testY] || depth[testX][testY] > depth[cur.x][cur.y] + map[testX][testY])) {
						queue.offer(new Position(testX, testY));
						visited[testX][testY] = true;
						depth[testX][testY] = depth[cur.x][cur.y] + map[testX][testY];
					}
				}

			}

		}

		sb.append(min).append("\n");

	}

}
