package baekjoon;

import java.io.*;
import java.util.*;

public class B_11724 {

	static List<List<Integer>> list;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		list = new ArrayList<>();

		visited = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			split = in.readLine().split(" ");
			list.get(Integer.parseInt(split[0])).add(Integer.parseInt(split[1]));
			list.get(Integer.parseInt(split[1])).add(Integer.parseInt(split[0]));
		}

		int count = 0;
		for (int i = 1; i < N + 1; i++) {
			if (!visited[i]) {
				bfs(i);
				count++;
			}
		}

		System.out.println(count);

	}

	static void bfs(int x) {

		Queue<Integer> q = new ArrayDeque<>();

		q.offer(x);
		visited[x] = true;
		while (!q.isEmpty()) {

			int testX = q.poll();
			visited[testX] = true;

			for (int j = 0; j < list.get(testX).size(); j++) {
				int idx = list.get(testX).get(j);
				if (!visited[idx]) {
					visited[idx] = true;
					q.offer(list.get(testX).get(j));
				}
			}

		}
	}

}
