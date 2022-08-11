package baekjoon;

import java.io.InputStreamReader;
import java.io.BufferedReader;

public class B_1010 {

	// 메모이제이션 방법으로 풀어야 함 !!!
	// < 조합 특징 >
	// 1. nC0 = 1 || nCn = 1
	// 2. n+1Cr+1 = nCr + nCr+1

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 메모이제이션으로 조합 경우의 수 구하기
		int[][] memo = new int[30][30];

		// 특징 1 -> nCn, nC0 -> 다 ~ 1
		for (int i = 0; i < 30; i++) {
			memo[i][i] = 1;
			memo[i][0] = 1;
		}

		// 특징 2
		for (int i = 2; i < 30; i++) {
			for (int j = 1; j < 30; j++) {
				memo[i][j] = memo[i - 1][j - 1] + memo[i - 1][j];
				// 현재 = 좌측 상단 + 현재 상단
			}
		}

		int T = Integer.parseInt(in.readLine());
		// nCr
		for (int i = 0; i < T; i++) {
			String[] split = in.readLine().split(" ");
			int M = Integer.parseInt(split[0]); // n
			int N = Integer.parseInt(split[1]); // r

			int answer = memo[N][M];

			sb.append(answer);
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
