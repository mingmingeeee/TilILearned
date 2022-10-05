package baekjoon;

import java.io.*;
import java.util.*;

public class B_2580 {

	private static int[][] map = new int[9][9];
	private static boolean[] visited;
	private static boolean flag;

	private static StringBuilder sb = new StringBuilder();

	private static List<Position> list = new ArrayList<>();

	private static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				if (map[i][j] == 0)
					list.add(new Position(i, j));
			}
		}
		

		visited = new boolean[list.size()];

		dfs(0);
		
		System.out.println(sb);

	}

	private static void dfs(int cnt) {
		if (flag)
			return;

		if (cnt == list.size()) {
			flag = true;
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
			return;
		}

		if (visited[cnt])
			return;

		visited[cnt] = true;

		int x = list.get(cnt).x;
		int y = list.get(cnt).y;
		for (int i = 1; i <= 9; i++) {
			if (isPossible(x, y, i)) {
				map[x][y] = i;

				dfs(cnt + 1);

				map[x][y] = 0;
			}
		}

		visited[cnt] = false;

	}

	private static boolean isPossible(int x, int y, int num) {

		// 행, 열
		for (int i = 0; i < 9; i++) {
			if (map[i][y] == num || map[x][i] == num)
				return false;
		}

		// 3x3 스도쿠
		int nx = x / 3;
		int ny = y / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (map[3  * nx + i][3 * ny + j] == num)
					return false;
			}
		}

		return true;
	}

}
