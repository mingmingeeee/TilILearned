package algo_ws_20_3;

import java.io.*;
import java.util.*;

public class Main2 {

	// 우, 하, 좌, 상
	private static final int[] dr = { 0, 1, 0, -1 };
	private static final int[] dc = { 1, 0, -1, 0 };

	private static final int CLEANER = -1; // 공기 청정기

	private static int R;
	private static int C;
	private static Queue<Dust> queue; // 미세먼지 BFS를 위한 큐
	private static int[][] room;
	private static int[][] copy; // 미세먼지 확산 후 순환 전 상태의 방을 저장

	private static class Dust {
		public int r; // 행
		public int c; // 열
		public int amount; // 미세먼지의 양

		public Dust(int r, int c, int amount) {
			super();
			this.r = r;
			this.c = c;
			this.amount = amount;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		int T = Integer.parseInt(split[2]); // T초 동안 공기 청정기 가동

		queue = new ArrayDeque<>();

		room = new int[R][C];
		int cleanerCCW = -1; // 반시계 순환 공기 청정기 행 인덱스 (윗쪽 공기청정기)
		int cleanerCW = -1; // 시계 순환 공기 청정기 행 인덱스 (아래쪽 공기청정기)

		for (int i = 0; i < R; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(split[j]);
				if (room[i][j] > 4) { // 확산될 수 있는 먼지일 경우 (5 이상일 경우만 확산 가능)
					queue.offer(new Dust(i, j, room[i][j])); // 확산할 미세 먼지를 큐에 등록
				} else if (room[i][j] == CLEANER) { // 공기 청정기일 경우
					// 최종적으로 저장되는 좌표는 시계 방향 순환 공기 청정기의 행 인덱스
					cleanerCW = i;
				}

			}
		}

		// 반시계 순환 공기 청정기의 행 인덱스는 시계 순환 공기 청정기 바로 위의 행 인덱스
		cleanerCCW = cleanerCW - 1;

		// 매 초마다 while문과 같이 반복
		while (T > 0) {

			// 1. 미세 먼지가 확산
			bfsDust();

			// 2. 확산 직후의 방 상태 저장
			saveAfterDust();

			// 아래 두 배열의 요소들은 dr, dc 배열을 접근하는 인덱스
			int[] ccw = { 0, 3, 2, 1, }; // 우, 상, 좌, 하 (반시계)
			int[] cw = { 0, 1, 2, 3 }; // 우, 하, 좌, 상 (시계)

			// 3. 공기 청정기가 가동 (반시계 방향)
			clean(cleanerCCW,  0,  ccw);
			
			// (시계 방향)
			clean(cleanerCW,  0,  cw);
			
			// 4. 공기 청정 후 미세먼지들을 큐에 등록 (1초 뒤 1번이 실행되면서 확산)
			afterClean();
			
			T--;
			
		}

	}

	// r, c: 공기 청정기의 좌표
	// d: 공기 청정기 순환을 위한 델타 배열
	// (백준 16926, 16935, 17406 문제 리뷰해보기)
	private static void clean(int r, int c, int[] d) {

		// 공기 순환 시작점 잡기
		int x = r;
		int y = c + 1;

		// 공기 정화
		room[x][y] = 0;

		// 전달 받은 d에 따라 순환
		for (int i = 0; i < 4; i++) {

			while (true) {

				int testR = x + dr[d[i]];
				int testC = y + dc[d[i]];

				// 경계를 벗어나면 반복문 종료 (다음 방향으로 전환)
				if (!(0 <= testR && testR < R && 0 <= testC && testC < C)) {
					break;
				}

				// 공기 청정기는 순환되면 안 된다.
				if (testR == r && testC == c) {
					break;
				}

				room[testR][testC] = copy[x][y];
				x = testR;
				y = testC;

			}

		}

	}

	private static void saveAfterDust() {
		copy = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				copy[i][j] = room[i][j];
			}
		}
	}

	private static void bfsDust() {

		// 큐 크기 확인 (동일 너비 대상 개수)
		int size = queue.size();

		while (--size >= 0) {
			// 현재 정점 꺼내기
			Dust dust = queue.poll();
			int amount = dust.amount / 5; // 확산되는 양
			int cnt = 0; // 확산된 방향의 개수

			// 4방향 확산
			for (int i = 0; i < 4; i++) {
				int testR = dust.r + dr[i];
				int testC = dust.c + dc[i];

				// 경계 체크
				if ((0 <= testR && testR < R) && (0 <= testC && testC < C)) {

					// 공기 청정기가 아니면
					if (room[testR][testC] != CLEANER) {
						cnt++; // 확산된 방향의 개수 증가
						room[testR][testC] += amount; // 해당 칸으로 미세먼지 확산하고 누적
					}

				}
			}

			// 4방향 확산 후 남은 미세먼지의 양 계싼
			room[dust.r][dust.c] -= amount * cnt;
		}

	}

}
