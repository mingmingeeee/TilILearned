package baekjoon;

import java.io.*;
import java.util.*;

public class B_17204 {

	private static int N;
	private static int K;
	private static int result;
	private static int[] points;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] s = in.readLine().split(" ");

		N = Integer.parseInt(s[0]);
		K = Integer.parseInt(s[1]);

		points = new int[N];
		for (int i = 0; i < N; i++) {
			points[i] = Integer.parseInt(in.readLine());
		}

		dfs(points[0], 1);

		sb.append(result);
		System.out.println(result);
	}

	private static void dfs(int n, int depth) {
		
		if(depth > N) {
			result = -1;
			return;
		}

		if (n == K) {
			result = depth;
			return;
		}

		dfs(points[n], depth + 1);

	}

}
