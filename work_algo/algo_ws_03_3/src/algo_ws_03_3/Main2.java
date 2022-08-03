package algo_ws_03_3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2 {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		System.setIn(new FileInputStream("input1.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		// N과 M
		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		
		// 2차원 배열 생성
		int[][] data = new int[N + 1][N + 1];  // 0번 인덱스는 사용 안함
		
		// 2차원 배열에 데이터 입력
		for (int i = 1; i < N + 1; i++) {
			split = in.readLine().split(" ");
			for (int j = 1; j < N + 1; j++) {
				data[i][j] = Integer.parseInt(split[j - 1]);
			}
		}
		
		/**
		 * 2. 알고리즘 풀기
		 */
		// 누적 합을 저장할 2차원 배열
		int[][] sum = new int[N + 1][N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				// 누적 합 = 좌측 값 + 상측 값 - 좌상측 값 + 현재 위치 값
				sum[i][j] = sum[i][j - 1] + sum[i - 1][j] - sum[i - 1][j - 1] + data[i][j];
			}
		}
		
		// 아래 과정을 M번 반복
		for (int i = 0; i < M; i++) {
			// 네 개의 정수 x1, y1, x2, y2 가져오기
			split = in.readLine().split(" ");
			int x1 = Integer.parseInt(split[0]);
			int y1 = Integer.parseInt(split[1]);
			int x2 = Integer.parseInt(split[2]);
			int y2 = Integer.parseInt(split[3]);
			
			// (x2, y2) - (x1 - 1, y2) - (x2, y1 - 1) + (x1 - 1, y1 - 1)
			int answer = sum[x2][y2] - sum[x1 - 1][y2] - sum[x2][y1 - 1] + sum[x1 - 1][y1 - 1];
			sb.append(answer);
			sb.append("\n");
		}
		
		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);
	}
}
