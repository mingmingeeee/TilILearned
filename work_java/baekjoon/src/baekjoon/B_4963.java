package baekjoon;

import java.io.*;
import java.util.*;

public class B_4963 {

	static int[] dx = { 1, -1, 0, 0, 1, 1, -1, -1 };
	static int[] dy = { 0, 0, 1, -1, -1, 1, 1, -1 };

	static boolean[][] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			String[] split = in.readLine().split(" ");

			int N = Integer.parseInt(split[0]);

			if (N == 0)
				break;

			int M = Integer.parseInt(split[1]);

			int[][] map = new int[M][N];
			visited = new boolean[M][N];
			for (int i = 0; i < M; i++) {
				split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}

			int count = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1 && !visited[i][j]) {
						bfs(map, i, j);
						count++;
					}
				}
			}

			sb.append(count).append("\n");

		}
		System.out.println(sb);
	}

	static void bfs(int[][] map, int x, int y) {

		Queue<Cur1> q = new ArrayDeque<>();
		q.offer(new Cur1(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {

			Cur1 p = q.poll();

			for (int i = 0; i < dx.length; i++) {
				int testX = p.x + dx[i];
				int testY = p.y + dy[i];

				if (testX >= 0 && testX < map.length && testY >= 0 && testY < map[0].length && 
						map[testX][testY] == 1 && !visited[testX][testY]) {
					q.offer(new Cur1(testX, testY));
					visited[testX][testY] = true;
				}
			}

		}

	}

}

class Cur1 {

	int x;
	int y;

	Cur1(int x, int y) {
		this.x = x;
		this.y = y;
	}

}