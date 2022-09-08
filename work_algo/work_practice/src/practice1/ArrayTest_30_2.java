package practice;

import java.io.*;

public class pr2 {

	public static void main(String[] args) throws Exception {

		int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };

		System.setIn(new FileInputStream("ArrayTest_30_input.txt")); // 문제에서 주어진 Input 데이터 파일명 작성

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine()); // 테스트 케이스

		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");

			// 여기서 부터 알고리즘 작성
			int N = Integer.parseInt(in.readLine());
			char[][] array = new char[N][N];
			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					array[i][j] = split[j].charAt(0);
				}
			}

			// 1. S 적힌 곳 지나갈 수 없음 + W 지나갈 수 없음 + A, B, C지나갈 수 없음
			// 로봇: A, B, C
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int x = i;
					int y = j;

					if (array[i][j] == 'A') { // 0
						y++;
						while (x >= 0 && x < N && y >= 0 && y < N && array[x][y] == 'S') {
							y++;
							count++;
						}
					} else if (array[i][j] == 'B') { // 0, 1
						for (int k = 0; k < 2; k++) {
							y = j + dir[k][1];
							while (x >= 0 && x < N && y >= 0 && y < N && array[x][y] == 'S') {
								y += dir[k][1];
								count++;
							}
						}
					} else if (array[i][j] == 'C') { // 0, 1, 2, 3

						for (int k = 0; k < 4; k++) {
							x = i + dir[k][0];
							y = j + dir[k][1];

							while (x >= 0 && x < N && y >= 0 && y < N && array[x][y] == 'S') {
								x += dir[k][0];
								y += dir[k][1];
								count++;
							}

						}

					}
				}

			}
			System.out.println(count);
		}

	}

}
