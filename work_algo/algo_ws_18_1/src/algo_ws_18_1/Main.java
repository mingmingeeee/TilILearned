package algo_ws_18_1;

import java.io.*;
import java.util.*;

public class Main {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int R, C;
	static Character[][] map;
	static int[][] count;
	static Queue<Position1> water = new ArrayDeque<>();
	static Queue<Position2> q = new ArrayDeque<>();
	static int answer = 0;

	static class Position1 { // 물
		int x;
		int y;

		Position1(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Position2 { // 고슴도치 
		int x;
		int y;
		int time;

		Position2(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new Character[R][C];
		count = new int[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();

			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') {
					q.add(new Position2(i, j, 0));
				} else if (map[i][j] == '*') {
					water.add(new Position1(i, j));
				}
			}

		}

		bfs();

		if(answer == 0)
			sb.append("KAKTUS");
		else
			sb.append(answer);
		
		System.out.println(sb);

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C) {
			return true;
		}
		return false;
	}

	public static void bfs() {

		while (!q.isEmpty()) {
			// 초마다
			
			// 물 퍼짐
			int size = water.size();

			for (int i = 0; i < size; i++) {

				Position1 p = water.poll();

				for (int k = 0; k < 4; k++) {
					int testX = p.x + dx[k];
					int testY = p.y + dy[k];
					if (isRange(testX, testY) && map[testX][testY] == '.') {
						map[testX][testY] = '*';
						water.offer(new Position1(testX, testY));
					}
				}

			}

			// 고슴도치 이동 
			size = q.size();
			for (int i = 0; i < size; i++) {

				Position2 p = q.poll();

				for (int k = 0; k < 4; k++) {
					int testX = p.x + dx[k];
					int testY = p.y + dy[k];
					if (isRange(testX, testY)) {
						if (map[testX][testY] == 'D') {
							answer = p.time + 1; // 이 다음에 만나러 가는 거니까 time 1 증가 
							return;
						} else if (map[testX][testY] == '.') {
							map[testX][testY] = 'S';
							q.offer(new Position2(testX, testY, p.time + 1));
						}
					}
				}

			}
		}

	}
}