package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1527 {

	private static int answer;

	public static void main(String[] args) throws Exception {

		// 금민수: 4, 7로만 이루어진 수
		// A 보다 크거나 같고 B보다 작거나 같은 자연수 => 금민수인 것 출력

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		dfs(A, B, 0);

		System.out.println(answer);
	}

	private static void dfs(int A, int B, long sum) {

		if (B < sum)
			return;
		if (sum >= A && sum <= B)
			answer++;

		// 1. 4로
		dfs(A, B, sum * 10 + 4);

		// 2. 7로
		dfs(A, B, sum * 10 + 7);

	}

}
