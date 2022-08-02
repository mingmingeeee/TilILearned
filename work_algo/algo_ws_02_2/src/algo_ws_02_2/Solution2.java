package algo_ws_02_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2 {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		int T;
		T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			
			// 첫 줄은 무의미 하므로 읽고 넘어간다.
			in.readLine();
			
			// 각 케이스의 사다리 정보를 2차원 배열에 담기
			int[][] data  = new int[100][100];
			for (int i = 0; i < 100; i++) {
				// input 파일에서 한 줄 읽기
				String[] split = in.readLine().split(" ");
				
				// 하나의 행을 data 배열에 저장
				//data[i] = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
				for (int j = 0; j < 100; j++) {
					data[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			/**
    		 * 2. 알고리즘 풀기
    		 */
			// 현재 위치
			int curX = -1;
			int curY = -1;
			
			// 시작 위치 찾기 (도착 지점 2에서 출발)
			for (int x = 0; x < 100; x++) {
				if (data[99][x] == 2) {
					curX = x;
					curY = 99;
					break;
				}
			}
			
			// 좌, 우, 상 순서로 탐색
			int[] dx = { -1, 1, 0 };
			int[] dy = { 0, 0, -1 };
			
			while (curY != 0) {  // 배열의 첫 행에 도착하기 전까지 반복
				
				// 현재위치 curX, curY 에서 3방향 탐색
				for (int i = 0; i < 3; i++) {
					// 3방향 중 한 방향 체크
					int testX = curX + dx[i];
					int testY = curY + dy[i];
					
					// 경계 체크
					if ((0 <= testX && testX < 100) &&
							(0 <= testY && testY < 100)) {
						// 이동 하려는 곳의 값을 가져옴
						int result = data[testY][testX];
						
						// 이동 하려는 곳의 값이 1이면 이동
						if (result == 1) {
							curX = testX;
							curY = testY;
							data[testY][testX] = 8;  // 이동한 곳은 8로 표기
							break;
						}
					}	
				}
			}
			
			/**
			 * 3. 정답 출력
			 */
			sb.append(curX);
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
