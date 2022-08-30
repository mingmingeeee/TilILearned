package baekjoon;

import java.io.*;
import java.util.*;

public class B_2667 {

	// 우, 하, 좌, 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	
	private static int count;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		int home = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				count = 0;
				if (!visited[i][j] && map[i][j] == 1) {
					dfs(i, j);
					home++;
					list.add(count);
				}
			}
		}
		
		sb.append(home).append("\n");
		
		Collections.sort(list);
		
		for(int i : list) {
			sb.append(i).append("\n");
		}
		
		System.out.println(sb);

	}

	private static boolean isRange(int x, int y) {

		if (0 <= x && x < N && 0 <= y && y < N)
			return true;
		return false;

	}

	private static void dfs(int x, int y) {
		
		visited[x][y] = true;
		count++;

		for (int d = 0; d < dx.length; d++) {
			int testX = x + dx[d];
			int testY = y + dy[d];

			if (isRange(testX, testY) && map[testX][testY] == 1 && !visited[testX][testY]) {
				dfs(testX, testY);
			}
		}

	}

}
