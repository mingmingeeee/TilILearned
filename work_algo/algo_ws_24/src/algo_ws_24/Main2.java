package algo_ws_24;

import java.io.*;
import java.util.*;

public class Main2 {

	// 4방향 탐색 (우, 하, 좌, 상)
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	// 말이 이동할 수 있는 8방향 (맨 우측 상단부터 시계방향)
	private static final int[] hdx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	private static final int[] hdy = { 1, 2, 2, 1, -1, -2, -2, -1 };

	private static final int GROUND = 0; // 평지

	private static int K; // 말처럼 이동할 수 있는 횟수
	private static int W; // 맵의 가로 길이
	private static int H; // 맵의 세로 길이
	private static int[][] map; // 입력 데이터에서 주어진 맵

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		K = Integer.parseInt(in.readLine());

		String[] split = in.readLine().split(" ");
		W = Integer.parseInt(split[0]);
		H = Integer.parseInt(split[1]);

		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		int min = bfs(0, 0);
		
		System.out.println(min);

	}

	private static int bfs(int startX, int startY) {

		Queue<Monkey> queue = new ArrayDeque<>();

		// 말처럼 0번 이동한 경우 ~ 말처럼 K번 이동한 경우 각각 isVisited 2차원 배열을 만들어 줌
		boolean[][][] isVisited = new boolean[H][W][K + 1];

		queue.offer(new Monkey(startX, startY, 0, K));

		// 방문 표시
		isVisited[startX][startY][K] = true;

		while (!queue.isEmpty()) {

			// 큐 크기 확인 (동일 너비 대상 개수)
			int size = queue.size();

			while (--size >= 0) {
				Monkey curMonkey = queue.poll();

				// 기저조건 (종료조건: 맨 오른쪽 아래 도착)
				if (curMonkey.x == H - 1 && curMonkey.y == W - 1) {
					return curMonkey.count;
				}

				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int testX = curMonkey.x + dx[i];
					int testY = curMonkey.y + dy[i];

					// 경계 안이고 방문하지 않은 곳이면
					if ((0 <= testX && testX < H) && (0 <= testY && testY < W)
							&& !isVisited[testX][testY][curMonkey.k]) {

						// 평지일 경우
						if (map[testX][testY] == GROUND) {
							// 방문처리
							isVisited[testX][testY][curMonkey.k] = true;
							// 다음 칸 이동
							queue.offer(new Monkey(testX, testY, curMonkey.count + 1, curMonkey.k));
						}
					}
				}

				// K 횟수가 0보다 크면 말처럼 점프 
				if (curMonkey.k > 0) { // 말처럼 뛸 수 있는 횟수가 있다면

					for (int i = 0; i < 8; i++) {
						int testX = curMonkey.x + hdx[i];
						int testY = curMonkey.y + hdy[i];

						// 경계 안이고 방문하지 않은 곳이면
						if ((0 <= testX && testX < H) && (0 <= testY && testY < W)
								&& !isVisited[testX][testY][curMonkey.k - 1]) {

							// 평지일 경우
							if (map[testX][testY] == GROUND) {
								// 방문처리
								isVisited[testX][testY][curMonkey.k] = true;
								// 다음 칸 이동
								queue.offer(new Monkey(testX, testY, curMonkey.count + 1, curMonkey.k - 1));
							}
						}
					}

				}

			}

		}
		
		// 도착점까지 갈 수 없는 경우엔 -1 출력 
		return -1;
	}

	private static class Monkey {
		public int x; // 원숭이의 현재 위치 x
		public int y; // 원숭이의 현재 위치 y
		public int count; // 현재까지 이동 횟수
		public int k; // 말처럼 뛸 수 있는 횟수 (남은 횟수)

		public Monkey(int x, int y, int count, int k) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.k = k;
		}
	}

}
