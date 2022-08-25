package algo_ws_18_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

import org.xml.sax.InputSource;

public class Main2 {

	// 우, 하, 좌, 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static final int RAW = 0; // 익지 않은 토마토
	private static final int RIPE = 1; // 익은 토마토
	private static final int BLANK = -1; // 빈 칸

	private static int N; // 가로 칸 수
	private static int M; // 세로 칸 수
	private static int[][] tomatoes; // 토마토들

	private static Queue<Tomato> queue; // 토마토 BFS를 위한 큐

	private static class Tomato {
		public int x; // 행
		public int y; // 열

		public Tomato(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		M = Integer.parseInt(split[0]);
		N = Integer.parseInt(split[1]);

		tomatoes = new int[N][M];
		queue = new ArrayDeque<>();

		int blank = 0; // 빈 칸의 수
		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				tomatoes[i][j] = Integer.parseInt(split[j]);

				switch (tomatoes[i][j]) {
				case RIPE: // 익은 토마토
					queue.offer(new Tomato(i, j)); // -> 이 위치를 기준으로 사방의 토마토들이 익게 만드는... 계속 퍼져나가면서!!!
					break;

				case BLANK: // 토마토가 들어있지 않은 칸
					blank++;
					break;

				}
			}
		}

		int minDays = bfs(N * M - blank);
		// N * M - blank: 기저 조건에 쓰임
		// 모든 토마토가 익었을 때 BFS 종료될 떄 쓰이는
		// 전체 영역 - 토마토 들어있지 않은 칸 -> 토마토가 안 익은 칸

		sb.append(minDays);
		System.out.println(sb);
	}

	// total: 모두 익어야 하는 토마토 수
	private static int bfs(int total) {

		int depth = 0; // BFS 깊이

		int cnt = 0;
		while (!queue.isEmpty()) {
			
			// 깊이 증가
			depth++;

			// 모두 익었는지 확인 -> 가져오기 
			if(cnt == total) {
				return depth - 1; // 들어가기 전에 depth 올려놨기 때문
			}

			// 큐 크기 확인(동일 너비 대상 개수)
			int size = queue.size();
			System.out.println(size);
			while (--size > 0) {

				Tomato current = queue.poll();

				// 4방향 탐색
				for (int i = 0; i < 4; i++) {
					int testX = current.x + dx[i];
					int testY = current.y + dy[i];

					// 경계 체크
					if ((0 <= testX && testX < N) && (0 <= testY && testY < M)) {

						// 토마토가 익지 않았다면
						if (tomatoes[testX][testY] == RAW) {
							tomatoes[testX][testY] = RIPE; // 토마토를 익히고
							queue.offer(new Tomato(testX, testY)); // 다음 칸 이동
						}

					}
				}

			}
		}

		return -1; // 모두 익지 못하는 상황
	}

}
