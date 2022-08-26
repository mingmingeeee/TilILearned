package algo_ws_19_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {

	private static class Node {

		int vertex;
		int weight;
		Node next;

		public Node(int vertex, int weight, Node next) {
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}

		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + ", next=" + next + "]";
		}
	}

	public static void main(String args[]) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		System.setIn(new FileInputStream("input1.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		String[] split = in.readLine().split(" ");
		int V = Integer.parseInt(split[0]);  // 정점의 개수
		int E = Integer.parseInt(split[1]);  // 간선의 개수
		int K = Integer.parseInt(in.readLine());  // 시작 정점의 번호
		
		Node[] adjList = new Node[V + 1];
		
		// start -> end로의 최단 경로
		int start = K;  // 출발 정점
		int end = V;  // 도착점 인덱스
		
		// 인접 리스트 데이터 입력
		for (int i = 0; i < E; i++) {
			split = in.readLine().split(" ");
			int from = Integer.parseInt(split[0]);
			int to = Integer.parseInt(split[1]);
			int weight = Integer.parseInt(split[2]);
			
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		// 다익스트라 알고리즘에 필요한 자료구조
		int[] D = new int[V + 1];  // 출발지에서 자신으로의 최소(최단) 비용(거리) => 최단 경로 구성 가중치 합
		boolean[] visited = new boolean[V + 1];  // 최소비용 확정여부
		
		Arrays.fill(D, Integer.MAX_VALUE);
		
		// 출발 정점 처리
		D[start] = 0;  // 시작점 0으로

		/**
		 * 2. 알고리즘 풀기
		 */
		int min;
		int current;
		
		for (int i = 1; i <= V; i++) {
			// 1단계 : 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
			// 방문 해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
			min = Integer.MAX_VALUE;
			current = 0;
			for (int j = 1; j <= V; j++) {
				if (!visited[j] && D[j] < min) {
					min = D[j];
					current = j;
				}
			}
			
			// 2단계: 방문처리
			visited[current] = true;  // 선택 정점 방문 처리
			
			// 문제가 start -> end로의 최단이면 탈출
			/*if (current == end) {
				break;  // current가 도착지라면 끝냄
			}*/
			
			// 3단계 : 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고 기존 최적해보다 유리하면 갱신
			for (Node temp = adjList[current]; temp != null; temp = temp.next) {
				if (!visited[temp.vertex] &&
						D[temp.vertex] > D[current] + temp.weight) {
					D[temp.vertex] = D[current] + temp.weight;
				}
			}
		}

		/**
		 * 3. 정답 출력
		 */
		for (int i = 1; i <= V; i++) {
			
			// 시작점부터 i번 정점으로의 최소 비용
			int minDistance = D[i];
			
			// 경로가 존재하지 않는 경우에는 INF 출력
			if (minDistance == Integer.MAX_VALUE) {
				sb.append("INF").append("\n");
			}
			// 그 외에는 최소 비용 출력
			else {
				sb.append(minDistance).append("\n");
			}
		}

		System.out.println(sb);
	}
}
