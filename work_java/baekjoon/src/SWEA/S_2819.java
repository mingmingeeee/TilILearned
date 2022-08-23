package SWEA;

import java.io.*;
import java.util.*;

public class S_2819 {
	
	static private class Position{
		int x;
		int y;
		String string = "";
		
		public Position(int x, int y, String string) {
			this.x = x;
			this.y = y;
			this.string = string;
		}
	}


	static int[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			set.clear();
			
			sb.append("#" + test_case + " ");

			int N = 4;
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, 0, "");
				}
			}

			sb.append(set.size()).append("\n");
		}
		System.out.println(sb);

	}
	static int count;
	static Set<String> set = new HashSet<String>();
	private static boolean[][] visited = new boolean[4][4];
	
	private static void dfs(int x, int y, int depth, String string) {

		// 기저 조건 -> 7일 때 끝
		if(depth == 7) {
			set.add(string);
			return;
		}
		
		// 유도 부분
		for (int d = 0; d < dx.length; d++) {
			int testX = x + dx[d];
			int testY = y + dy[d];
			if (isRange(testX, testY) && !visited[testX][testY]) {
				String s = string + Integer.toString(map[testX][testY]);
				dfs(testX, testY, depth + 1, s);
			}
		}

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < map.length && 0 <= y && y < map[0].length) {
			return true;
		}
		return false;
	}

}

