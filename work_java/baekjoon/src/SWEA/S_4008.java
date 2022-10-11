package SWEA;

import java.io.*;
import java.util.*;

// 주어진 연산자 카드를 사용하여 수식을 계산했을 때
// 결과가 최대가 되는 수식, 최소가 되는 수식을 찾고 두 값의 차이 출력하기 

public class S_4008 {

	private static final String[] op = { "+", "-", "*", "/" };

	private static int N;
	private static int[] operator;
	private static int[] numbers;

	private static int min;
	private static int max;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(in.readLine());
			numbers = new int[N];
			operator = new int[4];

			String[] split = in.readLine().split(" ");
			for (int i = 0; i < 4; i++) {
				operator[i] = Integer.parseInt(split[i]);
			}

			split = in.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(split[i]);
			}

			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;

			dfs(0, numbers[0]);

			sb.append(max - min).append("\n");

		}
		System.out.println(sb);

	}

	private static void dfs(int cnt, int sum) {

		if (cnt == N - 1) {
			min = Math.min(min, sum);
			max = Math.max(max, sum);

			return;
		}

		for (int i = 0; i < 4; i++) {

			if (operator[i] == 0)
				continue;

			operator[i]--;

			if (i == 0) {
				dfs(cnt + 1, sum + numbers[cnt + 1]);
			} else if (i == 1) {
				dfs(cnt + 1, sum - numbers[cnt + 1]);
			} else if (i == 2) {
				dfs(cnt + 1, sum * numbers[cnt + 1]);
			} else if (i == 3) {
				dfs(cnt + 1, sum / numbers[cnt + 1]);
			}

			operator[i]++;

		}

	}

}
