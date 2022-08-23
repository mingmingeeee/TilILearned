package algo_ws_17_1;

import java.io.*;
import java.util.*;

public class Main {

	// A - B
	// B - C
	// C - D
	// D - E
	// 무향 그래프

	static boolean[] visited;
	static List<List<Integer>> list;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]); // 정점
		int M = Integer.parseInt(split[1]); // 간선

		list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {

			split = in.readLine().split(" ");

			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			list.get(a).add(b);
			list.get(b).add(a);

		}

		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			dfs(i, 0);
			if (flag == 1) {
				System.out.println(1);
				return;
			}
		}

		System.out.println(0);

	}

	static int flag = 0;

	private static void dfs(int start, int depth) {

		if (depth == 4) {
			flag = 1;
			return;
		}

		visited[start] = true;

		for (int i = 0; i < list.get(start).size(); i++) {
			if (!visited[list.get(start).get(i)]) {
				dfs(list.get(start).get(i), depth + 1);
			}
		}

		visited[start] = false;

	}

}
