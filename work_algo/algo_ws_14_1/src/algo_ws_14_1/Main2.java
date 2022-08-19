package algo_ws_14_1;

import java.io.*;
import java.util.*;

public class Main2 {

	// 우상, 우, 우하
	private static final int[] dr = { -1, 0, 1 };
	private static final int[] dc = { 1, 1, 1 };

	private static final char BLANK = '.'; // 빈 칸
	private static final char PIPE = 'P'; // 파이프

	private static int R; // 빵집 근처의 모습 행 수
	private static int C; // 빵집 근처의 열 수
	private static char[][] data; // 빵집 근처의 모습
	private static int answer; // 파이프라인의 최대 개수

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);

		// 빵집 근처의 모습
		data = new char[R][C];
		for (int i = 0; i < R; i++) {
			String line = in.readLine();
			for (int j = 0; j < C; j++) {
				data[i][j] = line.charAt(j);
			}
		}

		// 답 초기화
		answer = 0;

		for (int i = 0; i < R; i++) {
			dfs(i, 0);
		}

		sb.append(answer);
		System.out.println(answer);
	}

	/**
	 * boolean 타입
	 */
	private static boolean dfs(int r, int c) {

		/**
		 * 기저 부분
		 */
		// 빵집에 도착했다면
		if (c == (C - 1)) {
			answer++; // 놓을 수 있는 파이프라인 개수 1 증가
			data[r][c] = PIPE; 
			return true;
		}

		// 진입 지점에 파이프 설치
		data[r][c] = PIPE;

		/**
		 * 유도 부분
		 */

		// 우상, 우, 우하 순으로 3방향 탐색
		for (int i = 0; i < 3; i++) {

			// 이동할 좌표 구하기
			int testR = r + dr[i];
			int testC = c + dc[i];

			// 경계 안이고 빈 칸이면 이동
			if ((0 <= testR && testR < R) && (0 <= testC && testC < C) && data[testR][testC] == BLANK) { // 이동하고자 하는 칸이
																											// 빈칸일 경우
				if (dfs(testR, testC)) { // 빵집까지 도착했다면 true이기 때문에 다른 방향 탐색을 멈춘다. (가지 치기)
					// 이미 건설된 건 ㄱㅊ
					// 근데 파이프 건설하다가 막다른 길에 다다랐을 때 다시 돌려주는 건 필요 없나..?
					// ㄴㄴ... 그 길은 어차피 막힌 셈이라서 필요 없음 
					return true;
				}
			}

		}
		return false;

	}

}
