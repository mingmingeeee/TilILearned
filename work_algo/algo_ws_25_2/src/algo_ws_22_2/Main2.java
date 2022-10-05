package algo_ws_22_2;

import java.io.*;
import java.util.*;

public class Main2 {

	private static final char GROUND = '.';  // 빈 칸
	private static final char BLOCK = '#';  // 벽
	private static final char START = '0';  // 출발점
	private static final char EXIT = '1';  // 출구

	// 우, 하, 좌, 상
	private static final int[] dr = { 0, 1, 0, -1 };
	private static final int[] dc = { 1, 0, -1, 0 };

	private static int N;
	private static int M;
	private static char[][] maze;

	private static class Position {
		public int r, c, cnt, key;  // 행, 열, 이동횟수, 획득한 키 값 (비트마스크 이용)

		public Position(int r, int c, int cnt, int key) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.key = key;
		}
	}

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
		String[] split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		maze = new char[N][M];
		
		Position start = null;  // 민식이의 위치
		for (int i = 0; i < N; i++) {
			String readLine = in.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = readLine.charAt(j);
				if (maze[i][j] == START) {
					start = new Position(i, j, 0, 0);  // 민식이의 위치 저장
					maze[i][j] = GROUND;  // 민식이가 있던 위치는 빈 땅으로 변경
				}
			}
		}

		/**
		 * 2. 알고리즘 풀기
		 */
		int min = bfs(start);

		/**
		 * 3. 정답 출력
		 */
		sb.append(min);
		System.out.println(sb);
	}

	private static int bfs(Position start) {
		
		Queue<Position> queue = new ArrayDeque<>();
		
		// 열쇠가 없는 경우부터 열쇠를 모두 가지고 있는 경우에 대한 방문체크 배열 생성
		// 각각 inVisited 2차원 배열을 만들어 줌
		boolean[][][] isVisited = new boolean[N][M][64];
		
		// 방문 예약
		queue.offer(start);
		
		// 방문 표시
		isVisited[start.r][start.c][0] = true;
		
		while (!queue.isEmpty()) {
			
			// 큐 크기 확인 (동일 너비 대상 개수)
			int size = queue.size();
			
			while (--size >= 0) {
				Position curPos = queue.poll();
				int r = curPos.r;
				int c = curPos.c;
				int cnt = curPos.cnt;
				int key = curPos.key;
				
				// 기저조건 (종료조건: 출구 1에 도착)
				if (maze[r][c] == EXIT) {
					// BFS 이므로 이곳에 도달 했다면 이동 횟수의 최솟값이다.
					return cnt;
				}
				
				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int testR = r + dr[i];
					int testC = c + dc[i];
					
					// 경계 안이고 방문하지 않은 곳이면
					if ((0 <= testR && testR < N) && (0 <= testC && testC < M)
							&& maze[testR][testC] != BLOCK
							&& !isVisited[testR][testC][key]) {
						
						// 평지일 경우
						if (maze[testR][testC] == GROUND || maze[testR][testC] == EXIT) {
							
							// 방문 처리
							isVisited[testR][testC][key] = true;
							
							// 다음 칸 이동
							queue.offer(new Position(testR, testC, cnt + 1, key));
						}
						// 열쇠일 경우
						else if ('a' <= maze[testR][testC] && maze[testR][testC] <= 'f') {
							
							// 열쇠 획득
							int newKey = 1 << (maze[testR][testC] - 'a');  // 1부터 32까지 ('a' ~ 'f')
							newKey = newKey | key;  // 새로운 키 추가 (OR 연산)
							
							// 방문 처리
							if (!isVisited[testR][testC][newKey]) {
								isVisited[testR][testC][key] = true;
								isVisited[testR][testC][newKey] = true;
							}
							
							// 다음 칸 이동
							queue.offer(new Position(testR, testC, cnt + 1, newKey));
						}
						// 문일 경우
						else if ('A' <= maze[testR][testC] && maze[testR][testC] <= 'F') {
							
							int door = 1 << (maze[testR][testC] - 'A');
							if ((key & door) > 0) {  // AND 연산 결과 0보다 크다면 문을 열 수 있는 열쇠가 있다는 뜻
								
								// 방문 처리
								isVisited[testR][testC][key] = true;
								
								// 다음 칸 이동
								queue.offer(new Position(testR, testC, cnt + 1, key));
							}
						}
					}
				}
			}
		}
		
		// 더이상 갈 곳이 없다면 -1 리턴
		return -1;
	}

}



