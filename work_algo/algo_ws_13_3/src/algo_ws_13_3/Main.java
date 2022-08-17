package algo_ws_13_3;

import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int min = Integer.MAX_VALUE;

	static int[] deltas;
	static int[][] map;
	static List<Position> cctv = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				if (0 < map[i][j] && map[i][j] < 6) {
					cctv.add(new Position(i, j, map[i][j]));
				}
			}
		}
		deltas = new int[cctv.size()];

		perm(0);

		System.out.println(min);

	}

	// 감시: 9
	static void dfs(int x, int y, int[][] data, int d) {

		int testX = x + dx[d];
		int testY = y + dy[d];
		if (testX >= 0 && testX < data.length && testY >= 0 && testY < data[0].length) {
			if (data[testX][testY] == 6)
				return;
			if (data[testX][testY] == 0)
				data[testX][testY] = 9;
			dfs(testX, testY, data, d);
		}

	}

	static void find() {

		int[][] tmp = new int[map.length][map[0].length];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[0].length; j++) {
				tmp[i][j] = map[i][j];
			}
		}

		for (int i = 0; i < cctv.size(); i++) {
			int n = cctv.get(i).data;

			switch (n) {
			case 1:
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, deltas[i]);
				break;

			case 2:
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, deltas[i]);
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, (deltas[i] + 2) % 4);
				break;

			case 3:
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, deltas[i]);
				if (deltas[i] == 0)
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, dx.length-1);
				else
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, deltas[i]-1);

				break;

			case 4:
				if (deltas[i] == 0) {
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 0);
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 2);
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 3);
				} else if (deltas[i] == 1) {
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 1);
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 3);
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 0);
				} else if (deltas[i] == 2) {
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 0);
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 2);
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 1);
				} else if (deltas[i] == 3) {
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 1);
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 3);
					dfs(cctv.get(i).x, cctv.get(i).y, tmp, 2);
				}
				break;

			case 5:
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, 0);
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, 1);
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, 2);
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, 3);
				break;
			}

		}

		int count = 0;
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[0].length; j++) {
				if (tmp[i][j] == 0)
					count++;
			}
		}
		if (min > count)
			min = count;
	}

	static void perm(int cnt) {

		if (cnt == cctv.size()) {
			find();
			return;
		}

		for (int i = 0; i < dx.length; i++) {
			deltas[cnt] = i;
			perm(cnt + 1);
		}

	}

}

class Position {
	int x;
	int y;
	int data;

	Position(int x, int y, int data) {
		this.x = x;
		this.y = y;
		this.data = data;
	}

}
