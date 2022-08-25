import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 인접 리스트: O(V^2+E)
// 인접 행렬: O(2*V^2)
// E가 V^2에 가깝다?: 완전 그래프의 모습이다.
// -> 완전 그래프일 땐 인접 리스트의 장점이 없음 

public class PrimTest {

	static class Node {

		int vertex, weight; // 나와 연결되어 있는 정점 번호가 뭔지, 가중치가 뭔지
		Node next;

		public Node(int vertex, int weight, Node next) {
			super();
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

		Node[] adjList = new Node[V]; // 각 정점별 인접 리스트

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}
		// 프림 알고리즘에 필요한 자료구조
		int[] minEdge = new int[V]; // 각 정점 입장에서 신장트리에 포함된 정점과의 간선 비용 중 최소 비용
		boolean[] visited = new boolean[V]; // 신장 트리에 포함 여부

		Arrays.fill(minEdge, Integer.MAX_VALUE); // 최소값 관리하기 위해 큰 값 세팅

		// 1. 임의의 시작점 처리, 0번 정점을 신장트리의 구성의 시작점
		minEdge[0] = 0;
		int result = 0; // 최소 신장트리 비용 누적

		for (int c = 0; c < V; c++) { // V개의 정점 처리하면 끝
			// step1. 신장 트리의 구성에 포함되지 않은 정점 중 최소비용 정점 선택
			int min = Integer.MAX_VALUE;
			int minVertex = -1;
			for (int i = 0; i < V; i++) {
				if (!visited[i] && min > minEdge[i]) {
					min = minEdge[i];
					minVertex = i;
				}
			}

			// step2. 신장트리에 추가
			visited[minVertex] = true;
			result += min; // 신장 트리 비용 누적

			// step3. 신장 트리에 새롭게 추가되는 정점과 신장트리에 포함되지 않은 정점들의 기존 최소비용과 비교해서 갱신
			// 신장 트리에 새롭게 추가되는 정점의 모든 인접 정점 들여다 보며 처리
			for (Node temp = adjList[minVertex]; temp != null; temp = temp.next) {  // -> 인접한 것만큼 바라보니 전체로 하면 E의 시간 복잡도
				// 인접리스트라 인접여부 체크 안해도 인접되어있다는 걸 알 수 있음
				if(!visited[temp.vertex] && minEdge[temp.vertex] > temp.weight) {
					// 원래 이 정점이 갖고 있던 간선의 최소 비용이, minVertex에서 오는 비용보다 더 크다면
					// 더 작은 놈(현재 minVertex에서 오는 비용)으로 업데이트
					minEdge[temp.vertex] = temp.weight;
				}
			}
			// 각 정점과 인접된 것들 -> 전체로 보면 전체 정점과 인접된 것들 -> O(E)

		}
		
		System.out.println(result);

	}

}
