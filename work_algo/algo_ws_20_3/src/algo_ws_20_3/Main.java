package algo_ws_20_3;

import java.io.*;
import java.util.*;

// debuging: shift 키 누르고 하면 1행 ~열 이렇게 안 봐도 되고 여러개 동시에 볼 수 있음 

public class Main {

	private static final int CLEANER = -1;

	private static int[][] room;
	private static List<Position> cleaner = new ArrayList<>();
	private static Queue<Position> air = new ArrayDeque<>();

	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };

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
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int R = Integer.parseInt(split[0]);
		int C = Integer.parseInt(split[1]);
		int T = Integer.parseInt(split[2]);

		room = new int[R][C];

		for (int i = 0; i < R; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(split[j]);
				if (room[i][j] == CLEANER)
					cleaner.add(new Position(i, j));
				else if (0 < room[i][j])
					air.offer(new Position(i, j));
			}
		}

		for (int i = 0; i < T; i++) {

			spread();
			clean(0, dx1, dy1);

		}

//		for (int i = 0; i < R; i++) {
//			for (int j = 0; j < C; j++) {
//				System.out.print(room[i][j] + " ");
//			}
//			System.out.println();
//		}

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < room.length && 0 <= y && y < room.length)
			return true;
		return false;
	}

	private static void spread() {

		int size = air.size();

		boolean[][] visited = new boolean[room.length][room[0].length];

		int[][] tmp = new int[room.length][room[0].length];

		for (int i = 0; i < size; i++) {
			Position cur = air.poll();

			int count = 0;
			for (int d = 0; d < dx.length; d++) {
				int testX = cur.x + dx[d];
				int testY = cur.y + dy[d];
				if (isRange(testX, testY) && room[testX][testY] != CLEANER) { // 방 안에 있고, 공기 청정기 아닐 때
					count++;
					tmp[testX][testY] += room[cur.x][cur.y] / 5;
					visited[testX][testY] = true;
					air.offer(new Position(testX, testY));
				}
			}
			tmp[cur.x][cur.y] += room[cur.x][cur.y] - room[cur.x][cur.y] / 5 * count;
			visited[cur.x][cur.y] = true;

		}

		for (int i = 0; i < room.length; i++) {
			for (int j = 0; j < room[0].length; j++) {
				if (visited[i][j]) {
					room[i][j] = tmp[i][j];
				}
			}
		}

	}

	static int[] dx1 = { 0, -1, 0, 1 };
	static int[] dy1 = { 1, 0, -1, 0 };

	static int[] dx2 = { 1, 0, -1, 0 };
	static int[] dy2 = { 0, 1, 0, -1 };

	private static void clean(int c, int[] dxo, int[] dyo) {

		int airX = cleaner.get(c).x;
		int airY = cleaner.get(c).y + 1;

		int[][] tmp = new int[room.length][room[0].length];

		int d = 0;

		int size = air.size();
		for (int i = 0; i < size; i++) {
			Position cur = air.poll();

			int testX = cur.x + dxo[d];
			int testY = cur.y + dyo[d];
			if (isRange(testX, testY) && room[testX][testY] != -1 && testX <= airX) {
				air.offer(new Position(testX, testY));
				tmp[testX][testY] = room[cur.x][cur.y];
			} else if (!isRange(testX, testY))
				d++;
			else
				air.offer(new Position(cur.x, cur.y));

		}

		for (int i = 0; i < room.length; i++) {
			for (int j = 0; j < room[0].length; j++) {
				System.out.print(tmp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

}