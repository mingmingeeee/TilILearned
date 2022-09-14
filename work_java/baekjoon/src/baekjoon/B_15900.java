package baekjoon;

import java.io.*;
import java.util.*;

public class B_15900 {

	// "나무 탈출"보드 게임
	// N개의 정점이 있는 트리 모양어쩌구
	// 각 트리 정점: 1~N까지 번호

	// 두 사람이 번갈아 가면서 게임판에 놓여있는 게임말 움직이는 게임
	// 1. 트리의 모든 리프 노드에 게임말 하나씩 있음
	// 2. 차례 오면 게임말 중 하나 골라 그 말이 놓여있던 노드의 부모 노드로 옮김
	// 3. 한 노드에 여러 개의 게임말이 놓일수도?
	// 4. 게임말이 루트 노드에 도착? -> 제거
	// 5. 게임말이 게임판에 존재하지 않아 고를 수 없는 사람이 지게 됨

	// 출력: 성원이가 게임 이길 수 있다면 Yes
	// 이길 수 없다면 No
	private static int N;
	private static Edge[] edgeList;
	private static boolean[] visited;
	private static StringBuilder sb;
	private static int answer;

	private static class Edge {
		int from;
		int to;
		Edge next;

		Edge(int from, int to, Edge next) {
			this.from = from;
			this.to = to;
			this.next = next;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		edgeList = new Edge[N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < N - 1; i++) {
			String[] s = in.readLine().split(" ");
			int from = Integer.parseInt(s[0]);
			int to = Integer.parseInt(s[1]);

			edgeList[from] = new Edge(from, to, edgeList[from]);
			edgeList[to] = new Edge(to, from, edgeList[to]);
		}

		// 1에서부터 내려가보기
		dfs(1, 0);

		if (answer % 2 == 1)
			sb.append("Yes");
		else
			sb.append("No");

		System.out.println(sb);

	}

	private static void dfs(int n, int depth) {
		visited[n] = true;
		int flag = 0;
		for (Edge temp = edgeList[n]; temp != null; temp = temp.next) {
			if (!visited[temp.to]) {
				flag = 1;
				dfs(temp.to, depth + 1);
			}
		}
		if (flag == 0) { // 리프 노드
			answer += depth;
		}
	}

}
