package baekjoon;

import java.io.*;
import java.util.*;

public class Main {

	// 1. 현재 위치 청소
	// 2. 현재 위치에서 왼쪽부터 차례대로 탐색 진행
	// 2-1. 왼쪽방향 -> 아직 청소 X : 그 방향으로 회전한 다음 한 칸 전진, 1번부터 진행
	// 2-2. 왼쪽 방향 청소할 공간X : 그 방향으로 회전, 2번으로 돌아감
	// 2-3. 네 방향 모두 청소 이미 되어있거나 벽: 바라보는 방향 유지한 채로 후진, 2번
	// 2-4. 네 방향 모두 청소 이미 되어있거나 벽, 뒤쪽 방향도 벽이라 후진X: 작동 멈춤

	// 이미 청소되어 있는 칸 또 청소 X, 벽 통과 X

	static final int WALL = 1;
	static final int BLANK = 0;

	static int[][] map;
	static boolean[][] cleaned;

	// d 방향
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 크기 N, M
		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		map = new int[N][M];
		cleaned = new boolean[N][M];

		// 로봇 청소기가 있는 칸의 좌표, 바라보고 있는 방향 d
		// d: 0 북
		// d: 1 동
		// d: 2 남
		// d: 3 서
		split = in.readLine().split(" ");
		int r = Integer.parseInt(split[0]);
		int c = Integer.parseInt(split[1]);
		int d = Integer.parseInt(split[2]);

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		bfs(r, c, d);
		
		sb.append(answer);

		System.out.println(sb);
	}
	

	static int answer;
	
	static int count;

	private static void bfs(int x, int y, int d) {

		Queue<Po> q = new ArrayDeque<>();
		q.offer(new Po(x, y, d));
		cleaned[x][y] = true;
		answer++;

		while (!q.isEmpty()) {

			Po cur = q.poll();

			int rotation = (cur.d + 3) % 4;

			int testX = cur.x + dx[rotation];
			int testY = cur.y + dy[rotation];
			

			
			if (isRange(testX, testY)) {
				if (!cleaned[testX][testY] && map[testX][testY] == 0) { // 왼쪽 방향 회전 -> 청소 가능?
					q.offer(new Po(testX, testY, rotation)); // 전진
					cleaned[testX][testY] = true;
					answer++;
					count = 0;
				} else { // 청소 불가능?
					if(count == 4) { // 네 방향 이미 청소 ㅇㅇ or 벽 
						testX = cur.x - dx[cur.d];
						testY = cur.y - dy[cur.d];
						if(isRange(testX, testY) && map[testX][testY] == 0) { // 뒤에 벽 X -> 후진
							q.offer(new Po(testX, testY, cur.d));
							count = 0;
						}
						else // 아니면 
							return; // 멈춤 
						continue;
					}
					q.offer(new Po(cur.x, cur.y, rotation)); // 방향만 왼쪽으로 회전
					count++;
				}
			}

		}

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < map.length && 0 <= y && y < map[0].length) {
			return true;
		}
		return false;
	}

}

class Po {
	int x;
	int y;
	int d;

	Po(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}
