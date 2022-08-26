package hwalgo19_부울경_04반_강민정;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CORBA.Current;

// 그리디라면 -> MST(Kruscal, Prim) or Dijkstra 

public class Main2 {

	private static int N;
	private static int[][] W;

	private static int answer;
	private static boolean[] isVisited; // DFS 풀 때 사용할 방문 체크 배열
	
	private static int start;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());

		W = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(split[j]); 
			}
		}

		answer = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			isVisited = new boolean[N];	
			isVisited[i] = true;
			dfs(i, 0);
		}
		
		sb.append(answer);
		System.out.println(sb);
		
	}
	
	private static boolean isAllVisited() {
		for(int i=0; i<N; i++) {
			if(!isVisited[i]) {
				return false;
			}
		}
		return true;
	}
	
	// current: 현재 정점 번호
	// weight: 가중치 (도시를 방문할 때마다 누적된 가중치가 들어옴)
	private static void dfs(int current, int weight) {
		
		// 기저 부분
		if (isAllVisited()) { // 모든 도시를 방문했다면
			
			// 마지막 도시에서 출발지로 돌아가는 길이 있을 경우
			if(W[current][0] != 0) {
				int result = weight + W[current][0]; // 마지막 도시에서 출발지로 돌아가는 비용을 더한다.
				if(result < answer) {
					answer = result;
				}
			}
		}
		
		
		// 유도 부분: 0 -> 1 위한
		for(int i=1; i<N; i++) {
			if(!isVisited[i] && W[current][i] != 0) {
				
				// 방문 처리
				isVisited[i] = true;
				
				dfs(i, weight + W[current][i]);
				
				// 방문 처리 반납
				isVisited[i] = false; 
				
			}
		}
		
	}

}