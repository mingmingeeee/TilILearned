package baekjoon;

import java.io.*;
import java.util.*;

// 간적크 간많프

public class B_17472 {

	// 우, 하, 좌, 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static int[][] map;
	private static boolean[][] visited;

	private static int N;
	private static int M;

	private static int island;

	private static int[] parents;

	private static class Edge implements Comparable<Edge> {
		public int from;
		public int to;
		public int weight;

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	private static void make() {
		parents = new int[island + 1];
		for (int i = 0; i <= island; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		// 섬 (정점) 나누기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					island++;
					dfs(i, j);
				}
			}
		}

		// 다리 정보 (간선)
		List<Edge> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) { // 섬
					for (int d = 0; d < 4; d++) { // 4방 탐색 -> 섬 이을 수 있는
						int testX = i + dx[d];
						int testY = j + dy[d];

						if (isRange(testX, testY)) {
							if (map[testX][testY] != 0) {
								continue; // 섬 내부일 경우 다른 방향으로 탐색
							} else { // 바다일 경우
								Edge temp = new Edge();
								temp.from = map[i][j]; // 이 섬에서 부터
								int tempX = testX;
								int tempY = testY;

								// 다리 건설 -> 한 방향으로만
								for (int cnt = 1; 0 <= tempX && tempX < N && 0 <= tempY && tempY < M; cnt++) {
									tempX += dx[d];
									tempY += dy[d];

									if (isRange(tempX, tempY)) {
										if (map[tempX][tempY] == 0) { // 계속 다리 건설
											continue;
										} else { // 섬이라면?
											if (cnt > 1) { // 다리 길이가 2부터
												temp.to = map[tempX][tempY]; // 이 섬까지
												temp.weight = cnt; // 다리 길이
												list.add(temp);
											}
											break;
										}
									}
								}
							}
						}

					}
				}
			}
		}
		
		make();

		Collections.sort(list);

//		System.out.println();
//		for (Edge item : list) {
//			System.out.println(item);
//		}

		int k = 0;
		int result = 0;

		for (Edge e : list) {
			
			if (union(e.from, e.to)) {

				result += e.weight;

				// 간선의 개수는 섬의 개수 - 1 개
				if (++k == island - 1) {
					break;
				}

			}
		}

		if (k == island - 1)
			System.out.println(result);
		else
			System.out.println(-1);

	}

	private static boolean isRange(int x, int y) {

		if (0 <= x && x < N && 0 <= y && y < M) {
			return true;
		}

		return false;

	}

	private static void dfs(int x, int y) {

		visited[x][y] = true;
		map[x][y] = island;

		for (int i = 0; i < dx.length; i++) {
			int testX = x + dx[i];
			int testY = y + dy[i];
			if (isRange(testX, testY) && map[testX][testY] == 1 && !visited[testX][testY]) {
				dfs(testX, testY);
			}
		}

	}

}
