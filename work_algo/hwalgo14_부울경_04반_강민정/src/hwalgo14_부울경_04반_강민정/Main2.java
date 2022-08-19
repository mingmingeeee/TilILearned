package hwalgo14_부울경_04반_강민정;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2 {

	// 우, 하, 좌, 상
	private static final int[] dr = { 0, 1, 0, -1 };
	private static final int[] dc = { 1, 0, -1, 0 };

	private static int R;  // 행
	private static int C;  // 열
	private static int[][] data;  // 말판
	private static boolean[] isVisited;  // 방문 배열

	private static int answer;  // 말이 지날 수 있는 최대의 칸 수

	public static void main(String args[]) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		System.setIn(new FileInputStream("input3.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		String[] split = in.readLine().split(" ");
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);

		data = new int[R][C];
		for (int i = 0; i < R; i++) {
			String line = in.readLine();
			for (int j = 0; j < C; j++) {
				data[i][j] = line.charAt(j) - 'A';  // 알파벳을 방문 배열의 인덱스 번호로 활용
			}
		}

		// 26은 알파벳 개수
		isVisited = new boolean[26];

		// 답 초기화
		answer = 0;

		/**
		 * 2. 알고리즘 풀기
		 */
		dfs(0, 0, 0);

		/**
		 * 3. 정답 출력
		 */
		sb.append(answer);
		System.out.println(sb);
	}

	private static void dfs(int cnt, int r, int c) {

		/**
		 * 기저 부분
		 */
		if (isVisited[data[r][c]]) {
			if (cnt > answer) {
				answer = cnt;
			}

			return;
		}

		/**
		 * 유도 부분
		 */
		// 방문 처리
		isVisited[data[r][c]] = true;

		// 4방 탐색
		for (int i = 0; i < 4; i++) {
			int testR = r + dr[i];
			int testC = c + dc[i];

			// 경계 검사
			if ((0 <= testR && testR < R) && (0 <= testC && testC < C)) {
				dfs(cnt + 1, testR, testC);
			}
		}

		// 다른 경우의 수에 영향을 끼치면 안되므로 방문 취소 처리
		isVisited[data[r][c]] = false;
	}
}
