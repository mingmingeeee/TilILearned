package algo_ws_27_2;

import java.io.*;
import java.util.*;

public class Main {

	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static int[][] map;
	private static boolean[][] visited;

	private static int N;
	private static int M;
	private static int size;

	private static int[] parents;

	private static class Bridge implements Comparable<Bridge> {
		int from;
		int to;
		int weight;

		public Bridge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Bridge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}

	}

	private static void make() {

		parents = new int[size];

		for (int i = 0; i < size; i++) {
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

		visited = new boolean[N][M];

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		// 1. 섬 번호 주기
		size = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					islands(i, j, size++);
//					System.out.println(i + " " + j + " " + size);
				}
			}
		}

//		System.out.println(size);
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}

		PriorityQueue<Bridge> queue = new PriorityQueue<>();

		// 2. 다리 연결하기
		// 4방 탐색 => 길이 정해지고 나면 계속해서 한 방향으로!
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				if (map[i][j] > 0) { // 번호가 정해져있다면 

					for (int d = 0; d < 4; d++) { // 현 자리에서 4방 탐색
						int bridge = 0;

						int testX = i;
						int testY = j;

						// 한 방향으로만 탐색하기 
						while (true) {

							testX += dx[d]; 
							testY += dy[d];

							if (isRange(testX, testY) && map[testX][testY] == 0) // 0이면 계속 다리 건설 
								bridge++;

							else if (isRange(testX, testY) && map[testX][testY] > 0 && map[testX][testY] != map[i][j]
									&& bridge > 1) {
								// 섬인데 같은 번호 섬이 아니고 다리 길이가 2 queue에 넣고 break;
								queue.offer(new Bridge(map[i][j], map[testX][testY], bridge));
								break;
							}

							else // 그 외 경우 break => 다른 방향으로 다리 건설할 수 있나 탐색
								break;

						}

					}

				}
				
			}
		}

//		while(!queue.isEmpty()) {
//			System.out.println(queue.poll().weight);
//		}

		// 3. 모든 섬을 연결했는지 탐색 => 크루스칼
		make();
		
		size -= 1;

		int min = 0;
		int k = 0;
		while(!queue.isEmpty()) {
			Bridge bridge = queue.poll();
//			System.out.println(bridge.weight + " " + bridge.from + " : " + bridge.to);
			if (union(bridge.from, bridge.to)) {
				min += bridge.weight;
				if (++k == size - 1)
					break;
			}
		}
		
		min = k != size - 1 ? -1 : min;

		sb.append(min);
		System.out.println(sb);

	}

	private static boolean isRange(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M)
			return true;
		return false;
	}

	private static void islands(int x, int y, int number) {

		map[x][y] = number;

		for (int d = 0; d < 4; d++) {
			int testX = x + dx[d];
			int testY = y + dy[d];

			if (isRange(testX, testY) && map[testX][testY] == 1 && !visited[testX][testY]) {
				visited[testX][testY] = true;
				islands(testX, testY, number);
			}
		}

	}

}
