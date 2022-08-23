import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 서로소 집합 
// Make-Set(x)
// Find-Set(x)
// Union(x, y)
public class Review {

	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	static void make() {
		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}
	}

	static int find(int a) {
		if (parents[a] == a)
			return a;

		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;
		parents[bRoot] = parents[aRoot];
		return true;
	}

	static int[] parents;
	static int V, E;
	static Edge[] edgeList;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		make();
		
		edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			edgeList[i] = new Edge(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edgeList);
		
		int result = 0;
		int count = 0;
		
		for(Edge edge : edgeList) {
			
			if(union(edge.from, edge.to)) {
				result += edge.weight;
				
				if(++count == V - 1)
					break;
			}
			
		}
		
		System.out.println(result);

	}

}
