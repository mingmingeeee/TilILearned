package baekjoon;

import java.io.*;
import java.util.*;

public class B_17352 {

	// 1. N개의 섬
	// 2. N - 1개의 다리 잇는 중
	// 3. 어떤 두 섬 사이든 다리로 왕복 가능
	// 4. 욱제가 다리 하나 무너뜨림
	// 5. 빨리...섬 다리 이어야 함

	static class Edge {
		int from;
		int to;
		Edge next;

		public Edge(int from, int to, Edge next) {
			this.from = from;
			this.to = to;
			this.next = next;
		}
	}

	private static Edge[] edgeList;
	private static int[] parents;
	private static int N;

	private static void make() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		edgeList = new Edge[N + 1];

		make();

		for (int i = 0; i < N - 2; i++) {
			String[] s = in.readLine().split(" ");
			int from = Integer.parseInt(s[0]);
			int to = Integer.parseInt(s[1]);

			edgeList[from] = new Edge(from, to, edgeList[from]);
			edgeList[to] = new Edge(to, from, edgeList[to]);

			union(from, to);
		}

		for (int i = 2; i <= N; i++) {
			if (find(1) != find(i)) {
				sb.append(1 + " " + i);
				break;
			}
		}

		System.out.println(sb);

	}

}
