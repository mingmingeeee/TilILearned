package baekjoon;

import java.io.*;
import java.util.*;

public class B_2887 {

	private static int N;
	private static ArrayList<Edge> edgeList = new ArrayList<>();
	private static int[] parents;
	private static Position[] positions;

	private static class Edge implements Comparable<Edge> {
		int weight;
		int from;
		int to;

		public Edge(int weight, int from, int to) {
			this.weight = weight;
			this.from = from;
			this.to = to;
		}

		public int compareTo(Edge o) {
			return this.weight - o.weight;
		};
	}

	private static class Position {
		int index;
		int x;
		int y;
		int z;

		public Position(int index, int x, int y, int z) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	private static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
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
		positions = new Position[N];
		
		for(int i=0; i<N; i++) {
			String[] s = in.readLine().split(" ");
			int x = Integer.parseInt(s[0]);
			int y = Integer.parseInt(s[1]);
			int z = Integer.parseInt(s[2]);
			
			positions[i] = new Position(i, x, y, z);
		}
		
		Arrays.sort(positions, (p1, p2) -> p1.x - p2.x);
		for(int i=0; i<N-1; i++) {
			int weight = Math.abs(positions[i].x - positions[i + 1].x);
			edgeList.add(new Edge(weight, positions[i].index, positions[i+1].index));
		}
		
		Arrays.sort(positions, (p1, p2) -> p1.y - p2.y);
		for(int i=0; i<N-1; i++) {
			int weight = Math.abs(positions[i].y - positions[i + 1].y);
			edgeList.add(new Edge(weight, positions[i].index, positions[i+1].index));
		}
		
		Arrays.sort(positions, (p1, p2) -> p1.z - p2.z);
		for(int i=0; i<N-1; i++) {
			int weight = Math.abs(positions[i].z - positions[i + 1].z);
			edgeList.add(new Edge(weight, positions[i].index, positions[i+1].index));
		}
		
		make();
		
		Collections.sort(edgeList); // 간선 기준 오름차순 정렬
		
		int result = 0;
		int count = 0;
		
		for(Edge edge : edgeList) {
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				if(++count == N - 1) break;
			}
		}
		
		System.out.println(result);
		
	}

}
