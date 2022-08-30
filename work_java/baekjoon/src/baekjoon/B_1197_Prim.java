package baekjoon;

import java.io.*;
import java.util.*;

// 프림

public class B_1197_Prim {

	private static class Node implements Comparable<Node> {

		int no;
		int weight;
		Node next;

		public Node(int no, int weight, Node next) {
			this.no = no;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}

	private static int V;
	private static int E;
	private static Node[] adjList;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		V = Integer.parseInt(split[0]);
		E = Integer.parseInt(split[1]);

		adjList = new Node[V];

		for (int i = 0; i < E; i++) {
			split = in.readLine().split(" ");

			int from = Integer.parseInt(split[0]) - 1;
			int to = Integer.parseInt(split[1]) - 1;
			int weight = Integer.parseInt(split[2]);

			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}

		int[] minEdge = new int[V];
		boolean[] visited = new boolean[V];

		PriorityQueue<Node> pQueue = new PriorityQueue<>();
		pQueue.offer(new Node(0, 0, null));

		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[0] = 0;

		int count = 0;
		int result = 0;
		while (!pQueue.isEmpty()) {

			Node p = pQueue.poll();

			if (visited[p.no])
				continue;

			visited[p.no] = true;
			result += p.weight;

			if (count++ == V - 1)
				break;

			for (Node temp = adjList[p.no]; temp != null; temp = temp.next) {
				if (!visited[temp.no] && minEdge[temp.no] > temp.weight) {
					minEdge[temp.no] = temp.weight;
					pQueue.offer(new Node(temp.no, minEdge[temp.no], null));
				}
			}

		}

		System.out.println(result);

	}

}
