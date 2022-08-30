package baekjoon;

import java.io.*;
import java.util.*;

// 5개 -> 4개 -> 3개 -> 2개 -> 1개

public class B_17136 {

	static int[][] paper;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = 10;

		paper = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		visited = new boolean[N][N];

		dfs(0, 0, 5);

		System.out.println(answer);
	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < 10 && 0 <= y && y < 10) {
			return true;
		}
		return false;
	}

	private static int answer;
	private static boolean[][] visited;

	private static void dfs(int x, int y, int num) {
		
		// 기저 조건
		if(num == 0)
			return;
		if(!isRange(x, y))
			return;

		int count = 0;
		for (int i = x; i < x + num; i++) {
			for (int j = y; j < y + num; j++) {
				if (paper[i][j] == 1 && !visited[i][j]) {
					visited[i][j] = true;
					count++;
				}
			}
		}

		if (count == num * num) {
			answer++;
		}
		else {
			for (int i = x; i < x + num; i++) {
				for (int j = y; j < y + num; j++) {
					if (paper[i][j] == 1) {
						visited[i][j] = false;
					}
				}
			}
			dfs(x, y, num);
			dfs(x, y + num, num);
			dfs(x + num, y, num);
			dfs(x + num, y + num, num);
		}

	}

}
