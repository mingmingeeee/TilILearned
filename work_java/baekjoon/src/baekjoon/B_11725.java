package baekjoon;

import java.io.*;
import java.util.*;

public class B_11725 {
	
	static int N;
	static int M;
	static List<List<Integer>> list = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		M = N - 1;
		
		for(int i=0; i<=N; i++) {
			list.add(new ArrayList<>());
		}
		
		for(int i=0; i<M; i++) {
			String[] split = in.readLine().split(" ");
			int from = Integer.parseInt(split[0]);
			int to = Integer.parseInt(split[1]);
			list.get(from).add(to);
			list.get(to).add(from);
		}
		
		bfs(1);
		
		System.out.println(sb);
		
	}
	
	private static void bfs(int V) {
		
		boolean[] visited = new boolean[N + 1];
		int[] parent = new int[N + 1];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(V);
		
		visited[V] = true;
	
		while(!q.isEmpty()) {
			
			int v = q.poll();
			
			for(int vs : list.get(v)) {
				
				if(!visited[vs]) {
					visited[vs] = true;
					parent[vs] = v; // 현재 나는 다음에 갈 노드들의 부모 
					q.offer(vs);
				}
				
			}
			
			
		}
		
		for(int i=2; i<N+1; i++) {
			sb.append(parent[i]).append("\n");
		}
		
	}

}
