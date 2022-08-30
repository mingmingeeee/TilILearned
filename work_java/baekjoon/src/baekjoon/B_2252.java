package baekjoon;

import java.io.*;
import java.util.*;

public class B_2252 {

	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}
	}

	private static int N;
	private static int M;
	private static Node[] adjList;
	private static int[] inDegree;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		N = Integer.parseInt(split[0]); // 학생 수
		M = Integer.parseInt(split[1]); // 키를 비교한 횟수

		adjList = new Node[N + 1];
		inDegree = new int[N + 1];

		for (int i = 0; i < M; i++) {
			// A, B 주어지는데 -> 학생 A가 학생 B앞에 서야 한다는 의미
			split = in.readLine().split(" ");
			int from = Integer.parseInt(split[0]);
			int to = Integer.parseInt(split[1]);

			adjList[from] = new Node(to, adjList[from]);
			++inDegree[to];
		}

		ArrayList<Integer> list = topologySort();

		for (int a : list) {
			sb.append(a + " ");
		}

		System.out.println(sb);
	}

	private static ArrayList<Integer> topologySort() {
		ArrayList<Integer> sorted = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();
		

		for (int i = 1; i <= N; i++) {
			if (inDegree[i] == 0) {
				queue.offer(i);
			}
		}

		while (!queue.isEmpty()) {

			int cur = queue.poll();

			sorted.add(cur);

			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {

				if (--inDegree[temp.vertex] == 0)
					queue.offer(temp.vertex);

			}

		}

		return sorted;
	}

}
