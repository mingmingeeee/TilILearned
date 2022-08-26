import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Review2 {
	static class Node {
		int vertex;
		int weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int V = Integer.parseInt(st.nextToken()); // 정점 수
		int E = Integer.parseInt(st.nextToken()); // 간선 수

		Node[] adjList = new Node[V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}

		int[] minEdge = new int[V];
		boolean[] visited = new boolean[V];

		Arrays.fill(minEdge, Integer.MAX_VALUE);

		minEdge[0] = 0;
		int result = 0;

		for (int i = 0; i < V; i++) {

			int min = Integer.MAX_VALUE;
			int minVertex = -1;

			for (int c = 0; c < V; c++) {
				if (!visited[c] && min > minEdge[c]) {
					min = minEdge[c];
					minVertex = c;
				}
			}

			visited[minVertex] = true;
			result += min;

			for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
					minEdge[temp.vertex] = temp.weight;
				}
			}
		}

		System.out.println(result);

	}


}
