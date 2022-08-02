package com.ssafy.hw.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + "\n");

			int num = Integer.parseInt(in.readLine());

			int[][] dalpeng = new int[num][num];
			int x = 0;
			int y = 0;
			int cnt = 1;

			int m1 = 1;
			int m2 = 1;
			int u = 0;

			while (u < num) {
				// 좌우
				for (int l = u; l < num; l++) {
					dalpeng[x][y] = cnt++;
					y = y + m1;
				}
				m1 *= -1;
				y = y + m1;
				x = x + m2;
				// 상하
				for (int m = u + 1; m < num; m++) {
					dalpeng[x][y] = cnt++;
					x = x + m2;
				}
				m2 *= -1;
				x = x + m2;
				y = y + m1;
				u++;
			}

			for (int l = 0; l < num; l++) {
				for (int m = 0; m < num; m++) {
					sb.append(dalpeng[l][m] + " ");
				}
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}

}
