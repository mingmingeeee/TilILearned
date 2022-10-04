package algo_ws_22_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

	private static int answer;
	private static int max;
	private static int N;
	private static int W;
	private static int H;

	private static int[] bricks;
	private static int[][] map;

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int i = 1; i <= T; i++) {
			sb.append("#" + i + " ");

			String[] split = in.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			W = Integer.parseInt(split[1]);
			H = Integer.parseInt(split[2]);

			max = 0;
			answer = Integer.MIN_VALUE;

			map = new int[H][W];
			bricks = new int[N];

			int total = 0;
			for (int x = 0; x < H; x++) {
				split = in.readLine().split(" ");
				for (int y = 0; y < W; y++) {
					map[x][y] = Integer.parseInt(split[y]);
					if(map[x][y] > 0)
						total++;
				}
			}

			
			Hp(0);
			System.out.println(total);
			System.out.println(total - answer);

		}

	}

	private static void cleanMap(int[][] map) {
		// ...
	}

	private static void dfs(int[][] map, int x, int y, int count) {

		for (int i = 0; i < 4; i++) {
			int testX = x + dx[i];
			int testY = y + dy[i];

			if (testX < 0 || testX >= H || testY < 0 || testY >= W || count < 0)
				return;

			if (map[testX][testY] > 1) {
				dfs(map, testX, testY, map[testX][testY] - 1);
			}

			max++;
			map[testX][testY] = 0;

			dfs(map, testX, testY, count--);
		}

	}

	private static void Hp(int cnt) {

		if (cnt == N) {

			int[][] tmp = new int[H][W];
			for (int i = 0; i < H; i++)
				tmp[i] = map[i].clone();

			for (int i = 0; i < N; i++) {
				int idx = 0;

				// 벽돌 깨기
				while (idx < W) {

					if (map[bricks[i]++][idx] > 0) {
						dfs(map, bricks[i], idx, map[bricks[i]][idx] - 1);
						break;
					}

				}

				// 빈 공간 정리
				cleanMap(map);

			}


			int sum = 0;
			// 최대 값 갱신
			answer = Math.max(answer, max);
			
			max = 0;

			return;
		}

		for (int i = 0; i < H; i++) {
			bricks[cnt] = i;
			Hp(cnt + 1);
		}

	}

}