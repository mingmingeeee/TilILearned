package baekjoon;

import java.io.*;
import java.util.*;

public class B_1197 {

	private static class Edge implements Comparable<Edge> {

		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}

	private static void make() {
		parents = new int[V];

		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return a = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	private static int[] parents;
	private static int V;
	private static int E;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		V = Integer.parseInt(split[0]);
		E = Integer.parseInt(split[1]);

		List<Edge> list = new ArrayList<>(); // 간선 리스트 -> 크루스칼

		for (int i = 0; i < E; i++) {
			split = in.readLine().split(" ");

			int from = Integer.parseInt(split[0]) - 1;
			int to = Integer.parseInt(split[1]) - 1;
			int weight = Integer.parseInt(split[2]);

			list.add(new Edge(from, to, weight));
		}

		Collections.sort(list);

		make();

		int result = 0;
		int k = 0;
		for (Edge e : list) {
			if (union(e.from, e.to)) {
				result += e.weight;

				if (++k == V - 1)
					break;
			}
		}
		
		System.out.println(result);

	}

}
