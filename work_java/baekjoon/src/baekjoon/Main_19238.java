package baekjoon;

import java.io.*;
import java.util.*;

// M명의 승객 태우는 것이 목표 => 빈 칸중 하나에 서 있음
// 빈 칸 => 상하좌우 인접한 빈칸 중 하나로 이동 가능
// 이동할 때 항상 최단 경로로만 이동
// 승객 태울 때 최단 거리의 승객 고름
// 그런 승객 여러명 => 행 번호 가장 작은 승객 => 열 번호 가장 작은 승객
// 연료: 한 칸 이동할 때마다 1만큼 소모 => 목적지로 이동시키면 소모한 연로 양 두 배 충전됨
// 이동 도중에 바닥나면 이동 실패 

// 출력: 최종적으로 남는 연료의 양

public class Main_19238 {

	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static final int BLANK = 0;
	private static final int WALL = 1;

	private static int[][] map;

	private static int N;
	private static int M;
	private static Taxi taxi;

	private static List<Passenger> passengers = new ArrayList<>();

	private static class Taxi {
		int x;
		int y;

		public Taxi(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static class Position implements Comparable<Position> {
		int x, y, dis, idx;

		public Position(int x, int y, int dis, int idx) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.idx = idx;
		}

		@Override
		public int compareTo(Position o) {
			if (this.dis == o.dis) {

				if (this.x == o.x)
					return this.y - o.y;

				return this.x - o.x;

			}
			return this.dis - o.dis;
		}
	}

	private static class Passenger {
		int start_x, start_y, des_x, des_y;

		public Passenger(int start_x, int start_y, int des_x, int des_y) {
			super();
			this.start_x = start_x;
			this.start_y = start_y;
			this.des_x = des_x;
			this.des_y = des_y;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(in.readLine(), " ");

		taxi = new Taxi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine(), " ");

			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			passengers.add(new Passenger(x1, y1, x2, y2));
		}

		while (!passengers.isEmpty()) {

			List<Position> candidate = bfs();
			
			if(candidate == null) {
				fuel = -1;
				break;
			}

			int idx = candidate.get(0).idx;
			int D = candidate.get(0).dis;

			Passenger cur = passengers.get(idx);
			go(cur.start_x, cur.start_y);
			int dis = go(cur.des_x, cur.des_y);

			if (dis == -1) {
				fuel = -1;
				break;
			}

			fuel = fuel - (dis + D);

			if (fuel < 0) {
				fuel = -1;
				break;
			}

			fuel += dis * 2;

			passengers.remove(candidate.get(0).idx);
		}
		sb.append(fuel);

		System.out.println(sb);

	}

	// 목적지로 이동
	private static int go(int x, int y) {

		Queue<Taxi> queue = new ArrayDeque<>();
		queue.offer(taxi);
		boolean[][] visited = new boolean[N + 1][N + 1];

		visited[taxi.x][taxi.y] = true;

		int depth = 0;
		while (!queue.isEmpty()) {

			int size = queue.size();

			for (int i = 0; i < size; i++) {

				Taxi cur = queue.poll();

				if (cur.x == x && cur.y == y) {
					taxi = new Taxi(cur.x, cur.y);
					return depth;
				}

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX >= 1 && testX < N + 1 && testY >= 1 && testY < N + 1 && !visited[testX][testY]) {
						if (map[testX][testY] == BLANK) {
							visited[testX][testY] = true;
							queue.offer(new Taxi(testX, testY));
						}
					}
				}

			}

			depth++;

		}

		return -1;

	}

	// 택시와의 최단거리 구하기
	private static List<Position> bfs() {
		List<Position> candidate = new ArrayList<>();

		Queue<Taxi> queue = new ArrayDeque<>();
		queue.offer(taxi);
		boolean[][] visited = new boolean[N + 1][N + 1];

		visited[taxi.x][taxi.y] = true;

		int depth = 0;
		boolean isTrue = false;
		while (!queue.isEmpty()) {

			int size = queue.size();

			for (int i = 0; i < size; i++) {

				Taxi cur = queue.poll();

				for (int idx = 0; idx < passengers.size(); idx++) {
					if (cur.x == passengers.get(idx).start_x && cur.y == passengers.get(idx).start_y) {
						candidate.add(new Position(cur.x, cur.y, depth, idx));
						isTrue = true;
					}
				}

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX >= 1 && testX < N + 1 && testY >= 1 && testY < N + 1 && !visited[testX][testY]) {
						if (map[testX][testY] == BLANK) {
							visited[testX][testY] = true;
							queue.offer(new Taxi(testX, testY));
						}
					}
				}

			}

			if (isTrue) {
				Collections.sort(candidate);
				return candidate;
			}

			depth++;

		}

		return null;

	}

}
