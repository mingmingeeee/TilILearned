package baekjoon;

import java.io.*;
import java.util.*;

public class B_2606 {

	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine()); 
		int M = Integer.parseInt(in.readLine());

		int[][] virus = new int[N + 1][N + 1];
		for(int i=0; i<M; i++) {
			String[] strings = in.readLine().split(" ");
			int x = Integer.parseInt(strings[0]);
			int y = Integer.parseInt(strings[1]);
			virus[x][y] = 1;
			virus[y][x] = 1;
		}
		
		visited = new boolean[N + 1];

		bfs(virus);

	}
	
	static void bfs(int[][] virus) {
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);
		visited[1] = true;
		int count = 0;
		
		while(!q.isEmpty()) {			
			int p = q.poll();

			for(int i=1; i<virus[0].length; i++) {
				if(virus[p][i]==1 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
					count++;
				}
			}
			
			
		}
		
		System.out.println(count);
	}

}