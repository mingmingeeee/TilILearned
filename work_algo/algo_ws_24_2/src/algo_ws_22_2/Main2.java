package algo_ws_22_2;

import java.io.*;
import java.util.*;

public class Main2 {

	private static final int BLANK = 0; // 빈 칸
	private static final int BLOCK = 1; // 벽
	private static final int VIRUS = 2; // 바이러스

	// 우, 하, 좌, 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static int N; // 행
	private static int M; // 열
	private static int[][] map; // 2차원 배열
	private static int max; // 답이 되는 안전지대 갯수 최대값

	private static class Virus {
		public int x;
		public int y;

		public Virus(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		max = Integer.MIN_VALUE;

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		// 1. 벽 3개 세우기
		dfs(0);
		
		System.out.println(max);

	}

	private static void dfs(int cnt) {

		// 기저 조건 (벽을 3개 세웠다면 바이러스 퍼뜨리기)
		if (cnt == 3) {
			int cntSafe = bfs();

			if (cntSafe > max) {
				max = cntSafe;
			}

			return;
		}

		// 유도 부분
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == BLANK) { // 방문하지 않았다면 (벽을 세우지 않았다면)
					map[i][j] = BLOCK; // 방문 처리 (벽 세우기)
					dfs(cnt + 1); // 다음 이동
					map[i][j] = BLANK; // 미방문 처리 (마지막에 세웠던 벽 제거)
				}
			}
		}

	}

	private static int bfs() {
		Queue<Virus> queue = new ArrayDeque<>();
		int[][] copy = new int[N][M];

		// map 원본 복사 (바이러스를 퍼뜨린 후 다시 원상 복구를 위해)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];

				// 바이러스면 큐에 넣기
				if (copy[i][j] == VIRUS) {
					queue.add(new Virus(i, j));
				}
			}
		}

		while (!queue.isEmpty()) {

			// 큐 크기 확인 (동일 너비 대상 개수)
			int size = queue.size();

			while (--size >= 0) {
				Virus curVirus = queue.poll();

				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int testX = curVirus.x + dx[i];
					int testY = curVirus.y + dy[i];

					// 경계 안이고 방문하지 않은 곳이면
					if ((0 <= testX && testX < N && 0 <= testY && testY < M)
							&& copy[testX][testY] == BLANK) {
						// 바이러스 퍼뜨리고
						copy[testX][testY] = VIRUS;
						
						// 다음 칸 이동
						queue.offer(new Virus(testX, testY));
					}
				}
			}

		}
		
		// 3. 안전 지대 개수 세기
		int cntSafe = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == BLANK) { 
					cntSafe++;
				}
			}
		}
		
		return cntSafe;
	}

}
