package hwalgo25_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main2 {

	private static final int BLANK = 0;
	private static final int CHEESE = 1;

	// 우, 하, 좌, 상
	private static final int[] dr = { 0, 1, 0, -1 };
	private static final int[] dc = { 1, 0, -1, 0 };

	private static int R;
	private static int C;
	private static int[][] board;

	// 공기의 위치를 저장할 구조체
	private static class Air {
		public int r, c; // 행, 열

		public Air(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);

		board = new int[R][C];
		for (int i = 0; i < R; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(split[j]);
			}
		}

		int countOfLastOneHour = 0; // 모든 치즈가 사라지기 직전의 치즈 수
		int hours = 0; // 경과 시간
		while (true) {
			int cnt = countOfCheese(); // 현재 남아있는 치즈 개수

			// 기저 조건
			if (cnt == 0) {
				break;
			}

			// 유도 조건
			countOfLastOneHour = cnt;
			bfs();
			hours++;

		}
		sb.append(hours).append("\n").append(countOfLastOneHour);
		System.out.println(sb);

	}

	private static void bfs() {
		Queue<Air> queue = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[R][C];

		// 방문 예약
		queue.offer(new Air(0, 0));

		// 방문 표시
		isVisited[0][0] = true;

		while (!queue.isEmpty()) {
			// 큐 크기 확인 (동일 너비 대상 개수)
			int size = queue.size();

			while (--size >= 0) {
				Air curAir = queue.poll();
				int r = curAir.r;
				int c = curAir.c;

				// 4방향 탐색
				for (int i = 0; i < 4; i++) {

					// 이동할 곳의 좌표 확인
					int testR = r + dr[i];
					int testC = c + dc[i];

					// 경계 안이고 방문하지 않은 곳이며, 빈 칸이면 => 방문된 곳은 공기라는 뜻
					if ((0 <= testR && testR < R) && (0 <= testC && testC < C) && board[testR][testC] == BLANK
							&& !isVisited[testR][testC]) {

						// 방문 처리
						isVisited[testR][testC] = true;

						// 다음 칸 이동
						queue.offer(new Air(testR, testC));

					}

				}
			}
		}

		// 순차 탐색하면서 치즈 주변에 공기가 있다면 녹인다.
		for (int r = 1; r < R - 1; r++) {
			for (int c = 1; c < C - 1; c++) {
				if(board[r][c] == CHEESE) {
					
					// 치즈이면 사방 탐색하여 공기가 있는지 확인
					for(int d = 0; d < 4; d++) {
						int testR = r + dr[d];
						int testC = c + dc[d];
						
						// 만약 공기라면 치즈 녹이기
						if(isVisited[testR][testC]) {
							board[r][c] = BLANK;
							break;
						}
					}
					
				}
			}
		}
	}

	private static int countOfCheese() {

		int cnt = 0;

		for (int i = 1; i < R - 1; i++) {
			for (int j = 1; j < C - 1; j++) {
				if (board[i][j] == CHEESE) {
					cnt++;
				}
			}
		}

		return cnt;
	}

}
