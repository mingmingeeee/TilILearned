import java.io.*;
import java.util.*;

public class Review3 {

	static class Node implements Comparable<Node> {
		int vertex, weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		Node[] adjList = new Node[V];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
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

		PriorityQueue<Node> pQueue = new PriorityQueue<>();

		pQueue.offer(new Node(0, minEdge[0], null));
		
		int cnt = 0;
		while (true) {

			Node minVertex = pQueue.poll();

			if(visited[minVertex.vertex])
				continue;
			
			visited[minVertex.vertex] = true;
			result += minVertex.weight;
			
			if(++cnt == V)
				break;

			for (Node temp = adjList[minVertex.vertex]; temp != null; temp = temp.next) {
				
				if(!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
					minEdge[temp.vertex] = temp.weight;
					pQueue.offer(new Node(temp.vertex, minEdge[temp.vertex], null));
				}
				
			}

		}
		
		System.out.println(result);
	}

}
