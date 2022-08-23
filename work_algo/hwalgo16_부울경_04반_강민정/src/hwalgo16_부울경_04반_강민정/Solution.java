package hwalgo16_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Solution {

	// 정보: 비상 연락망과 연락을 시작하는 당번
	// 가장 나중에 연락을 받게 되는 사람 & 번호가 가장 큰 사람
	// 방향 그래프

	static List<List<Integer>> graph;
	static int tmp;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {

			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");

			int N = Integer.parseInt(split[0]);
			int start = Integer.parseInt(split[1]);

			graph = new ArrayList<>();

			for (int i = 0; i < 100; i++) {
				graph.add(new ArrayList<>());
			}

			split = in.readLine().split(" ");
			for (int i = 0; i < N; i += 2) {
				int from = Integer.parseInt(split[i]) - 1;
				int to = Integer.parseInt(split[i + 1]) - 1;
				graph.get(from).add(to);
			}

			boolean[] visited = new boolean[100];

			bfs(start - 1, visited);

			sb.append(tmp + 1).append("\n");

		}
		System.out.println(sb);

	}

	private static void bfs(int start, boolean[] visited) {

		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		visited[start] = true;

		int depth = 0;

		int max = 0;
		tmp = 0;

		while (!queue.isEmpty()) {

			tmp = max;
			max = 0;
			int size = queue.size();

			for (int j = 0; j < size; j++) {
				int c = queue.poll();

				for (int i = 0; i < graph.get(c).size(); i++) {
					if (!visited[graph.get(c).get(i)]) {
						queue.offer(graph.get(c).get(i));
						visited[graph.get(c).get(i)] = true;
						max = Math.max(max, graph.get(c).get(i));
					}
				}
			}

		}

	}

}
