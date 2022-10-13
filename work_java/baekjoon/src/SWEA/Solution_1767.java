package SWEA;

import java.io.*;
import java.util.*;

public class Solution_1767 {

	// 전선 교차 X
	// 상, 하, 좌, 우로 연결 가능
	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static int N;
	private static int[][] map;
	private static boolean[][] visited;
	private static List<Position> pro;
	private static int min;

	private static Position[] tmp;

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

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			// 답: 최대한 많은 코어 전원에 연결했을 때 전선길이의 합
			// => 여러 방법 있을 경우?전설 길이 합이 최소 되는 값 구하기

			/// 입력 ///
			N = Integer.parseInt(in.readLine());
			pro = new ArrayList<>();
			visited = new boolean[N][N];
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						visited[i][j] = true;
						if (i == N - 1 || j == N - 1 || i == 0 || j == 0) {
							continue;
						}
						pro.add(new Position(i, j));
					}
				}
			}

			int count = pro.size();

			// 모든 processor 개수부터 시작
			for (int i = count; i >= 1; i--) {
				min = Integer.MAX_VALUE;

				tmp = new Position[i];
				comb(0, 0, i);

				if (min != Integer.MAX_VALUE)
					break;
			}

			sb.append(min).append("\n");

		}

		System.out.println(sb);
	}

	private static void comb(int cnt, int start, int n) {
		if (cnt == n) {
			dfs(0, 0);
			return;
		}

		for (int i = start; i < pro.size(); i++) {
			tmp[cnt] = pro.get(i);
			comb(cnt + 1, i + 1, n);
		}

	}

	private static void dfs(int cnt, int sum) {
		if (cnt == tmp.length) {
			min = Math.min(min, sum);
			return;
		}

		for (int d = 0; d < 4; d++) {

			boolean isConnected = false;
			int k = 1;
			while (true) {
				int testX = tmp[cnt].x + dx[d] * k;
				int testY = tmp[cnt].y + dy[d] * k;

				if (testX >= N || testY >= N || testX < 0 || testY < 0) {
					isConnected = true;
					break;
				}
				if (visited[testX][testY]) {
					break;
				}

				visited[testX][testY] = true;
				k++;
			}

			k--;

			if (isConnected)
				dfs(cnt + 1, sum + k);

			// 되돌리기
			while (true) {
				int testX = tmp[cnt].x + dx[d] * k;
				int testY = tmp[cnt].y + dy[d] * k;

				if (testX == tmp[cnt].x && testY == tmp[cnt].y) {
					break;
				}
				if (visited[testX][testY])
					visited[testX][testY] = false;
				k--;
			}

		}
	}

}
