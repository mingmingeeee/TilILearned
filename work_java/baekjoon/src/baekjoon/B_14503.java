package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_14503 {

	// 북 동 남 서
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	final static int BRICK = 1;
	final static int BLANK = 0;
	final static int CLEAN = 2;

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// N, M
		st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// (r, c), d
		st = new StringTokenizer(in.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		// N개의 줄 상태
		int[][] data = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < M; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int testX = 0;
		int testY = 0;

		// 처음 위치 청소
		data[r][c] = CLEAN;
		int clean = 1;

		// 알고리즘 시작
		while (true) {
			int flag = 0;
			// 현재 방향에서 4방 탐색
			for (int i = 0; i < 4; i++) {
				d = (d + 3) % 4; // 왼쪽 회전

				testX = r + dx[d];
				testY = c + dy[d];

				// 전진 하기 전 검사
				if (testX >= 0 && testX < N && testY >= 0 && testY < M && data[testX][testY] == BLANK) {
					// 전진
					r += dx[d];
					c += dy[d];
					data[r][c] = CLEAN;
					clean++;

					flag = 1; 

					break;
				}

			}

			if (flag == 0) {// 4방향 모두 청소되어 있거나 or 벽이라면 flag = 0일 것
				if (r - dx[d] >= 0 && r - dx[d] < N && c - dy[d] >= 0 && c - dy[d] < M) { // 경계 체크
					if (data[r - dx[d]][c - dy[d]] == CLEAN) { // 후진할 수 있다면 후진
						r -= dx[d];
						c -= dy[d];
					} else if (data[r - dx[d]][c - dy[d]] == BRICK) { // 벽이면 나감
						break;
					}
				} else break; // 그 외 경계 밖이면 나감 
			}

		}

		System.out.println(clean);

	}

}