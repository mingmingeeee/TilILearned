package hwalgo16_부울경_04반_강민정;

import java.io.*;
import java.util.*;

// 유향 그래프 

public class Solution2 {

	private static int[][] adjMatrix;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {

			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");
			int N = Integer.parseInt(split[0]); // 데이터의 길이
			int E = N / 2; // 간선의 수 (중복 포함)
			int start = Integer.parseInt(split[1]); // 시작 정점

			// 간선 정보를 인접 행렬에 저장
			adjMatrix = new int[101][101]; // 연락 인원 최대 100명, 번호는 1부터 시작
			split = in.readLine().split(" ");
			for (int i = 0; i < E; i++) {
				int from = Integer.parseInt(split[i * 2]);
				int to = Integer.parseInt(split[i * 2 + 1]);
				adjMatrix[from][to] = 1;
			}

			int answer = bfs(start);

			sb.append(answer).append("\n");
		}

		System.out.println(sb);
	}

	private static int bfs(int start) {

		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] isVisited = new boolean[101];

		isVisited[start] = true;
		queue.offer(start);

		int answer = 0;
		while (!queue.isEmpty()) {

			// 큐 크기 확인 (동일 너비 대상 개수)
			int size = queue.size();

			// 그래프의 각 깊이마다 마지막 연락을 받은 사람 중 가장 숫자가 큰 사람 번호
			int max = 0;
			while (--size >= 0) {

				// 현재 정점 꺼내기
				int curNode = queue.poll();

				// 현재 정점으로부터 인접한 정점을 큐에 넣기
				for (int i = 1; i <= 100; i++) {
					if (!isVisited[i] && adjMatrix[curNode][i] != 0) { // 가지치기
						isVisited[i] = true;
						queue.offer(i);

						// 최댓값 갱신
						if (i > max) {
							max = i;
						}

					}
				}
			}
			if (max > 0) {
				answer = max;
			}

		}
		
		return answer;

	}

}
