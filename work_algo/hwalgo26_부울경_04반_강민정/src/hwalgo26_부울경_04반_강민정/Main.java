package hwalgo26_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main {
	
	private static final int INF = 999999;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(in.readLine());

		for (int tc = 0; tc < t; tc++) {

			int n = Integer.parseInt(in.readLine());

			// 편의점 들를 때: 빈 병은 버리고 새 맥주 병 살 수 있음
			// 박스에는: 20병 넘길 수 없음
			// 편의점 나선 직후: 50미터 가기 전에 맥주 한 병 더 사야 함

			int[][] data = new int[n + 2][2];
			for (int i = 0; i < n + 2; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < 2; j++) {
					data[i][j] = Integer.parseInt(split[j]);
				}
			}

			int[][] D = new int[n + 2][n + 2];
			for (int i = 0; i < n + 2; i++) {
				for (int j = i + 1; j < n + 2; j++) {
					D[i][j] = D[j][i] = INF;
					int d = Math.abs(data[i][0] - data[j][0]) + Math.abs(data[i][1] - data[j][1]);

					if (d <= 1000) {
						D[i][j] = D[j][i] = 1;
					}
				}
			}

			for (int k = 0; k < n + 2; k++) {
				for (int i = 0; i < n + 2; i++) {
					for (int j = 0; j < n + 2; j++) {
						D[i][j] = Math.min(D[i][k] + D[k][j], D[i][j]);
					}
				}
			}

			String answer = D[0][n + 1] > 0 && D[0][n + 1] <INF ? "happy" : "sad";

			sb.append(answer).append("\n");

		}

		System.out.println(sb);
	}

}
