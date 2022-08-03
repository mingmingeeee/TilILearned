package algo_ws_02_3;

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
			sb.append("#" + test_case + " ");

			// 농장의 크기
			int N = Integer.parseInt(in.readLine());

			// 농장물의 가치

			int[][] data = new int[N][N];
			for (int i = 0; i < N; i++) {
				String newLine = in.readLine();
				for (int j = 0; j < N; j++) {
					data[i][j] = newLine.charAt(j) - '0'; // char형 -> int형 변환
				}
			}

			/**
			 * 2. 알고리즘 풀기
			 */
			int centerIndex = N / 2;
			int blankCount = N / 2;
			
			int answer = 0;  // 수익
			for (int i = 0; i < N; i++) {
				for (int j = blankCount; j < N - blankCount; j++) {
					answer += data[i][j];
				}
				
				if (i < centerIndex) {
					blankCount--;
				}
				else {
					blankCount++;
				}
			}

			/**
			 * 3. 정답 출력
			 */
			sb.append(answer + "\n");
		}
		System.out.println(sb);
	}
}
