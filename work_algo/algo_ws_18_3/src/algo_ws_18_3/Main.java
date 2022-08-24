package algo_ws_18_3;

import java.io.*;
import java.util.*;

public class Main {

	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static int[][] map;
	static int raw;
	
	static Queue<Position> tomato = new ArrayDeque<>();

	private static class Position {
		int x;
		int y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		int M = Integer.parseInt(split[0]);
		int N = Integer.parseInt(split[1]);

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				if (map[i][j] == 0)
					raw++;
				else if(map[i][j] == 1)
					tomato.offer(new Position(i, j));
			}
		}


		sb.append(bfs());
		
		
		System.out.println(sb);

	}

	private static boolean isRange(int x, int y) {

		if (0 <= x && x < map.length && 0 <= y && y < map[0].length) {
			return true;
		}
		return false;

	}

	static int bfs() {

		boolean[][] visited = new boolean[map.length][map[0].length];

		int depth = 0;
		while (!tomato.isEmpty()) {
			depth++;
			
			int size = tomato.size();


			while (--size >= 0) {
				Position p = tomato.poll();

				for (int i = 0; i < 4; i++) {
					int testX = p.x + dx[i];
					int testY = p.y + dy[i];

					if (isRange(testX, testY) && map[testX][testY] == 0 && !visited[testX][testY]) {
						visited[testX][testY] = true;
						map[testX][testY] = 1;
						if (--raw == 0) 
							return depth;
						tomato.offer(new Position(testX, testY));
					}
				}

			}

		}
		
		if(raw == 0)
			return 0;
		
		return -1;
	}

}
