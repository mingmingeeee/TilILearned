package algo_ws_17_1;

import java.io.*;
import java.util.*;

import javax.xml.soap.Node;

public class Main3 {

	private static int N;
	private static Node[] adjList;
	private static boolean[] visited; // DFS 에서 사용할 방문 여부 체크 배열
	private static int answer;

	private static class Node {

		public int vertex; // 정점의 번호
		public Node next; // 다음 노드

		public Node(int vertex, Node next) {
			this.vertex = vertex;
			this.next = next;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]); // 정점
		int M = Integer.parseInt(split[1]); // 간선

		// 정점 수 크기로 인접리스트 생성
		adjList = new Node[N];

		for (int i = 0; i < M; i++) {
			split = in.readLine().split(" ");
			int from = Integer.parseInt(split[0]);
			int to = Integer.parseInt(split[1]);

			// 무향이므로 간선 하나로 양방향 처리
			adjList[from] = new Node(to, adjList[from]);
			adjList[to] = new Node(from, adjList[to]);
		}

		answer = 0;
  
		// 모든 정점의 시작을 확인해봐야 함
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			dfs(i, 0);
		}

		sb.append(answer);

		System.out.println(answer);

	}

	// current: 현재 정점 번호
	// cnt: dfs 호출 횟수

	private static void dfs(int current, int cnt) {

		// 방문 처리
		visited[current] = true;

		// 기저 부분
		// cnt가 4라는 뜻은 A부터 E까지 친구라는 뜻
		if (cnt == 4) {
			answer = 1;
			return;
		}

		// 유도 부분
		// current 정점의 인접 정점들 처리
		for (Node temp = adjList[current]; temp != null; temp = temp.next) {
			// 방문하지 않았으며 인접한 경우
			if (!visited[temp.vertex]) {
				dfs(temp.vertex, cnt + 1);
			}
		}

		// 처음 방문 처리했던 부분 회수
		visited[current] = false;

	}

}
