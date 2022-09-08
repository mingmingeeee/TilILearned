package baekjoon;

import java.io.*;
import java.util.*;

public class B_1326 {

	private static int[] numbers;

	static class Node {
		int idx;
		int depth;

		public Node(int idx, int depth) {
			this.idx = idx;
			this.depth = depth;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		numbers = new int[N + 1];

		String[] s = in.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			numbers[i] = Integer.parseInt(s[i - 1]);
		}

		s = in.readLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int b = Integer.parseInt(s[1]);

		int answer = bfs(a, b);

		sb.append(answer);
		System.out.println(sb);
	}

	private static int bfs(int a, int b) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[numbers.length];

		queue.add(new Node(a, 0));
		visited[a] = true;

		while (!queue.isEmpty()) {

			Node cur = queue.poll();

			if (cur.idx == b)
				return cur.depth;

			int mult = 1; // 배수
			while (true) { // 오른쪽
				int n = mult * numbers[cur.idx]; // 징검다리에 써져 있는 수 배수만큼
				if (n + cur.idx >= numbers.length)
					break;
				if (!visited[n + cur.idx]) {
					visited[n + cur.idx] = true;
					queue.offer(new Node(n + cur.idx, cur.depth + 1));
				}
				mult++;
			}

			mult = 1; // 배수
			while (true) { // 왼쪽
				int n = mult * numbers[cur.idx]; // 징검다리에 써져 있는 수 배수만큼
				if (cur.idx - n < 1)
					break;
				if (!visited[cur.idx - n]) {
					visited[cur.idx - n] = true;
					queue.offer(new Node(cur.idx - n, cur.depth + 1));
				}
				mult++;
			}

		}

		return -1;

	}

}
