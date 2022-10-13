package baekjoon;

import java.io.*;
import java.util.*;

public class B_5427 {

	private static final char BLANK = '.';
	private static final char WALL = '#';
	private static final char JI= 'J';
	private static final char FIRE = 'F';

	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };


	private static Queue<Position> ji = new ArrayDeque<>();
	private static Queue<Position> fire = new ArrayDeque<>();

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

		String[] split = in.readLine().split(" ");

		int w = Integer.parseInt(split[0]);
		int h = Integer.parseInt(split[1]);

		char[][] map = new char[w][h];

		for (int i = 0; i < w; i++) {
			split = in.readLine().split("");
			for (int j = 0; j < h; j++) {
				map[i][j] = split[j].charAt(0);
				if (map[i][j] == JI)
					ji.offer(new Position(i, j));
				else if (map[i][j] == FIRE)
					fire.offer(new Position(i, j));
			}
		}

		int check = bfs(map, w, h);

		if (check == -1)
			sb.append("IMPOSSIBLE").append("\n");
		else
			sb.append(check).append("\n");

		System.out.println(sb);

	}

	private static int bfs(char[][] map, int w, int h) {
		Queue<Position> queue = new ArrayDeque<>();

		int depth = 1;
		while (!ji.isEmpty()) {

			// 불 먼저 퍼뜨리기
			int size = fire.size();
			for (int i = 0; i < size; i++) {
				Position cur = fire.poll();

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX >= 0 && testX < w && testY >= 0 && testY < h) {
						if (map[testX][testY] == JI || map[testX][testY] == BLANK) {
							fire.offer(new Position(testX, testY));
							map[testX][testY] = FIRE;
						}
					}
				}
			}

			// 지훈 이동
			size = ji.size();
			for (int i = 0; i < size; i++) {
				Position cur = ji.poll();

				if (cur.x == 0 || cur.x == w - 1 || cur.y == 0 || cur.y == h - 1)
					return depth;

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX >= 0 && testX < w && testY >= 0 && testY < h) {
						if (map[testX][testY] == BLANK) {
							ji.offer(new Position(testX, testY));
							map[testX][testY] = JI;
						}
					}
				}
			}

			depth++;

		}

		return -1;
	}

}
