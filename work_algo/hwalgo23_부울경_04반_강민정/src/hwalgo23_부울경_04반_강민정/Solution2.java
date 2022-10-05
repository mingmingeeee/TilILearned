package hwalgo23_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Solution2 {

	private static final int INF = Integer.MAX_VALUE;
	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static int[][] map;
	private static int N;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(in.readLine());
			
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				char[] tmp = in.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = tmp[j] - '0';
				}
			}

			sb.append(daijkstra(0, 0));
			sb.append("\n");

		}

		System.out.println(sb);
	}

	private static int daijkstra(int startX, int startY) {

		int[][] min = new int[N][N];
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				min[i][j] = INF;
			}
		}

		min[startX][startY] = 0;

		int minTime;
		int x = 0, y = 0, nx, ny;

		while (true) {

			minTime = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && minTime > min[i][j]) {
						minTime = min[i][j];
						x = i;
						y = j;
					}
				}
			}

			if (x == -1 || y == -1)
				return -1;

			visited[x][y] = true;

			if (x == N - 1 && y == N - 1)
				return minTime;

			for (int d = 0; d < 4; d++) {
				nx = x + dx[d];
				ny = y + dy[d];
				
				if(nx >= 0 && nx < N && ny >= 0 && ny < N && min[nx][ny] > minTime + map[nx][ny]) {
					min[nx][ny] = minTime + map[nx][ny];
				}
			}

		}

	}

}
