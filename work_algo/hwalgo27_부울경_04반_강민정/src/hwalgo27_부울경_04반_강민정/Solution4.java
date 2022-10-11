package hwalgo27_부울경_04반_강민정;

// "그래프 문제" => 인접 행렬, 인접 리스트
// 정점: 학생
// 간선: 학생들 간 키 관계 
// (1. 가중치 X 
//  2. 간선의 방향성: O 유향 그래프 => "a -> b": a가 b보다 키가 크다)
// 학생이 몇 번째인지 알고 싶을때: n - 1관계 필요 

import java.io.*;
import java.util.*;

public class Solution4 {

	static int N, M;
	static int cnt;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			M = Integer.parseInt(in.readLine());

			int[][] adjMatrix = new int[N + 1][N + 1]; // 학생 번호 1부터 처리

			for (int i = 1; i <= N; i++)
				adjMatrix[i][0] = -1; // 탐색하지 않은 상태의 초기 값

			StringTokenizer st = null;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(in.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1; // a보다 b가 키가 크다
			}

			int answer = 0;

			for (int i = 1; i <= N; i++) { // 모든 학생을 탐색의 시작점으로 하여 반복
				if (adjMatrix[i][0] == -1) // 탐색이 되어있지 않을 때에만 탐색 go
					dfs(i, adjMatrix);
			}

			// 모든 정점이 알고 있는 관계로 탐색을 마친 상태 (큰 정점을 따라 탐색해서 간접 관계를 직접 관계로 다 반영해서 인접 행렬 상태
			// update)
			// 열 기준 정보를 확인하면 자신보다 작은 정점 파악 가능
			for (int k = 1; k <= N; k++) {
				for (int i = 1; i <= N; i++) {
					adjMatrix[0][k] += adjMatrix[i][k];
				}
			}

			for (int k = 1; k <= N; k++) {
				if (adjMatrix[k][0] + adjMatrix[0][k] == N - 1) {
					answer++;
				}
			}

			System.out.println("#" + tc + " " + answer);

		}

	}

	static void dfs(int cur, int[][] adjMatrix) { // cur 학생부터 자신보다 키가 큰 학생따라 탐색

		// 행 기준: 나보다 큰 애들
		for (int i = 1; i <= N; i++) { // 자신의 인접 행렬 들여다 보기
			if (adjMatrix[cur][i] == 1) { // cur < i => 나보다 큰 애를 들여다 보고
				if (adjMatrix[i][0] == -1) // 나보다 큰 i가 탐색을 하지 않은 상태면 탐색 go
					dfs(i, adjMatrix); // 탐색하러 간다.

				// 탐색이 된 상태면 그 녀석의 탐색 정보 활용해서 나한테 반영하는 것
				// 나보다 큰 정점의 탐색 정보를 메모
				if (adjMatrix[i][0] > 0) { // i보다 큰 정점이 존재 : cur < i < j 를 만족하는 j 존재 ==> cur < j 상태로 메모
					for (int j = 1; j <= N; j++) {
						if (adjMatrix[i][j] == 1) { // i < j
							adjMatrix[cur][j] = 1; // i < j => cur < j
						}
					}
				}
			}
		}
		// 자신보다 큰 정점의 탐색을 모두 완료 => 메모하기
		int cnt = 0;
		for (int k = 1; k <= N; k++) {
			cnt += adjMatrix[cur][k]; // cur < k => 1의 개수만큼 더해짐!!
		}

		adjMatrix[cur][0] = cnt;

	}

}
