package hwalgo27_부울경_04반_강민정;

// "그래프 문제" => 인접 행렬, 인접 리스트
// 정점: 학생
// 간선: 학생들 간 키 관계 
// (1. 가중치 X 
//  2. 간선의 방향성: O 유향 그래프 => "a -> b": a가 b보다 키가 크다)
// 학생이 몇 번째인지 알고 싶을때: n - 1관계 필요 

import java.io.*;
import java.util.*;

public class Solution5 {

	static int N, M;
	static int cnt;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(in.readLine());
			M = Integer.parseInt(in.readLine());

			int[][] adjMatrix = new int[N + 1][N + 1]; // 학생 번호 1부터 처리


			StringTokenizer st = null;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(in.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = 1; // a보다 b가 키가 크다
			}

			int answer = 0;

			for (int k = 1; k <= N; ++k) { // 경유 (가능한 모든 학생)
				for (int i = 1; i <= N; ++i) { // 출발 (자신)
					if (i == k)
						continue;
					for (int j = 1; j <= N; ++j) { // 도착 (상대)
						if (adjMatrix[i][j] == 1)
							continue;
						adjMatrix[i][j] = adjMatrix[i][k] & adjMatrix[k][j];
					}
				}

			}

			// 모든 정점이 알고 있는 관계로 탐색을 마친 상태 (큰 정점을 따라 탐색해서 간접 관계를 직접 관계로 다 반영해서 인접 행렬 상태
			// update)
			// 열 기준 정보를 확인하면 자신보다 작은 정점 파악 가능
			for (int i = 1; i <= N; ++i) {
				for (int j = 1; j <= N; ++j) {
					adjMatrix[i][0] += adjMatrix[i][j]; // i보다 큰 j가 결국 카운트에 누적
					adjMatrix[0][j] += adjMatrix[i][j]; // j보다 작은 i가 결국 카운트에 누적
				}
			}

			for (int k = 1; k <= N; ++k) {
				if (adjMatrix[k][0] + adjMatrix[0][k] == N - 1)
					answer++;
			}

			System.out.println("#" + tc + " " + answer);

		}

	}

}
