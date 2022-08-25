package algo_ws_19_1;

import java.io.*;
import java.util.*;

// 방향 그래프
// 주어진 시작점에서 다른 모든 정점으로의 최단 경로 구하는 프로그램

public class Main {

	private static class Node implements Comparable<Node> {

		int vertex;
		int weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
		
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}; 

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int V = Integer.parseInt(split[0]);
		int E = Integer.parseInt(split[1]);

		int K = Integer.parseInt(in.readLine()) - 1;

		Node[] adjList = new Node[V];

		for (int i = 0; i < E; i++) {
			split = in.readLine().split(" ");
			// u에서 v로 가는 가중치 w
			int from = Integer.parseInt(split[0]) - 1;
			int to = Integer.parseInt(split[1]) - 1;
			int weight = Integer.parseInt(split[2]);

			adjList[from] = new Node(to, weight, adjList[from]);
		}

		int[] D = new int[V];
		boolean[] visited = new boolean[V];

		Arrays.fill(D, Integer.MAX_VALUE);
		D[K] = 0;
		
		PriorityQueue<Node> pQueue = new PriorityQueue<>();
		pQueue.offer(new Node(K, D[K], null));
		
		while(!pQueue.isEmpty()) {
			
			Node min = pQueue.poll();
			
			if(visited[min.vertex])
				continue;
			
			visited[min.vertex] = true;
			
			for (Node temp = adjList[min.vertex]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] &&
						D[temp.vertex] > D[min.vertex] + temp.weight) {
					D[temp.vertex] = D[min.vertex] + temp.weight;
					pQueue.offer(new Node(temp.vertex, D[temp.vertex], null));
				}
			}

			
		}
		
		for(int i: D) {
			if(i==Integer.MAX_VALUE)
				sb.append("INF").append("\n");
			else
				sb.append(i).append("\n");
		}

		System.out.println(sb);

	}

}
