package practice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ArrayTest_30_1 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ArrayTest_30_input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());  // 테스트 케이스
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");

			// 여기서부터 알고리즘 작성
			
			int N = Integer.parseInt(in.readLine());  // 공간의 한 변 길이
			char[][] map = new char[N][N];  // 공간 정보를 담을 2차원 배열

			// 파일로부터 공간 정보를 불러와 2차원 배열에 담는다.
			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = split[j].charAt(0);
				}
			}

			int answer = 0;
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					char robot = map[x][y];

					int[] dx = {};  // 델타 x
					int[] dy = {};  // 델타 y

					if (robot == 'A') {  // 우
						dx = new int[] {0};
						dy = new int[] {1};
					}
					else if (robot == 'B') {  // 좌우
						dx = new int[] {0, 0};
						dy = new int[] {-1, 1};
					}
					else if (robot == 'C') {  // 좌우상하
						dx = new int[] {0, 0, -1, 1};
						dy = new int[] {-1, 1, 0, 0};
					}

					for (int i = 0; i < dx.length; i++) {
						int testX = x + dx[i];
						int testY = y + dy[i];

						// 이동 후 위치가 경계 안쪽이면 계속 반복실행
						while ((0 <= testX && testX < N)
								&& (0 <= testY && testY < N)) {

							if (map[testX][testY] == 'S') {
								answer++;
							}
							else {
								break;
							}

							testX = testX + dx[i];
							testY = testY + dy[i];
						}
					}
				}
			}

			System.out.println(answer);
		}
	}
}
