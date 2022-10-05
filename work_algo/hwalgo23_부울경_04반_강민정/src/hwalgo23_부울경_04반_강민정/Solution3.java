package hwalgo23_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Solution3 {

	private static final int INF = Integer.MAX_VALUE;
	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static int[][] map;
	private static int N;

	private static class Position implements Comparable<Position> {
		int x;
		int y;
		int weight;

		public Position(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Position o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(in.readLine());

			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				char[] tmp = in.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = tmp[j] - '0';
				}
			}

			sb.append(dijkstra(0, 0));
			sb.append("\n");

		}

		System.out.println(sb);
	}

	private static int dijkstra(int startX, int startY) {

		int[][] min = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				min[i][j] = INF;
			}
		}

		min[startX][startY] = 0;

		PriorityQueue<Position> queue = new PriorityQueue<>();
		queue.offer(new Position(0, 0, min[startX][startY]));

		boolean[][] visited = new boolean[N][N];

		Position p;

		while (!queue.isEmpty()) {

			Position minTime = queue.poll();

			if (visited[minTime.x][minTime.y])
				continue;

			visited[minTime.x][minTime.y] = true;

			if (minTime.x == N - 1 && minTime.y == N - 1)
				return min[minTime.x][minTime.y];

			for (int i = 0; i < 4; i++) {
				int testX = minTime.x + dx[i];
				int testY = minTime.y + dy[i];

				if (testX >= 0 && testX < N && testY >= 0 && testY < N
						&& min[testX][testY] > minTime.weight + map[testX][testY]) {
					min[testX][testY] = minTime.weight + map[testX][testY];
					queue.offer(new Position(testX, testY, min[testX][testY]));
				}
			}

		}
		
		return -1;
	}
}