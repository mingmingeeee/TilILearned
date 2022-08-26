package algo_ws_20_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

// 각 칸에 도둑 루피
// 잃을 수 있는 최소 금액 출력

// 그리디 -> MST? 다익스트라?

public class Main2 {

	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };

	private static int[][] map;
	private static int N;
	private static int min;
	private static int[][] D;

	private static class Node implements Comparable<Node> {
		int x;
		int y;
		int weight;

		public Node(int x, int y, int weight) {
			super();
			this.x = x;
			this.y = y;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int test_case = 0;
		while (true) {
			N = Integer.parseInt(in.readLine());

			if (N == 0)
				break;

			map = new int[N][N];
			min = Integer.MAX_VALUE;
			D = new int[N][N];

			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}

			Daijkstra();

			sb.append("Problem " + (++test_case) + ": " + D[N - 1][N - 1]).append("\n");

		}

		System.out.println(sb);

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N)
			return true;
		return false;
	}

	private static void Daijkstra() {

		PriorityQueue<Node> pQueue = new PriorityQueue<>();

		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++)
			Arrays.fill(D[i], Integer.MAX_VALUE);

		D[0][0] = map[0][0];

		pQueue.offer(new Node(0, 0, D[0][0]));

		while (!pQueue.isEmpty()) {

			Node current = pQueue.poll();

			if (visited[current.x][current.y])
				continue;

			visited[current.x][current.y] = true;

			if (current.x == N - 1 && current.y == N - 1)
				return;

			for (int d = 0; d < dx.length; d++) {
				int testX = current.x + dx[d];
				int testY = current.y + dy[d];

				if (isRange(testX, testY) && !visited[testX][testY]
						&& D[testX][testY] > D[current.x][current.y] + map[testX][testY]) {
					D[testX][testY] = D[current.x][current.y] + map[testX][testY];
					pQueue.offer(new Node(testX, testY, D[testX][testY]));
				}
			}

		}

	}

}
