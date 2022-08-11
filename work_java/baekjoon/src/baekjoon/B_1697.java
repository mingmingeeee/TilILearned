package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_1697 {
	// 수빈: 걷거나 순간 이동 
	// 걷는다면: X-1 or X+1
	// 순간이동: 2*X

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] strings = in.readLine().split(" ");
		int N = Integer.parseInt(strings[0]); // 수빈이가 있는 위치
		int K = Integer.parseInt(strings[1]); // 동생이 있는 위치 

		bfs(N, K, 0);
	}
	
	static void bfs(int N, int K, int count) {
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		queue.add(N);
		boolean[] visited = new boolean[100_000+1];
		visited[N] = true;
		while(!queue.isEmpty()) {
			
			int c = queue.size();
			
			for(int i=0; i<c; i++) {
				
				int x = queue.poll();

				if(x==K) {
					System.out.println(count);
					return;
				}
				
				if(x+1<=100_000 && x+1>=0 && !visited[x+1]) {
					queue.offer(x + 1);
					visited[x + 1] = true;
				}
				if(x-1<=100_000 && x-1 >=0&& !visited[x-1]) {
					queue.offer(x - 1);
					visited[x-1] = true;
				}
				if(2*x <=100_000 && 2*x >=0 && !visited[2*x]) {
					queue.offer(2 * x);
					visited[2*x] = true; 
				}
				
			}
			count++;
			
		}
		
	}


}
