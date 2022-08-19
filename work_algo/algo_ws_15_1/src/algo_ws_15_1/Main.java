package algo_ws_15_1;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] adjArray;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		
		adjArray = new int[N+1][N+1];
		visited = new boolean[N + 1];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			adjArray[v1][v2] = 1;
			adjArray[v2][v1] = 1; 
		}
		
		dfs(V);
		sb.append("\n");
		bfs(V);
		System.out.println(sb);
	}
	
	private static void bfs(int V) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		queue.offer(V);
		
		visited[V] = true;
		while(!queue.isEmpty()) {
			
			int v = queue.poll();
			
			sb.append(v + " ");
			
			for(int i=0; i<N+1; i++) {
				if(adjArray[v][i] == 1 && !visited[i]) {
					queue.offer(i);
					visited[i] = true;
				}
			}
			
		}
		
	}
	
	private static void dfs(int V) {
		
		visited[V] = true;
		sb.append(V + " ");
		
		for(int i=0; i<N+1; i++) {
			if(adjArray[V][i]==1&&!visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
		
	}

}
