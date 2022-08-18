import java.util.*;
import java.io.*;

public class AdjMatrixTest {

	static int[][] adjMatrix;
	static int N;
	static boolean visited[];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 정점 수
		int E = sc.nextInt(); // 간선 수

		adjMatrix = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < E; i++) { // 간선 정보에 해당하는 부분만 덮어씀
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = 1; // 무향 그래프
		}

		bfs();
//		dfs(0);

	}

	private static void bfs() {

		// 0 정점 시작점
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N]; // 방문 관리 배열

		visited[0] = true;
		queue.offer(0);

		while (!queue.isEmpty()) {

			int cur = queue.poll();
			System.out.print((char) (cur + 'A'));

			// 현 정점의 인접 정점들을 큐에 넣어서 차후 탐색하도록 만들기
			for (int i = 0; i < N; i++) {
				if (!visited[i] && adjMatrix[cur][i] != 0) {
					// 방문하지 않았으며
					// 현재 정점에서 그 정점으로 갈 수 있는 경우(인접한 경우)
					// 가중치가 있을 경우에는 1일 수도 있고 2일 수도 있고 하니까 0이 아닐 때로!
					visited[i] = true;
					queue.offer(i);
				}
			}

		}
		System.out.println();

	}

	private static void dfs(int cur) {

		System.out.print((char) (cur + 'A'));
		visited[cur] = true;

		// 현 정점의 인접 정점들을 큐에 넣어서 차후 탐색하도록 만들기
		for (int i = 0; i < N; i++) {
			if (!visited[i] && adjMatrix[cur][i] != 0) {
				// 방문하지 않았으며
				// 현재 정점에서 그 정점으로 갈 수 있는 경우(인접한 경우)
				// 가중치가 있을 경우에는 1일 수도 있고 2일 수도 있고 하니까 0이 아닐 때로!
				dfs(i);
			}
		}
		
	}

}
