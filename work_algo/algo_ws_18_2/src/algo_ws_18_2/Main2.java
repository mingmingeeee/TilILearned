package algo_ws_18_2;

import java.io.*;
import java.util.*;

public class Main2 {

	// 우, 하, 좌, 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static int N; // 주어진 공간의 한 변의 길이
	private static int[][] spaces; // 문제에서 주어진 공간
	private static int sharkSize; // 아기 상어 크기
	private static int eat; // 크기 증가 전까지 먹은 물고기 수

	private static class Shark implements Comparable<Shark> {

		public int x; // 행
		public int y; // 열
		public int depth; // 이동거리

		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Shark o) {
			// 1. 가장 위에 있는 물고기
			int compare = Integer.compare(this.x, o.x);

			// 만약 1번과 같은 물고기가 많다면
			if (compare == 0) {
				// 2. 가장 왼쪽에 있는 물고기
				compare = Integer.compare(this.y, o.y);
			}
			return compare;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		spaces = new int[N][N];
		sharkSize = 2; // 문제에서 주어진 초기값 2
		eat = 0;
		int seconds = 0; // 물고기를 잡아먹을 수 있는 시간 (정답)

		// 아기 상어의 현재 위치를 저장하는 변수
		int curSharkX = -1;
		int curSharkY = -1;

		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				spaces[i][j] = Integer.parseInt(split[j]);

				switch (spaces[i][j]) {

				case 9: // 아기 상어
					curSharkX = i;
					curSharkY = j;
					spaces[i][j] = 0; // 탐색에 방해되지 않게 빈 칸으로 변경

				}
			}
		}

		while (true) {
			// 아기 상어와 가장 가까운 물고기 찾기
			Shark shark = bfs(curSharkX, curSharkY);
			
			// 물고기를 먹을 수 있는 아기 상어가 있다면
			if(shark != null) {
				
				// 시간 누적
				seconds += shark.depth;
				
				// 거리가 가까운 물고기가 많다면, 가장 위에, 가장 왼쪽에 있는 물고기를 먹는다.
				// 물고기를 먹으면, 그 칸은 빈 칸이 된다.
				eat++;
				spaces[shark.x][shark.y] = 0;
				
				// 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가
				if(sharkSize == eat) {
					sharkSize++;
					eat = 0;
				}
				
				curSharkX = shark.x;
				curSharkY = shark.y;
				
				
			} else {
				break; // 엄마 상어에게 요청 
			}
			
		}

	}

	// x, y: 현재 출발할 아기 상어의 위치
	private static Shark bfs(int x, int y) {

		Queue<Shark> queue = new ArrayDeque<>(); // 아기상어 BFS를 위한 큐
		boolean[][] isVisited = new boolean[N][N]; // 방문 체크

		int depth = 0; // BFS 깊이

		// 출발 위치부터 탐색을 위해 큐에 넣어 예약
		queue.offer(new Shark(x, y));
		isVisited[x][y] = true;

		while (!queue.isEmpty()) {

			// 깊이 증가 (시간 증가)
			depth++;

			// 큐 크기 확인 (동일 너비 대상 개수)
			int size = queue.size();

			// 아래 while문에서 발견된 물고기들은 모두 같은 거리의 물고기들이다.
			// 물고기를 먹을 수 있는 아기 상어를 리스트에 담는다.
			List<Shark> candidate = new ArrayList<>();

			while (--size >= 0) {
				// 현재 아기 상어 꺼내기
				Shark curShark = queue.poll();

				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int testX = curShark.x + dx[i];
					int testY = curShark.y + dy[i];

					// 경계 안이고 방문하지 않은 곳이면
					if ((0 <= testX && testX < N) && (0 <= testY && testY < N) && !isVisited[testX][testY]) {

						// 방문 처리
						isVisited[testX][testY] = true;

						// 물고기를 발견하면
						if (1 <= spaces[testX][testY] && spaces[testX][testY] <= 6) {

							// 아기 상어 자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없음
							if (sharkSize < spaces[testX][testY]) {
								continue;
							}

							// 크기가 같은 물고기이면 먹을 수 없지만, 지나갈 수 있음
							else if (sharkSize == spaces[testX][testY]) {

								// 다음 칸 이동
								queue.offer(new Shark(testX, testY));
							}

							// 크기가 작은 물고기는 먹을 수 있음
							else if (sharkSize > spaces[testX][testY]) {

								// 물고기를 먹을 수 있는 아기 상어 등록 (탐색 종료)
								candidate.add(new Shark(testX, testY));
							}

						}

					}
					// 빈 칸이면
					else if (spaces[testX][testY] == 0) {

						// 다음 칸 이동
						queue.offer(new Shark(testX, testY));
					}
				}
			}

			// 물고기를 먹을 수 있는 아기 상어가 있다면
			if (!candidate.isEmpty()) {

				// 거리가 가까운 물고기가 많다면, 가장 위에, 가장 왼쪽에 있는 물고기를 먹는다.
				Collections.sort(candidate); // 기준에 따라 정렬
				Shark shark = candidate.get(0); // 물고기를 먹을 수 있는 아기 상어 선택
				shark.depth = depth; // 이동 거리 저장

				return shark;
			}
		}

		// 물고기를 먹지 못했다면 엄마 상어에게 요청
		return null;

	}

}
