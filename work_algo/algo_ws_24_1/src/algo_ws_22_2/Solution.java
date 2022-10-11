package algo_ws_22_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Solution {

	private static int n;
	private static int W;
	private static int H;
	private static int min;

	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static class Position {
		int x;
		int y;
		int cnt;

		public Position(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			String[] split = in.readLine().split(" ");
			n = Integer.parseInt(split[0]);
			H = Integer.parseInt(split[1]);
			W = Integer.parseInt(split[2]);

			min = Integer.MAX_VALUE;

			int[][] map = new int[W][H];

			for (int i = 0; i < W; i++) {
				split = in.readLine().split(" ");
				for (int j = 0; j < H; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}

			go(map, 0);

			sb.append(min).append("\n");
		}

		System.out.println(sb);
	}

	// 순열로 뽑기
	private static void go(int[][] map, int cnt) {

		if (cnt == n) {
			// 4. 남은 벽돌 개수 구해서 비교
			min = Math.min(min, count(map));
			return;
		}

		// 1. 떨어질 벽돌 찾기
		int startX = 0;
		int startY = 0;

		int[][] newMap = new int[W][H];
		for (int y = 0; y < H; y++) {
			boolean isDropped = false;
			for (int x = 0; x < W; x++) {

				if (map[x][y] > 0) {
					copy(map, newMap);

					startX = x;
					startY = y;
					isDropped = true;

					// 2. 연쇄 반응 => 벽돌 없어지는 거
					bfs(startX, startY, newMap);

					// 3. 벽돌 정리
					clean(newMap);

					go(newMap, cnt + 1);
					break;

				}

			}
			if (!isDropped) // 부딪힐 벽돌이 없는 상태
				go(map, cnt + 1);
		}

	}

	private static int count(int[][] map) {
		// TODO Auto-generated method stub
		int count = 0;
		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				if (map[x][y] > 0)
					count++;
			}
		}
		return count;
	}

	private static void clean(int[][] map) {
		Stack<Position> stack = new Stack<>();

		for (int y = 0; y < H; y++) {
			for (int x = 0; x < W; x++) {
				if (map[x][y] > 0) {
					stack.push(new Position(x, y, map[x][y]));
					map[x][y] = 0;
				}
			}
		}

		while (!stack.isEmpty()) {

			Position cur = stack.pop();
			int y = cur.y;

			for (int x = W - 1; x >= 0; x--) {
				if (map[x][y] == 0) {
					map[x][y] = cur.cnt;
					break;
				}
			}

		}

	}

	private static void bfs(int startX, int startY, int[][] map) {
		Queue<Position> queue = new ArrayDeque<>();
		if (map[startX][startY] > 1)
			queue.offer(new Position(startX, startY, map[startX][startY]));

		boolean[][] visited = new boolean[W][H];
		visited[startX][startY] = true;
		map[startX][startY] = 0;

		while (!queue.isEmpty()) {

			Position cur = queue.poll();

			// 4방향
			for (int d = 0; d < 4; d++) {
				int testX = cur.x;
				int testY = cur.y;

				// cnt - 1까지 탐색 
				for (int i = 0; i < cur.cnt - 1; i++) {
					testX += + dx[d];
					testY += + dy[d];
					if (testX >= 0 && testX < W && testY >= 0 && testY < H && 
							map[testX][testY] > 0 && !visited[testX][testY]) {
						if (map[testX][testY] > 1) {
							queue.offer(new Position(testX, testY, map[testX][testY]));
						}
						visited[testX][testY] = true;
						map[testX][testY] = 0;
					}
				}
			}

		}

	}

	private static void copy(int[][] map, int[][] newMap) {
		// TODO Auto-generated method stub
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				newMap[i][j] = map[i][j];
			}
		}
	}

}