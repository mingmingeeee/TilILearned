package baekjoon;

import java.io.*;
import java.util.*;

public class B_5567 {

	// 1. 상근이의 친구
	// 2. 상근이의 친구의 친구 초대

	private static class Edge {
		int from;
		int to;
		Edge next;

		public Edge(int from, int to, Edge next) {
			this.from = from;
			this.to = to;
			this.next = next;
		}
	}

	private static int V;
	private static int E;
	private static boolean[] visited;
	private static Edge[] edgeList;
	private static int answer;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		V = Integer.parseInt(in.readLine()); // 동기의 수
		E = Integer.parseInt(in.readLine()); // 리스트 길이 (간선 길이)

		edgeList = new Edge[V + 1];
		visited = new boolean[V + 1];

		for (int i = 0; i < E; i++) {
			String[] s = in.readLine().split(" ");

			int from = Integer.parseInt(s[0]);
			int to = Integer.parseInt(s[1]);

			edgeList[from] = new Edge(from, to, edgeList[from]);
			edgeList[to] = new Edge(to, from, edgeList[to]);
		}

//		dfs(1, 0);
//
//		for (int i = 2; i < V + 1; i++) {
//			if (visited[i])
//				answer++;
//		}
		bfs(1);

		sb.append(answer);
		System.out.println(sb);

	}

	private static int bfs(int num) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(num);
		visited[num] = true;

		int depth = 0;
		while (!q.isEmpty()) {
			if(depth > 0)
				answer += q.size();
			int size = q.size();

			if (depth == 2)
				return answer;
			else if (depth > 2)
				break;

			for (int i = 0; i < size; i++) {
				int p = q.poll();
				for (Edge temp = edgeList[p]; temp != null; temp = temp.next) {
					if (!visited[temp.to]) {
						visited[temp.to] = true;
						q.offer(temp.to);
					}
				}
			}
			depth++;
		}

		return 0;
	}

//	private static void dfs(int i, int depth) {
//		if (depth == 2) {
//			return;
//		}
//
//		for (Edge temp = edgeList[i]; temp != null; temp = temp.next) {
//			visited[temp.to] = true;
//			dfs(temp.to, depth + 1);
//		}
//	}

}
