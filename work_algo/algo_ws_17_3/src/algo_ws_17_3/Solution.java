package algo_ws_17_3;

import java.io.*;
import java.util.*;

public class Solution {
	
	static class Edge implements Comparable<Edge>{
		
		int from;
		int to;
		long weight;
		
		public Edge(int from, int to, int weight){
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return (int) (this.weight - o.weight);
		}
		
	}
	
	static void make(int V) {
		
		parents = new int[V];
		
		for(int i=0; i<V; i++) {
			parents[i] = i; 
		}
		
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		
		return parents[a] = find(parents[a]); 
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if(aRoot == bRoot)
			return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	static int[] parents;
	static Edge[] edgeList;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			sb.append("#" + test_case + " ");
			
			String[] split = in.readLine().split(" ");
			
			int V = Integer.parseInt(split[0]);
			int E = Integer.parseInt(split[1]);
			
			edgeList = new Edge[E];
			
			for(int i=0; i<E; i++) {
				split = in.readLine().split(" ");
				
				int a = Integer.parseInt(split[0]) - 1;
				int b = Integer.parseInt(split[1]) - 1;
				int c = Integer.parseInt(split[2]);
				
				edgeList[i] = new Edge(a, b, c); 
			}
			
			
			make(V);
			
			Arrays.sort(edgeList);
			
			long answer = 0;
			long count = 0;
			
			for(Edge edge : edgeList) {
				if(union(edge.from, edge.to)) {
					answer += edge.weight;
					
					if(++count == V - 1)
						break;
				}
			}
			
			sb.append(answer).append("\n");
			
		}
		
		System.out.println(sb);

	}

}
