package algo_ws_07_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Solution2 {

	// 상 하 좌 우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N;
	static int[][] data;

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			/**
			 * 1. 입력 파일 객체화
			 */
			sb.append("#" + test_case + " ");

			N = Integer.parseInt(in.readLine());

			data = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] strings = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					data[i][j] = Integer.parseInt(strings[j]);
				}
			}

			max = -1;
			min_x = Integer.MAX_VALUE;
			// 0, 0부터 시작 -> 결과 저장하는 로직 만들기 -> 반복... N*N만큼 반복
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {

					move(x, y, 1, x, y);

				}
			}

			sb.append(min_x).append(" ").append(max).append("\n");
		}

		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);

	}

	static int max = -1;
	static int min_x = Integer.MAX_VALUE;

	public static void move(int x, int y, int count, int x1, int y1) {
		System.out.println(x + " " + y);

		for (int i = 0; i < 4; i++) {
			System.out.println("d");
			if (x + dx[i] < 0 || x + dx[i] >= N || y + dy[i] < 0 || y + dy[i] >= N) continue;
			if (data[x][y] + 1 != data[x + dx[i]][y + dy[i]]) continue;
			
			move(x + dx[i], y + dy[i], count, x1, y1);
			
			if (max < count) {
				max = count;
				min_x = data[x1][y1];
			} else if (max == count && min_x > data[x1][y1]) {
				min_x = data[x1][y1];
			}

			
			break;
		}
		return;

	}

}


