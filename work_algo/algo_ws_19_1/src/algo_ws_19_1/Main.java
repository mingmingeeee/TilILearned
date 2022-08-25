package algo_ws_19_1;

import java.io.*;
import java.util.*;

// 방향 그래프
// 주어진 시작점에서 다른 모든 정점으로의 최단 경로 구하는 프로그램

public class Main {
	
	private static class Node {
		
		int from;
		int to;
		Node next;
		
		public Node(int from, int to, Node next) {
			super();
			this.from = from;
			this.to = to;
			this.next = next;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] split = in.readLine().split(" ");
		
		int V = Integer.parseInt(split[0]);
		int E = Integer.parseInt(split[1]);
		
		
		int K = Integer.parseInt(in.readLine());
		
		
		for(int i=0; i<E; i++) {
			split = in.readLine().split(" ");
			
			
		}
		

		
		
		
	}

}
