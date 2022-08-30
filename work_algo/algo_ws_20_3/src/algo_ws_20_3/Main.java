package algo_ws_20_3;

import java.io.*;
import java.util.*;

// debuging: shift 키 누르고 하면 1행 ~열 이렇게 안 봐도 되고 여러개 동시에 볼 수 있음 

public class Main {

	private static final int CLEANER = -1;

	private static int[][] room;
	private static List<Position> cleaner = new ArrayList<>();
	private static Queue<Position> air = new ArrayDeque<>();

	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };

	private static int R;
	private static int C;

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

		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
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
			clean();

		}

		int sum = 0;
		for(Position p : air) {
			sum += room[p.x][p.y];
		}

		sb.append(sum);

		System.out.println(sum);

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < room.length && 0 <= y && y < room[0].length)
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

	static void clean() {

		int top = cleaner.get(0).x;

		for (int x = top - 1; x > 0; x--) {
			room[x][0] = room[x - 1][0];
		}

		for (int y = 0; y < C - 1; y++) {
			room[0][y] = room[0][y + 1];
		}

		for (int x = 0; x < top; x++) {
			room[x][C - 1] = room[x + 1][C - 1];
		}

		for (int y = C - 1; y > 1; y--) {
			room[top][y] = room[top][y - 1];
		}

		room[top][1] = 0;

		int bottom = cleaner.get(1).x;

		for (int x = bottom + 1; x < R - 1; x++) {
			room[x][0] = room[x + 1][0];
		}

		for (int y = 0; y < C - 1; y++) {
			room[R - 1][y] = room[R - 1][y + 1];
		}

		for (int x = R - 1; x > bottom; x--) {
			room[x][C - 1] = room[x - 1][C - 1];
		}

		for (int y = C - 1; y > 1; y--) {
			room[bottom][y] = room[bottom][y - 1];
		}

		room[bottom][1] = 0;

		for (int i = 0; i < room.length; i++) {
			for (int j = 0; j < room[0].length; j++) {
				if (room[i][j] > 0) {
					air.offer(new Position(i, j));
				}
			}
		}

	}
}