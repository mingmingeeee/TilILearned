import java.io.*;
import java.util.*;

public class TopologySortTest {

	static class Node {

		int vertex; // 나와 연결되어 있는 정점 번호가 뭔지, 가중치가 뭔지
		Node next;

		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}

	}

	static int V, E;
	static Node[] adjList;
	static int[] inDegree;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		V = Integer.parseInt(st.nextToken()); // 정점 수
		E = Integer.parseInt(st.nextToken()); // 간선 수

		adjList = new Node[V + 1]; // 각 정점별 인접 리스트
		inDegree = new int[V + 1]; // 정점별 진입 차수 정보 리스트

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			// 유향 처리
			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++; // from에서 나가서 to로 들어가는 거기 때문에 to를 늘려줘야 함
		}

		ArrayList<Integer> list = topologySort();

		if (list.size() == V) { // 위상 정렬 완성
			for (Integer i : list)
				System.out.print(i + " ");
			System.out.println();
		} else {
			System.out.println("cycle..");
		}
	}

	private static ArrayList<Integer> topologySort() {

		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();

		// 정렬 차수가 0인 정점 큐에 넣기
		for (int i = 1; i <= V; i++) {
			if (inDegree[i] == 0)
				queue.offer(i);
		}

		// BFS
		while (!queue.isEmpty()) {

			int cur = queue.poll();
			list.add(cur); // 나온 녀석은 처리할 게 없어서 나온 놈임 -> 처리 순서니까 list에 추가해주기

			for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if (--inDegree[temp.vertex] == 0)
					queue.offer(temp.vertex); // 진입 차수가 0이 되는 정점 queue에 넣음
			}

		}

		return list;
	}

}
