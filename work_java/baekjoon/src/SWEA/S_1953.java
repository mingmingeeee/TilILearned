package SWEA;

import java.io.*;
import java.util.*;

public class S_1953 {

	// 1-> 상하좌우
	// 2-> 상하
	// 3-> 좌우
	// 4-> 상우
	// 5-> 하우
	// 6-> 하좌
	// 7-> 상좌
	// 상 0 하1 좌2 우3
	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static int[][] dir = { {}, { 0, 1, 2, 3 }, { 0, 1 }, { 2, 3 }, { 0, 3 }, { 1, 3 }, { 1, 2 }, { 0, 2 } };

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

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			String[] split = in.readLine().split(" ");
			int N = Integer.parseInt(split[0]); // 세로 크기
			int M = Integer.parseInt(split[1]); // 가로 크기
			int R = Integer.parseInt(split[2]); // 세로 위치
			int C = Integer.parseInt(split[3]); // 가로 위치
			int L = Integer.parseInt(split[4]); // 탈출 후 소요된 시간

			int[][] map = new int[N][M];

			for (int i = 0; i < N; i++) {
				split = in.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}

			int answer = bfs(map, N, M, R, C, L);

			sb.append(answer).append("\n");
		}
		System.out.println(sb);

	}

	private static boolean check(int idx, int val) {

		if (val == 1)
			return true;

		switch (idx) {
		case 0:
			if (val == 2 || val == 5 || val == 6)
				return true;
			break;
		case 1:
			if (val == 2 || val == 4 || val == 7)
				return true;
			break;
		case 2:
			if (val == 3 || val == 4 || val == 5)
				return true;
			break;
		case 3:
			if (val == 3 || val == 6 || val == 7)
				return true;
			break;
		}
		return false;

	}

	private static int bfs(int[][] map, int n, int m, int r, int c, int l) {

		Queue<Position> queue = new ArrayDeque<>();
		queue.offer(new Position(r, c));

		boolean[][] visited = new boolean[n][m];
		visited[r][c] = true;

		int count = 1;
		int depth = 1;

		while (!queue.isEmpty()) {

			int size = queue.size();

			for (int i = 0; i < size; i++) {
				Position cur = queue.poll();

				int d1 = map[cur.x][cur.y];
				for (int d2 = 0; d2 < dir[d1].length; d2++) {
					int d = dir[d1][d2];
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX >= 0 && testX < n && testY >= 0 && testY < m && !visited[testX][testY]) {

						if (check(d, map[testX][testY])) {
							queue.offer(new Position(testX, testY));
							visited[testX][testY] = true;
							count++;
						}

					}
				}
			}
			if (++depth == l)
				return count;
		}

		if (depth < l)
			return count;

		return 1;
	}

}
