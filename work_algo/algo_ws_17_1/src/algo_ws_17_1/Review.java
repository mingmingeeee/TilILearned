package algo_ws_17_1;

import java.io.*;
import java.util.*;

public class Review {

	static int answer;
	static boolean[] visited;
	static Node[] adjList;
	
	private static class Node{
		int to;
		Node next;
		
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]); // 정점
		int M = Integer.parseInt(split[1]); // 간선

		adjList = new Node[N];

		for (int i = 0; i < M; i++) { // 간선 수만큼 반복
			split = in.readLine().split(" ");
			
			int from = Integer.parseInt(split[0]);
			int to = Integer.parseInt(split[1]);
			
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]); 
		}
		
		answer = 0;
		
		for(int i=0; i<N; i++) {
			visited = new boolean[N];
			dfs(i, 0);
		}

		
		sb.append(answer);
		System.out.println(sb);
	}
	
	private static void dfs(int v, int depth) {
		
		visited[v] = true;
		
		if(depth == 4) {
			answer = 1;
			return;
		}
		
		for(Node temp = adjList[v]; temp != null; temp = temp.next) {
			if(!visited[temp.to])
				dfs(temp.to, depth + 1);
		}
		
		visited[v] = false; 
		
	}

}
