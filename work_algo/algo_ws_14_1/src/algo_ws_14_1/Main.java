package algo_ws_14_1;

import java.io.*;
import java.util.*;

public class Main {

	static int[] dx = { -1, 0, 1 };
	static int[] dy = { 1, 1, 1 };
	static char[][] map;
	static int answer;
	static boolean check;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int R = Integer.parseInt(split[0]);
		int C = Integer.parseInt(split[1]);

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			char[] input = in.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = input[j];
			}
		}

		for (int i = 0; i < map.length; i++) {
			check = false;
			dfs(i, 0);
		}

		sb.append(answer);
		System.out.println(sb);
	}

	static void dfs(int x, int y) {

		// 유망하지 않다면 돌아감
		if (0 > x || x >= map.length || 0 > y || y >= map[0].length || map[x][y] != '.' || check)
			return;

		// 답 체크
		if (y == map[0].length - 1) {
			check = true;
			answer++;
			return;
		}
		
		// 유망하다면
		map[x][y] = '#';
		for (int i = 0; i < dx.length; i++) {
			int testX = x + dx[i];
			int testY = y + dy[i];
			dfs(testX, testY);
		}

	}

}

