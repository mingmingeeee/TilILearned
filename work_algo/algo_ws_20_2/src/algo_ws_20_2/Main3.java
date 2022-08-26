package algo_ws_20_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main3 {

	// 우, 하, 좌, 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static class Vertex implements Comparable<Vertex> {
		public int x;
		public int y;
		public int minDistance;  // 출발지에서 자신으로의 최소비용

		public Vertex(int x, int y, int minDistance) {
			this.x = x;
			this.y = y;
			this.minDistance = minDistance;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.minDistance - o.minDistance;
		}
	}

	public static void main(String args[]) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input1.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		int T = 1;
		while (true) {

			/**
			 * 1. 입력 파일 객체화
			 */
			int N = Integer.parseInt(in.readLine());  // 한 변의 길이
			if (N == 0) {
				break;
			}

			int[][] map = new int[N][N];
			int startX = 0;
			int startY = 0;
			int endX = N - 1;
			int endY = N - 1;

			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}

			int[][] distance = new int[N][N];  // 출발지에서 자신으로의 최소(최단) 비용(거리) => 최단 경로 구성 가중치 합
			boolean[][] visited = new boolean[N][N];  // 최소비용 확정여부
			PriorityQueue<Vertex> pQueue = new PriorityQueue<>();

			for (int i = 0; i < N; i++) {
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}

			/**
			 * 2. 알고리즘 풀기
			 */
			distance[startX][startY] = map[startX][startY];  // 시작점 최소 비용을 시작지점 도둑루피의 크기로 설정
			pQueue.offer(new Vertex(startX, startY, distance[startX][startY]));

			while (!pQueue.isEmpty()) {

				// 1단계 : 미방문 정점 중 출발지에서 자신으로의 비용이 최소인 정점 선택
				// 방문 해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
				Vertex current = pQueue.poll();

				// 이미 처리된 정점이 큐에서 또 나오면 무시
				if (visited[current.x][current.y]) {
					continue;
				}

				// 2단계: 방문처리
				visited[current.x][current.y] = true;  // 선택 정점 방문 처리
				
				// 문제가 start -> end로의 최단이면 탈출
				if (current.x == endX && current.y == endY) {
					break;  // current가 도착지라면 끝냄
				}

				// 3단계 : 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고 기존 최적해보다 유리하면 갱신
				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int testX = current.x + dx[i];
					int testY = current.y + dy[i];
						
					// 경계 체크
					if ((0 <= testX && testX < N) && (0 <= testY && testY < N)) {
						
						if (!visited[testX][testY] && 
								distance[testX][testY] > distance[current.x][current.y] + map[testX][testY]) {
							distance[testX][testY] = distance[current.x][current.y] + map[testX][testY];
							pQueue.offer(new Vertex(testX, testY, distance[testX][testY]));
						}
					}
				}
			}
			
			
			/**
			 * 3. 정답 출력
			 */
			sb.append("Problem ").append(T++).append(": ").append(distance[endX][endY]).append("\n");
		}
		System.out.println(sb);
	}
}
