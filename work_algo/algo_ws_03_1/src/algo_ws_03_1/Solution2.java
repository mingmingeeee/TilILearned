package algo_ws_03_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution2 {
	
	// 지형 정보
	private static final char GROUND = '.';
	private static final char BRICK = '*';
	private static final char STEEL = '#';
	private static final char WATER = '-';
	
	// 상, 우, 하, 좌 (시계방향)
	private static final char[] TANK = { '^', '>', 'v', '<' };
	private static final int[] dx = { -1, 0, 1, 0 };
	private static final int[] dy = { 0, 1, 0, -1 };

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
			
			// 높이 H, 너비 W
			String[] split = in.readLine().split(" ");
			int H = Integer.parseInt(split[0]);
			int W = Integer.parseInt(split[1]);
			
			// 게임 맵
			char[][] map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String newLine = in.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = newLine.charAt(j);
				}
			}
			
			// 사용자가 넣을 입력의 개수 N
			int N = Integer.parseInt(in.readLine());
			
			// 사용자 입력
			char[] userInput = new char[N];
			String newLine = in.readLine();
			for (int i = 0; i < N; i++) {
				userInput[i] = newLine.charAt(i);
			}
			
			/**
			 * 2. 알고리즘 풀기
			 */
			// 전차 위치 찾기 (-1은 값이 초기화 되지 않았음을 의미)
			int curX = -1;  // 전차의 x좌표
			int curY = -1;  // 전차의 y좌표
			int dir = -1;   // 전차의 방향
			
			// 1. 전차 위치 찾기
			loop: for (int x = 0; x < H; x++) {
				for (int y = 0; y < W; y++) {
					// 전차의 위치와 방향 확인
					for (int i = 0; i < 4; i++) {
						if (map[x][y] == TANK[i]) {
							curX = x;
							curY = y;
							dir = i;
							break loop;
						}
					}
				}
			}
			
			// 2. 사용자 입력에 따라 전차 조작
			for (int i = 0; i < N; i++) {
				
				// 사용자가 슈팅 버튼을 눌렀을 때
				if (userInput[i] == 'S') {
					
					// 포탄이 이동할 좌표 구하기
					int testX = curX + dx[dir];
					int testY = curY + dy[dir];
					
					loop: while (true) {
						// 경계 체크
						if ((0 <= testX && testX < H) && (0 <= testY && testY < W)) {
							switch (map[testX][testY]) {
							case GROUND:
							case WATER: // 평지 or 물: 전진
								testX = testX + dx[dir];
								testY = testY + dy[dir];
								break;
								
							case BRICK:
								map[testX][testY] = GROUND;
								break loop;
								
							case STEEL:
								break loop;
							}
						}
						else {
							// 포탄이 경계 밖으로 나가면 포탄 이동 종료
							break loop;
						}
					}
				}
				// 사용자가 전차를 이동 시켰을 때
				else {
					switch (userInput[i]) {
					case 'U': dir = 0; break;  // 전차 방향 상
					case 'R': dir = 1; break;  // 전차 방향 우
					case 'D': dir = 2; break;  // 전차 방향 하
					case 'L': dir = 3; break;  // 전차 방향 좌
					}
					
					// 전차가 바라보는 방향 변경
					map[curX][curY] = TANK[dir];
					
					// 이동 하려는 좌표 구하기
					int testX = curX + dx[dir];
					int testY = curY + dy[dir];
					
					// 경계 체크
					if ((0 <= testX && testX < H) && (0 <= testY && testY < W)) {
						// 이동하려는 칸이 평지라면 이동
						if (map[testX][testY] == GROUND) {
							map[curX][curY] = GROUND;  // 전차가 있던 자리는 평지로 이동
							curX = testX;
							curY = testY;
							map[curX][curY] = TANK[dir];  // 이동 후 자리는 전차의 방향 표시
						}
					}
				}
			}

			/**
			 * 3. 정답 출력
			 */
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
}
