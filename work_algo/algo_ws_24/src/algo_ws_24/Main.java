package algo_ws_24;

import java.io.*;
import java.util.*;

public class Main {

	private static int[] hdx = { -2, -1, -2, -1, 1, 2, 1, 2 };
	private static int[] hdy = { -1, -2, 1, 2, 2, 1, -2, -1 };

	private static int[] dx = { 1, -1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static int W;
	private static int H;
	private static int[][] map;
	private static boolean[][][] visited;

	private static class Position {
		int x;
		int y;
		int depth;
		int k;

		public Position(int x, int y, int depth, int k) {
			this.x = x;
			this.y = y;
			this.depth = depth;
			this.k = k;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int K = Integer.parseInt(in.readLine());

		String[] split = in.readLine().split(" ");

		H = Integer.parseInt(split[0]);
		W = Integer.parseInt(split[1]);

		visited = new boolean[W][H][K + 1];

		map = new int[W][H];

		for (int i = 0; i < W; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < H; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		System.out.println(bfs(K));

	}

	private static boolean isRange(int x, int y) {
		if (x >= 0 && x < W && y >= 0 && y < H)
			return true;
		return false;
	}

	private static int bfs(int K) {

		Queue<Position> queue = new ArrayDeque<>();
		queue.offer(new Position(0, 0, 0, 0));
		visited[0][0][0] = true;

		while (!queue.isEmpty()) {

			int size = queue.size();
			
			for (int i = 0; i < size; i++) {
				Position c = queue.poll();
				if (c.x == W - 1 && c.y == H - 1) {
					return c.depth;
				}
				
				if (c.k < K) {
					for (int d = 0; d < 8; d++) {
						int testX = c.x + hdx[d];
						int testY = c.y + hdy[d];

						if (isRange(testX, testY) && map[testX][testY] != 1 && !visited[testX][testY][c.k + 1]) {
							queue.offer(new Position(testX, testY, c.depth + 1, c.k + 1));
							visited[testX][testY][c.k + 1] = true;
						}
					}
				}

				for (int d = 0; d < 4; d++) {
					int testX = c.x + dx[d];
					int testY = c.y + dy[d];
					

					if (isRange(testX, testY) && map[testX][testY] != 1 && !visited[testX][testY][c.k]) {
						queue.offer(new Position(testX, testY, c.depth + 1, c.k));
						visited[testX][testY][c.k] = true;
					}
				}

				

			}
			
			

		}

		return -1;

	}

}
