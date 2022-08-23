package hwalgo15_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main2 {

	private static class Node {

		public int v; // 정점의 번호
		public int depth; // 깊이

		public Node(int v, int depth) {
			this.v = v;
			this.depth = depth;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);

		int answer = bfs(N, K);

		sb.append(answer);
		System.out.println(sb);

	}

	private static int bfs(int start, int dst) {

		Queue<Node> queue = new ArrayDeque<>();
		boolean isVisited[] = new boolean[100_001]; // K의 범위는 0 ~ 100,000

		isVisited[start] = true;
		queue.offer(new Node(start, 0));

		int depth = 0;
		while (!queue.isEmpty()) {

			// 큐 크기 확인 (동일 너비 대상 개수)
			int size = queue.size();

			// 1초 증가 (BFS의 깊이 증가)
			depth++;

			while (--size >= 0) {

				// 현재 정점 꺼내기
				Node curNode = queue.poll();

				// 기저 부분: 만약 동생이 있는 위치 K에 도착했다면
				if (curNode.v == dst) {
					// 정점의 depth 값을 리턴
					return curNode.depth;
				}

				// 1초 후 현재 정점에서 이동할 수 있는 모든 위치 구하기
				Node[] nodes = new Node[3];
				nodes[0] = new Node(curNode.v - 1, depth);
				nodes[1] = new Node(curNode.v + 1, depth);
				nodes[2] = new Node(curNode.v * 2, depth);

				// 위에서 구한 모든 정점을 큐에 넣기 (방문 예약)
				for (int i = 0; i < 3; i++) {
					// 아래 if문은 가지치기
					if (0 <= nodes[i].v && nodes[i].v <= 100_000 && !isVisited[nodes[i].v]) {
						isVisited[nodes[i].v] = true;
						queue.offer(nodes[i]);
					}
				}
			}

		}
		return depth;

	}

}