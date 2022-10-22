package baekjoon;

import java.io.*;
import java.util.*;

// 양팔 저울과 몇 개의 추 => 주어진 구슬의 무게 확인

public class Main_2629 {

	private static int[] weights;
	private static int[] check_weights;
	private static boolean dp[][]; // 만들 수 있는 무게인지 저장해주는 dp 배열

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine()); // 추의 개수 : 30 이하
		weights = new int[N];

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		int max = 0;
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
			max += weights[i];
		}

		int M = Integer.parseInt(in.readLine()); // 확인하고자 하는 구슬들의 개수 : 7 이하
		check_weights = new int[M];

		// 확인 가능: 수평일 때
		// 풀 수 있는 방법: 조합 => 그대로 풀면 폭발함 30C15일 때... => DP로 풀어야 함
		// 조합 최적화 => DP
		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < M; i++) {
			check_weights[i] = Integer.parseInt(st.nextToken());
			max += check_weights[i];
		}

		dp = new boolean[N + 1][max + 1];

		dp(0, 0);

		for (int i = 0; i < M; i++) {
			if (check_weights[i] > max) {
				sb.append("N").append(" ");
				continue;
			}
			if (dp[N][check_weights[i]])
				sb.append("Y").append(" ");
			else
				sb.append("N").append(" ");
		}

		// 출력: 각 구슬의 무게에 대해 확인 가능: Y, 아니면: N

		System.out.println(sb);
	}

	// bottom-up
	private static void dp(int cnt, int weight) {
		if (dp[cnt][weight])
			return;

		dp[cnt][weight] = true;

		if (cnt == weights.length)
			return;
		
		// 오른쪽 추가: +, 왼쪽 추가: -
		dp(cnt + 1, weight + weights[cnt]); // 추 쪽에 올리기
		dp(cnt + 1, weight); // 아무쪽에도 올리지 않기
		dp(cnt + 1, Math.abs(weight - weights[cnt])); // 체크해야하는 추쪽에 올리기

	}

}
