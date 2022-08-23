package SWEA;

import java.io.*;
import java.util.*;

public class S_2117 {

	// N: 5 이상 20 이하
	// 지불할 수 있는 비용 M: 1 이상 10 이하

	// 보안 회사 이익 = 집 개수 * M - 운영 비용
	// 운영 비용: K * K + (K - 1) * (K - 1) <- K: 서비스 영역

	private static final int HOME = 1;
	private static final int BLANK = 0;
	private static int[][] map;
	private static int N;
	private static int max;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			int M = Integer.parseInt(split[1]);

			map = new int[N][N];
			max = 0;

			for (int i = 0; i < N; i++) {
				split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}

			/**
			 * 알고리즘 풀기
			 */
			
			

			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {

					int profit = 0;
					int K = 1;

					while (K <= N + 1) {
						// 마름모: 나를 중심으로
						int m_cost = K * K + (K - 1) * (K - 1);
						int home_count = 0;

						int startX = x - K + 1;
						int startY = y;

						int idx = 0;
						boolean isPlus = false;
						while (startX < x + K) {

							startY = y - idx;
							for (int i = startY; i < startY + 2 * idx + 1; i++) {
								if (isRange(startX, i) && map[startX][i] == HOME) {
									home_count++;
								}
							}

							if (!isPlus) {
								++idx;
							} else {
								--idx;
							}

							if (K - 1 == idx)
								isPlus = true;

							++startX;
						}

						profit = M * home_count - m_cost;
						

						if (profit >= 0 && max < home_count) 
							max = home_count;
						K++;
					}

				}
			}

			sb.append(max).append("\n");

		}
		
		System.out.println(sb);

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N) {
			return true;
		}
		return false;
	}

}
