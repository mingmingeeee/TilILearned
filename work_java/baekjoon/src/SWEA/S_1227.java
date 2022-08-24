package algo_ws_18_2;

import java.io.*;
import java.util.*;

public class S_1227 {

	private static final int WALL = 1;
	private static final int PATH = 0;
	private static final int START = 2;
	private static final int DEST = 3;

	private static boolean[][] visited;
	private static int[][] map;

	private static int[] dx = { 0, 1, 0, -1 };
	private static int[] dy = { 1, 0, -1, 0 };
	
	private static int answer;

	static class Position {
		int x;
		int y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {

			answer = 0;
			sb.append("#" + test_case + " ");

			int n = Integer.parseInt(br.readLine());

			int N = 100;
			map = new int[N][N];
			visited = new boolean[N][N];

			int x = 0, y = 0;

			for (int i = 0; i < N; i++) {
				String[] split = br.readLine().split("");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
					if (map[i][j] == START) {
						x = i;
						y = i;
					}

				}
			}

			dfs(x, y);

			sb.append(answer).append("\n");

		}

		System.out.println(sb);
	}

	private static boolean isRange(int x, int y) {

		if (0 <= x && x < map.length && 0 <= y && y < map[0].length) {
			return true;
		}
		return false;

	}

	private static void dfs(int x, int y) {
		
		if(map[x][y] == WALL)
			return;

		else if(map[x][y] == DEST) {
			answer = 1;
			return;
		}
		visited[x][y] = true;
  
		for (int d = 0; d < dx.length; d++) {
			int testX = x + dx[d];
			int testY = y + dy[d];
			if (isRange(testX, testY) && !visited[testX][testY]) {
				dfs(testX, testY);
			}
		}

		visited[x][y] = false;

	}

}
