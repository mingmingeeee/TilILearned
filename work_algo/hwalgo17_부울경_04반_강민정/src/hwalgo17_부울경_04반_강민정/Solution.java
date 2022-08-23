package hwalgo17_부울경_04반_강민정;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {

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
		
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		
		return true;
	}
	
	static int[] parents;
	
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");

			int N = Integer.parseInt(split[0]);
			int M = Integer.parseInt(split[1]);
			
			make(N);
			
			int answer = 0;

			for (int i = 0; i < M; i++) {
				split = in.readLine().split(" ");
				
				int a = Integer.parseInt(split[0]) - 1;
				int b = Integer.parseInt(split[1]) - 1;
				
				if(union(a, b)) {answer++;
				}
				
			}
			
			sb.append(N - answer).append("\n");

		}
	
		System.out.print(sb);

	}

}
