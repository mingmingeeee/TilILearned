package hwalgo18_부울경_04반_강민정;

import java.io.*;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main2 {

	// 우, 하, 좌, 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static int N;
	private static char[][] picture;
	private static boolean[][] isVisited;
	private static int cnt1;  // 적록색약이 아닌 사람이 봤을 때의 구역의 개수
	private static int cnt2;  // 적록색약인 사람이 봤을 때의 구역의 개수

	private static class Pixel {
		public int x;
		public int y;
		public char color;

		public Pixel(int x, int y, char color) {
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input1.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		N = Integer.parseInt(in.readLine());

		picture = new char[N][N];
		for (int i = 0; i < N; i++) {
			String line = in.readLine();
			for (int j = 0; j < N; j++) {
				picture[i][j] = line.charAt(j);
			}
		}

		/**
		 * 2. 알고리즘 풀기
		 */
		// 색약이 아닌 사람이 봤을 때 구역의 수 구하기
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bfs(i, j, false);  // false는 색약이 아닌 경우를 뜻함
			}
		}

		// 색약인 사람이 봤을 때 구역의 수 구하기
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				bfs(i, j, true);  // true는 색약인 경우를 뜻함
			}
		}

		/**
		 * 3. 정답 출력
		 */
		sb.append(cnt1).append(" ").append(cnt2);
		System.out.println(sb);
	}

	private static void bfs(int x, int y, boolean isColorBlind) {

		// 기저 부분: 이미 탐색한 곳이라면 즉시 종료
		if (isVisited[x][y]) {
			return;
		}

		Queue<Pixel> queue = new ArrayDeque<>();

		isVisited[x][y] = true;
		queue.offer(new Pixel(x, y, picture[x][y]));

		while (!queue.isEmpty()) {

			// 큐 크기 확인 (동일 너비 대상 개수)
			int size = queue.size();

			while (--size >= 0) {
				// 현재 정점 꺼내기
				Pixel pixel = queue.poll();

				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int testX = pixel.x + dx[i];
					int testY = pixel.y + dy[i];

					// 경계 체크
					if ((0 <= testX && testX < N) && (0 <= testY && testY < N)) {

						// 방문하지 않았다면
						if (!isVisited[testX][testY]) {

							// 색약이 아니고, 처음 탐색할 때의 픽셀 색깔이 동일하면
							if (!isColorBlind && (picture[testX][testY] == pixel.color)) {

								// 방문 처리 후, 큐에 넣어 방문 예약
								isVisited[testX][testY] = true;
								queue.offer(new Pixel(testX, testY, pixel.color));
							}
							// 색약이면
							else if (isColorBlind) {

								// 색깔에 따라 탐색하는 조건이 달라짐
								switch (pixel.color) {
								case 'R':
								case 'G':
									if (picture[testX][testY] == 'R' || picture[testX][testY] == 'G') {
										// 방문 처리 후, 큐에 넣어 방문 예약
										isVisited[testX][testY] = true;
										queue.offer(new Pixel(testX, testY, pixel.color));
									}
									break;

								case 'B':
									if (picture[testX][testY] == 'B') {
										// 방문 처리 후, 큐에 넣어 방문 예약
										isVisited[testX][testY] = true;
										queue.offer(new Pixel(testX, testY, pixel.color));
									}
									break;
								}
							}
						}
					}
				}
			}
		}

		// 큐를 비울 때까지 탐색했다는 것은, 한 구역을 모두 탐색을 했다는 것이고,
		// 그말은 한 구역이 같은 색상이라는 뜻
		if (isColorBlind) {
			cnt2++;  // 색약인 사람이 봤을 때 구역의 수 증가
		}
		else {
			cnt1++;  // 색약이 아닌 사람이 봤을 때 구역의 수 증가
		}
	}
}

