package hwalgo25_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static int M;
	private static int[][] cheese;
	private static int cnt, size, time;

	private static final int[] dx = { 0, 0, -1, 1 };
	private static final int[] dy = { -1, 1, 0, 0 };

	private static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		
		cheese = new int[N][M];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(split[j]);
				if(cheese[i][j] == 1)
					cnt++;
			}
		}
		
		while(cnt > 0) {
			time++;
			size = cnt;
			bfs();
		}
		
		sb.append(time).append("\n").append(size);
		
		System.out.println(sb);

	}

	private static void bfs() {

		Queue<Position> queue = new ArrayDeque<>();
		queue.offer(new Position(0, 0));

		boolean[][] visited = new boolean[N][M];
		visited[0][0] = true;

		while (!queue.isEmpty()) {

			int size = queue.size();

			for (int i = 0; i < size; i++) {

				Position cur = queue.poll();

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX >= 0 && testX < N && testY >= 0 && testY < M && !visited[testX][testY]) {
						if (cheese[testX][testY] == 1) {
							cheese[testX][testY] = 0;
							cnt--;
						} else {
							queue.offer(new Position(testX, testY));
						}
						visited[testX][testY] = true;
					}
				}

			}

		}

	}

}
