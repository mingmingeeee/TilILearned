
import java.io.*;
import java.util.*;

public class Reivew {

	static class Node {
		int vertex;
		Node next;

		public Node(int vertex, Node next) {
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

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adjList = new Node[V + 1];
		inDegree = new int[V + 1];

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, adjList[from]);
			inDegree[to]++;
		}

		ArrayList<Integer> list = topologySort();

		if (list.size() == V) {
			for (Integer i : list)
				System.out.println(i + " ");
			System.out.println();
		} else {
			System.out.println("cycle..");
		}

	}

	private static ArrayList<Integer> topologySort() {

		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> queue = new ArrayDeque<>();

		for (int i = 1; i <= V; i++) {
			if (inDegree[i] == 0)
				queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			
			int cur = queue.poll();
			list.add(cur);
			
			for(Node temp = adjList[cur]; temp != null; temp = temp.next) {
				if(--inDegree[temp.vertex] == 0)
					queue.offer(temp.vertex);
			}
			
		}
		
		return list;

	}

}
