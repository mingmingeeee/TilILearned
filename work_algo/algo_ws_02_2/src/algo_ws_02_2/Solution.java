package algo_ws_02_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	// 아래로 이동 하고 좌우 이동 가능하면 이동하고 그 다음 부터는 다시 애라로
	// 아래
	// 좌우
	// 아래
	// 반복...

	// 1. 아래 검사
	// 2. 좌우 검사 -> 한 방향만 되게 되어 있음
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(in.readLine());

		int T = 10;

		int[][] ladar = new int[100][100];

		for (int i = 0; i < 100; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < 100; j++) {
				ladar[i][j] = Integer.parseInt(st.nextToken());
			}
		}

//		for (int i = 1; i <= 10; i++) {
//			sb.append("#" + i + " ");
//
//			/**
//			 * -> 출발은 항상 [0][0]~[0][99]
//			 */
//			
//	
//			for (int y = 99; y >= 0; y--) {
//
//				if (ladar[99][y] == 2) {
//					
//					int testX = 99;
//					int testY = y;
//					while (true) {
//						for (int k = 0; k < dir_x.length; k++) {
//							
//							while (testX >= 0 && testX < 100 && testY >= 0 && testY < 100) {
//
//								if (ladar[testX][testY] == 1 || ladar[testX][testY] == 2) {
//									ladar[testX][testY] = 3;
//									testX += dir_x[k];
//									testY += dir_y[k];
//								} else {
//									break;
//								}
//
//							}
//						}
//
//						if (testX == 0) {
//							System.out.println(y);
//							return;
//						}
//					
//					}
//
//				}

		for (int y = 0; y < 100; y++) {
			int testX = 0;
			int testY = y;
			System.out.println(testX + " " + testY);
			// 1) 아래로 가다가 좌우 만나면 좌우로
			while (testX >= 0 && testX < 100 && testY >= 0 && testY < 100 && ladar[testX][testY] == 1) {

				// 우로
				if (testX >= 0 && testX < 100 && testY + 1 < 100 && ladar[testX][testY + 1] == 1) {
					System.out.println(testX + " " + testY);
					while (testY + 1 < 100 && ladar[testX][testY + 1] == 1) {
						if (ladar[testX][testY + 1] == 1) {
							ladar[testX][testY + 1] = 3;
							testY++;
						}
					} // 좌로
				} else if (testX >= 0 && testX < 100 && testY - 1 >= 0 && ladar[testX][testY - 1] == 1) {
					System.out.println(testX + " " + testY);
					while (testY - 1 >= 0 && ladar[testX][testY - 1] == 1) {
						if (ladar[testX][testY - 1] == 1) {
							ladar[testX][testY - 1] = 3;
							testY--;
						}
					}
				}
				// 처음에는 아래로
				else {
					ladar[testX + 1][testY] = 3;
					testX++;
					}
				if (ladar[testX][testY] == 2) {
					System.out.println(y);
				}
				
			}

		}

	}
}