package baekjoon;

import java.io.*;
import java.util.*;

public class B_18352 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]); // 도시의 개수
		int M = Integer.parseInt(split[1]); // 도로의 개수
		int K = Integer.parseInt(split[2]); // 최단 거리
		int X = Integer.parseInt(split[3]); // 출발 도시 번호

		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		int[] distance = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
			distance[i] = -1;
		}

		for (int i = 0; i < M; i++) {
			split = in.readLine().split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);
			graph.get(x).add(y);
		}

		bfs(graph, N, M, K, X, distance);

	}

	static void bfs(ArrayList<ArrayList<Integer>> graph, int N, int M, int K, int X, int[] distance) {

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(X);
		distance[X] = 0;
		
		StringBuilder sb = new StringBuilder();
		
		int flag = 0;

		while (!q.isEmpty()) {
			int c = q.poll();

			for (int j = 0; j < graph.get(c).size(); j++) {
				int idx = graph.get(c).get(j);
				if (distance[idx] == -1) {
					q.offer(idx);
					distance[idx] = distance[c] + 1;
				}
			}
		}
		
		for(int i=1; i<=N; i++) {
			if(distance[i] == K) {
				sb.append(i).append("\n");
				flag = 1;
			}
		}
		
		if(flag==0)
			sb.append(-1);
		
		System.out.println(sb);

	}

}