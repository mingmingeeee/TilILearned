package hwalgo27_부울경_04반_강민정;

// "그래프 문제" => 인접 행렬, 인접 리스트
// 정점: 학생
// 간선: 학생들 간 키 관계 
// (1. 가중치 X 
//  2. 간선의 방향성: O 유향 그래프 => "a -> b": a가 b보다 키가 크다)
// 학생이 몇 번째인지 알고 싶을때: n - 1관계 필요 

import java.io.*;
import java.util.*;

public class Solution2 {

	static int N, M, adjMatrix[][];

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			M = Integer.parseInt(in.readLine());

			adjMatrix = new int[N + 1][N + 1]; // 학생 번호 1부터 처리

			StringTokenizer st = null;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(in.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1; // a보다 b가 키가 크다.
			}

			int answer = 0;

			for (int i = 1; i <= N; i++) { // 모든 학생을 탐색의 시작점으로 하여 반복
				if(gtBFS(i) + ltBFS(i) == N-1) answer++;
			}
			
			System.out.println("#" + tc+ " " + answer);

		}

	}

	static int gtBFS(int start) { // start 학생부터 자신보다 키가 큰 학생따라 탐색

		int cnt = 0; // 나보다 큰 학생 수

		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N + 1];

		visited[start] = true;
		queue.offer(start);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			// 행 기준: 나보다 큰 애들
			for (int i = 1; i <= N; i++) { // 자신의 인접 행렬 들여다 보기
				if (adjMatrix[cur][i] == 1 && !visited[i]) { // i가 cur보다 키가 크고 아직 탐색되지 않았다면
					cnt++;
					visited[i] = true;
					queue.offer(i);
				}
			}
		}

		return cnt;

	}

	static int ltBFS(int start) { // start 학생부터 자신보다 키가 작은 학생따라 탐색

		int cnt = 0; // 나보다 작은 학생 수

		Queue<Integer> queue = new ArrayDeque<Integer>();
		boolean[] visited = new boolean[N + 1];

		visited[start] = true;
		queue.offer(start);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			// 열 기준: 나보다 작은 애들
			for (int i = 1; i <= N; i++) { // 자신의 인접 행렬 들여다 보기
				if (adjMatrix[i][cur] == 1 && !visited[i]) { // i가 cur보다 키가 작고 아직 탐색되지 않았다면
					cnt++;
					visited[i] = true;
					queue.offer(i);
				}
			}
		}

		return cnt;

	}

}
