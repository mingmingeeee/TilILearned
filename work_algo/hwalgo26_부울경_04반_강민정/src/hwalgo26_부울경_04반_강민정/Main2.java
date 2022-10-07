package hwalgo26_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main2 {

	private static class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {

			// 편의점 개수
			int N = Integer.parseInt(in.readLine());

			// 각 정점의 정보를 리스트에 담기
			List<Point> points = new ArrayList<>();

			for (int i = 0; i < N + 2; i++) {
				String[] split = in.readLine().split(" ");
				int x = Integer.parseInt(split[0]);
				int y = Integer.parseInt(split[1]);

				points.add(new Point(x, y));
			}

			// 플로이드 워샬 알고리즘 활용하여 문제 풀기
			int INF = 99999;

			// 동적 테이블
			int[][] D = new int[N + 2][N + 2];

			// 동적 테이블 채우기
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					Point p1 = points.get(i);
					Point p2 = points.get(j);

					int distance = Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
					D[i][j] = distance;

					// 자기 자신으로의 인접 정보가 아니고,
					// 두 정점 사이의 거리가 1000m 초과할 경우 INF로 채우기 (갈 수 없다는 뜻으로 사용)
					if (i != j && D[i][j] > 1000) {
						D[i][j] = INF;
					}

				}
			}

			// 플로이드 워샬 알고리즘
			// 경유지 -> 출발지 -> 도착지 (경! 출! 도!)
			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					// 출발지와 경유지가 같다면 다음 출발지
					if (i == k)
						continue;
					for (int j = 0; j < N + 2; j++) {
						// 경유지와 도착지가 같거나 출발지가 곧 도착지이면 패스
						if (i == j || k == j)
							continue;

						// 최단 거리 갱신하기
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}

			// 상근이네 집(0)에서 페스티벌(N + 1)까지 INF가 아니면 갈 수 있다.
			String result = D[0][N + 1] != INF ? "happy" : "sad";

			sb.append(result).append("\n");
		}
		System.out.println(sb);

	}

}
