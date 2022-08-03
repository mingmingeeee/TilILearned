package com.ssafy.hw.problem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution2 {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + "\n");

			// 달팽이 크기
			int N = Integer.parseInt(in.readLine());

			// 달팽이 2차원 배열
			int[][] data = new int[N][N];

			/**
			 * 2. 알고리즘 풀기
			 */
			// 경계 -> 방향 전환 (우, 하, 좌, 상)
			int[] dx = { 0, 1, 0, -1 };
			int[] dy = { 1, 0, -1, 0 };

			int num = 1; // 달팽이 숫자
			int endNum = N * N; // 끝 숫자

			// (0, 0) 부터 시작
			int curX = 0;
			int curY = 0;

			// 방향은 우측부터~!^>^
			int dir = 0; // dx[0], dy[0]

			// 첫 번째 달팽이 숫자를 배열에 저장
			data[curX][curY] = num;

			while (num < endNum) {
				int testX = curX + dx[dir];
				int testY = curY + dy[dir];

				// 이동 할 좌표 경계 체크와 방문 여부 확인
				if ((0 <= testX && testX < N) &&
						(0 <= testY && testY < N) && 
						(data[testX][testY]) == 0) { // 방문 여부 체크																					// 전)

					// 현재 좌표 이동
					curX = testX;
					curY = testY;

					// 현재 좌표에 달팽이 숫자 저장
					data[curX][curY] = ++num;
				} else {
					// 경계를 벗어나거나, 이미 방문 좌표일 경우는 !!!방향 전환!!!
					dir = (dir + 1) % 4; // 0, 1, 2, 3 순으로 순환 
				}
			}

			/**
			 * 3. 정답 출력
			 */
			for(int i = 0; i < N; i++) {
				for(int j=0; j<N; j++) {
					sb.append(data[i][j]).append(" ");
				}
				sb.append("\n");
			}

		}
		System.out.println(sb);
	}
}
