package hwalgo27_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Solution {

	private static int[][] adjMatrix;
	private static int shortCnt;
	private static int tallCnt;
	private static boolean[] visited1;
	private static boolean[] visited2;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {

			sb.append("#" + tc + " ");

			int N = Integer.parseInt(in.readLine());
			int M = Integer.parseInt(in.readLine());

			adjMatrix = new int[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				String[] split = in.readLine().split(" ");
				int from = Integer.parseInt(split[0]);
				int to = Integer.parseInt(split[1]);
				adjMatrix[from][to] = 1;
				// adjMatrix[from][to] == 1이라면 from < to
			}

			int answer = 0;
			for (int i = 1; i <= N; i++) {

				shortCnt = 0;
				tallCnt = 0;

				visited1 = new boolean[N + 1];
				visited2 = new boolean[N + 1];

				taller(i);
				shorter(i);
//				System.out.println(shortCnt + " " + tallCnt);
				if (shortCnt + tallCnt == N - 1) {
//					System.out.println(i);
					answer++;
				}

			}

			sb.append(answer).append("\n");

		}

		System.out.println(sb);
	}
	
	private static void shorter(int start) {

		visited1[start] = true;

		for (int i = 1; i < adjMatrix.length; i++) {
			if (i == start)
				continue;
			if (adjMatrix[i][start] == 1 && !visited1[i]) { // i < start => start보다 작은 애들
				shorter(i);
				shortCnt++;
			}
		}

	}

	private static void taller(int start) {

		visited2[start] = true;

		for (int i = 1; i < adjMatrix.length; i++) {
			if (i == start)
				continue;
			if (adjMatrix[start][i] == 1 && !visited2[i]) { // i < start => start보다 큰 애들
				taller(i);
				tallCnt++;
			}
		}
	}

}
