
import java.io.*;
import java.util.*;

public class AdjListTest {

	static class Node {
		int to;
//		int weight // 가중치
		Node next;

		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}

	static Node[] adjList;
	static int N;
	static boolean visited[];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt(); // 정점 개수
		int E = sc.nextInt(); // 간선 개수

		adjList = new Node[N];

		for (int i = 0; i < E; i++) { // 간선 수만큼 반복
			int from = sc.nextInt();
			int to = sc.nextInt();

			adjList[from] = new Node(to, adjList[from]); // 리스트 add => 계속 앞으로 
			adjList[to] = new Node(from, adjList[to]); // 리스트 add => 계속 앞으로 & 대칭

		}

		visited = new boolean[N];
//		dfs(0);
		bfs(0);
		

	}

	private static void bfs(int start) {
		// ACBGEDF
		// 0 정점 시작점
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N]; // 방문 관리 배열

		visited[start] = true;
		queue.offer(start);

		while (!queue.isEmpty()) {

			int cur = queue.poll();
			System.out.print((char) (cur + 'A'));

			// 현 정점의 인접 정점들을 큐에 넣어서 차후 탐색하도록 만들기
			for (Node temp = adjList[cur]; temp!= null; temp = temp.next) {
				// 리스트에서 현재에 있는 정점을 꺼내고 null이 아닐 때까지 수행
				if (!visited[temp.to]) {
					// 방문하지 않았으며
					// 현재 정점에서 그 정점으로 갈 수 있는 경우(인접한 경우)
					// 가중치가 있을 경우에는 1일 수도 있고 2일 수도 있고 하니까 0이 아닐 때로!
					visited[temp.to] = true;
					queue.offer(temp.to);
				}
			}

		}
		System.out.println();

	}

	private static void dfs(int cur) {

		visited[cur] = true;
		System.out.print((char) (cur + 'A'));

		// 한 정점의 인접 정점들에 큐에 넣어서 차후 탐색하도록 만들기
		for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
			// temp에 들어있는 to가 아직 방문되지 않았고 (인접이 방문되지 않았고)
			if (!visited[temp.to]) {
				dfs(temp.to); // temp.to로 간다!
			}
		}

	}

}
