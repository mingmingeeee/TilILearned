package src.com.ssafy.hw.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] sum = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					sum[i][j] = sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1] + Integer.parseInt(st.nextToken());
				}
			}

			int result = 0;
			int max = 0;
			for (int i = 1; i <= N - M + 1; i++) {
				for (int j = 1; j <= N - M + 1; j++) {
					result = sum[i + M - 1][j + M - 1] - sum[i + M - 1][j - 1] - sum[i - 1][j + M - 1]
							+ sum[i - 1][j - 1];
					if (max < result) {
						max = result;
					}
				}
			}
			sb.append(max).append("\n");

		}
		System.out.println(sb);

	}

}
