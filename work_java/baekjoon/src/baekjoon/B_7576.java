package baekjoon;

import java.io.*;
import java.util.*;

public class B_7576 {

	static int count = 0;
	static Queue<Position2> q = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] split = in.readLine().split(" ");

		int M = Integer.parseInt(split[0]);
		int N = Integer.parseInt(split[1]);

		int[][] tomato = new int[N][M];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				tomato[i][j] = Integer.parseInt(split[j]);
				if (tomato[i][j] == 0)
					count++;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (tomato[i][j] == 1)
					q.offer(new Position2(i, j, 0));
			}
		}

		bfs(tomato);

		if (count != 0)
			sb.append(-1);

		System.out.println(sb);
	}

	static void bfs(int[][] tomato) {

		while (!q.isEmpty()) {

			Position2 p = q.poll();

			for (int i = 0; i < dx.length; i++) {
				int testX = p.x + dx[i];
				int testY = p.y + dy[i];

				if (testX >= 0 && testX < tomato.length && testY >= 0 && testY < tomato[0].length) {
					if (tomato[testX][testY] == 0) {
						tomato[testX][testY] = 1;
						q.offer(new Position2(testX, testY, p.depth + 1));
						count--;
					}
				}

			}

			if (count == 0) {
				if (p.depth > 0)
					sb.append(p.depth + 1);
				else
					sb.append(p.depth);
				return;
			}

		}

	}
}

class Position2 {

	int x;
	int y;
	int depth;

	public Position2(int x, int y, int depth) {

		this.x = x;
		this.y = y;
		this.depth = depth;
	}

}