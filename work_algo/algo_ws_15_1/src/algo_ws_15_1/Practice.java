package algo_ws_15_1;

import java.io.*;
import java.util.*;

public class Practice {
	
	static class Node{
		int to;
		// int weight;
		Node next;
		
		Node(int to, Node next){
			this.to = to;
			this.next = next;
		}
	}
	
	static Node[] adjList;
	static int N;
	static boolean visited[];
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt(); // 정점 개수
		int E = scanner.nextInt(); // 간선 개수 
		int V = scanner.nextInt();
		
		adjList = new Node[N+1];
		
		for(int i=0; i<E; i++) {
			int from = scanner.nextInt();
			int to = scanner.nextInt();
			
			adjList[from] = new Node(to,  adjList[from]);
			adjList[to] = new Node(from,  adjList[to]); 
		}
		
		visited = new boolean[N+1];
		

		bfs(V);
		
	}
	
	private static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		
		visited[start] = true;
		queue.offer(start);
		
		while(!queue.isEmpty()) {
			
			int cur = queue.poll();
			System.out.println(cur);
			
			for(Node temp = adjList[cur]; temp!=null; temp=temp.next) {
				if(!visited[temp.to]) {
					visited[temp.to] = true;
					queue.offer(temp.to);
				}
			}
				
			
		}
		System.out.println();
		
	}
	
	private static void dfs(int cur) {
		
		visited[cur] = true;
		System.out.println(cur);
		
		for(Node temp = adjList[cur]; temp!=null; temp = temp.next) {
			if(!visited[temp.to]) {
				dfs(temp.to);
			}
		}
		
	}

}
