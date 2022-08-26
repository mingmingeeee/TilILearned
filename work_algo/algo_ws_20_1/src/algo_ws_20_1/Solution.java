package algo_ws_20_1;

import java.io.*;
import java.sql.Array;
import java.util.*;

import javax.xml.transform.Templates;

// 1. 극이 다르면 회전, 같으면 회전 안 됨 (방향 다르게)
// 2. 열두시 방향 극성 보고 점수 판단

// 시계 방향: 1, 반시계 방향: -1
// N극: 0, S극: 1

public class Solution {

	private static int[][] magnet;
	private static int[][] tmp;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int K = Integer.parseInt(in.readLine());

			magnet = new int[4][8];

			for (int i = 0; i < 4; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(split[j]);
				}
			}

			for (int i = 0; i < K; i++) {
				String[] split = in.readLine().split(" ");

				int n = Integer.parseInt(split[0]) - 1;
				int d = Integer.parseInt(split[1]);

				tmp = new int[4][8];
				for (int k = 0; k < 4; k++) {
					for (int j = 0; j < 8; j++) {
						tmp[k][j] = magnet[k][j];
					}
				}
				visited = new boolean[4];

				check_rotate(n, d);

			}

			int result = 0;
			if (magnet[0][0] == 1)
				result += 1;
			if (magnet[1][0] == 1)
				result += 2;
			if (magnet[2][0] == 1)
				result += 4;
			if (magnet[3][0] == 1)
				result += 8;

//			for (int k = 0; k < 4; k++) {
//				for (int j = 0; j < 8; j++) {
//					System.out.println(magnet[k][j]+ " " );
//				}
//				System.out.println();
//			}

			sb.append(result).append("\n");

		}

		System.out.println(sb);

	}

	private static void rotate(int n, int d) {

		int t;
		switch (d) {
		case 1: // 시계
			t = magnet[n][magnet[0].length - 1];
			for (int i = magnet[0].length - 1; i > 0; i--) {
				magnet[n][i] = magnet[n][i - 1];
			}
			magnet[n][0] = t;
			break;

		case -1: // 반시계
//			System.out.print("반시계");
			t = magnet[n][0];
			for (int i = 0; i < magnet[0].length - 1; i++) {
				magnet[n][i] = magnet[n][i + 1];
			}
			magnet[n][magnet[0].length - 1] = t;
			break;

		}

	}

	private static void check_rotate(int n, int d) {
		rotate(n, d); // 나는 돌아감
		visited[n] = true;
		// 극이 다른지 판단
		if (n + 1 < magnet.length && !visited[n + 1]) { // 내 앞이랑 내가 다를 때
			if (tmp[n][2] != tmp[n + 1][magnet[0].length - 2]) {
				check_rotate(n + 1, d * -1);
			}
		}
		if (n - 1 >= 0 && !visited[n - 1]) { // 내 뒤랑 내가 다를 때
			if (tmp[n][magnet[0].length - 2] != tmp[n - 1][2]) {
				check_rotate(n - 1, d * -1);
			}
		}
	}

}
