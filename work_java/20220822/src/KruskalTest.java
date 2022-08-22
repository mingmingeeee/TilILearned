import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KruskalTest {
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight; // from에서 to까지의 간선 정보 

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
//			return Inteer.compare(this.weight - o.weight); // 가중치에 음수, 양수 섞여있을 때
			return this.weight - o.weight;
		}
		
	}
	
	static int[] parents; // 서로소 집합을 트리 기반으로 유지하기 위한 parents 기반
	static int V, E; // 정점의 개수, 간선의 개수
	static Edge[] edgeList; // 간선 리스트
	
	static void make() { // 크기가 1인 서로소 집합 생성
		
		parents = new int[V]; // 정점 개수만큼 index 배열을 만들고 
		for(int i=0; i<V; i++) { // 모든 노드가 자신을 부모(대표자)로 하는 집합으로 만듦
			parents[i] = i; // 나로 채우기 
		}
		
	}
	
	static int find(int a) { // a의 대표자 찾기
		if(parents[a] == a) { // 나의 부모가 나라면 내가 우리 집합의 대표자
			return a;
		}
		// 나의 부모가 다른 사람 이라면
		return parents[a] = find(parents[a]); // 나의 부모에게 우리의 대표자를 알아와달라고 시킴 
		// 그렇게 받은 부모를 나의 부모로 바꾸고 return
		// -> 우리의 대표자를 나의 부모로.. : path compression
	}
	
	static boolean union(int a, int b) { // 리턴값 : true ==> union 성공
		int aRoot = find(a); // a 집합 대표자 가져왓!
		int bRoot = find(b); // b 집합 대표자 가져왓!
		
		if(aRoot == bRoot) return false; // 우리... 부모가 같은 가족 이었어?? -> 합칠 수 없음 -> false return
		
		// return 안 되고 여기 내려오면 부모가 다르단 이야기! -> 서로소 집합 
		parents[bRoot] = aRoot; // b를 a에 합침
		return true; // 합치는 거 성공!!! 
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken()); // 정점 수
		E = Integer.parseInt(st.nextToken()); // 간선 수 
		
		edgeList = new Edge[E]; // 간선 리스트 
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			edgeList[i] = new Edge(Integer.parseInt(st.nextToken()), 
							Integer.parseInt(st.nextToken()), 
							Integer.parseInt(st.nextToken()));
		}
		
		make();
		
		Arrays.sort(edgeList); // 간선 기준 오름차순 정렬
		
		int result = 0;
		int count = 0;
		
		for(Edge edge : edgeList) { // 간선 꺼내기 
			if(union(edge.from, edge.to)) { // union 성공했다: 합쳐짐 -> 사이클 발생하지 않았다는 ~
				result += edge.weight; // 가중치 비용 누적
				if(++count == V - 1) break; // 성공할 때마다 count 올리고, 이게 V - 1이면 break;
				// V - 1 = 정점 - 1
			}
		}
		
		System.out.println(result);
	}

}

/*
5 10 // 정점수, 간선 수
// 간선 수 만큼  
0 1 5 // from, to, weight
0 2 10
0 3 8
0 4 7
1 2 5
1 3 3
1 4 6
2 3 1
2 4 3
3 4 1
*/