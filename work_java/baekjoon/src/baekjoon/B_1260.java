package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_1260 {

	static int[][] graph;
	static boolean[] visited = new boolean[1001]; // 입력 케이스  + 1 (정점)
	static int N, M;

	static Queue<Integer> queue = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String[] s = in.readLine().split(" ");


		N = Integer.parseInt(s[0]); // 정점 개수
		M = Integer.parseInt(s[1]); // 간선 개수
		int V = Integer.parseInt(s[2]); // 시작할 정점 번호
		
		graph = new int[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			s = in.readLine().split(" ");
			int node1 = Integer.parseInt(s[0]);
			int node2 = Integer.parseInt(s[1]);

			// 대칭 - 무방향
			graph[node1][node2] = 1;
			graph[node2][node1] = 1;
		}
		
		dfs(V);
		sb.append("\n");
		Arrays.fill(visited,  false); // 방문 배열 초기화
		
		bfs(V);
		System.out.println(sb);

	}
	
	static void dfs(int v) {
		
		visited[v] = true; 
		sb.append(v).append(" ");
		
		// recursive
		for(int i=1; i<=N; i++) {
			if(graph[v][i] == 1 && !visited[i]) { // 관계가 있고 방문 안 했다면 
				dfs(i);
			}
		}
		
	}
	
	static void bfs(int v) {
		
		queue.add(v); // 본인 값 너기
		visited[v] = true; // 방문 mark
		
		while(!queue.isEmpty()) {
			
			v = queue.poll(); // 나 꺼내기
			sb.append(v).append(" "); // 꺼낸 거 담기
			
			for(int i = 1; i <= N; i++) { // 모~든 정점 방문!!!
				if(graph[v][i]== 1 && !visited[i]) { // v와 연결되어 있는 모든 정점 i, 방문 전 정점
					queue.add(i);

					visited[i] = true; 
				}
			}
			
		}
		queue.clear();
	}

}
