package algo_ws_18_1;

import java.io.*;
import java.util.*;

public class Main2 {

	static final int WATER = '*';
	static final int CAVE = 'D';
	static final int GOSUM = 'S';
	static final int BLANK = '.';
	static final int ROCK = 'X';

	private static char[][] map;

	static Queue<Position> water = new ArrayDeque<>();
	static Queue<Position> gosum = new ArrayDeque<>();
	static int answer;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static class Position {
		int x;
		int y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		int R = Integer.parseInt(split[0]);
		int C = Integer.parseInt(split[1]);

		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				if (map[i][j] == WATER) {
					water.offer(new Position(i, j));
				} else if (map[i][j] == GOSUM) {
					gosum.offer(new Position(i, j));
				}
			}
		}

		int answer = bfs();

		System.out.println(answer != 0 ? answer : "KAKTUS");
	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < map.length && 0 <= y && y < map[0].length) {
			return true;
		}
		return false;
	}

	private static int bfs() {

		int depth = 0;
		while (!gosum.isEmpty()) {

			int size = water.size();
			for (int i = 0; i < size; i++) {

				Position p = water.poll();

				for (int d = 0; d < dx.length; d++) {
					int testX = p.x + dx[d];
					int testY = p.y + dy[d];
					if (isRange(testX, testY) && map[testX][testY] == BLANK) {
						map[testX][testY] = WATER;
						water.offer(new Position(testX, testY));
					}
				}

			}

			size = gosum.size();

			for (int i = 0; i < size; i++) {

				Position p = gosum.poll();

				for (int d = 0; d < dx.length; d++) {
					int testX = p.x + dx[d];
					int testY = p.y + dy[d];
					if (isRange(testX, testY)) {
						if(map[testX][testY] == CAVE) {
							return ++depth;
						}
						else if (map[testX][testY] == BLANK) {
							map[testX][testY] = GOSUM;
							gosum.offer(new Position(testX, testY));
						}
					}
				}
			}

			depth++;
		}
		return 0;

	}

}
