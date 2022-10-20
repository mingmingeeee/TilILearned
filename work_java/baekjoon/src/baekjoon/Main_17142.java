package baekjoon;

import java.io.*;
import java.util.*;

// 바이러스: 활성, 비활성
// 가장 처음: 비활성상태
// 활성 상태: 상하좌우 
// 바어리스 M개를 활성 상태로 변경하려고 함
// 활성 바이러스가 비활성 바이러스 있는 칸으로 가면 => (비활성 => 활성)

// 출력: 모든 빈 칸에 바이러스 퍼뜨리는 최소 시간

public class Main_17142 {

	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static final int BLANK = 0;
	private static final int WALL = 1;
	private static final int VIRUS_DEACTIVE = 2;
	private static final int VIRUS_ACTIVE = 3;

	private static int N;
	private static int M;
	private static int answer = Integer.MAX_VALUE;

	private static int[][] map;
	private static List<Position> viruses = new ArrayList<>();
	private static Position[] virus;

	private static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 연구소 크기 N
		M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스의 개수 M

		map = new int[N][N];
		virus = new Position[M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == VIRUS_DEACTIVE) {
					viruses.add(new Position(i, j));
				}
			}
		}

		// virusesCM => 바이러스 놓을 수 있는 자리에서 M개 골라서(comb) 퍼트리기 (bfs)
		// 2: 바이러스 비활성화
		// bfs로 퍼트릴 때의 기저 조건: 그냥 queue에 있는 거 다 빠질 때
		// 체크는 check method만들어서 하기

		comb(0, 0);
		
		if(answer == Integer.MAX_VALUE)
			answer = -1;
		
		sb.append(answer);
		System.out.println(sb);

	}

	private static void copy(int[][] copyMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < M; i++) {
			int x = virus[i].x;
			int y = virus[i].y;
			copyMap[x][y] = VIRUS_ACTIVE;
		}
	}

	private static void comb(int cnt, int start) {
		if (cnt == M) {
			int[][] copyMap = new int[N][N];
			copy(copyMap);
			
			
			int result = bfs(copyMap);
			if(result != -1)
				answer = Math.min(answer, result);

			return;
		}

		for (int i = start; i < viruses.size(); i++) {
			virus[cnt] = viruses.get(i);
			comb(cnt + 1, i + 1);
		}
	}

	private static int bfs(int[][] copyMap) {
		Queue<Position> queue = new ArrayDeque<>();

		for (int i = 0; i < M; i++) {
			queue.offer(virus[i]);
		}

		int depth = 0;
		while (!queue.isEmpty()) {

			int size = queue.size();

			if(check(copyMap))
				return depth;

			for (int i = 0; i < size; i++) {
				Position cur = queue.poll();

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX >= 0 && testY >= 0 && testX < N && testY < N) {
						if (copyMap[testX][testY] == VIRUS_DEACTIVE || copyMap[testX][testY] == BLANK) {
							copyMap[testX][testY] = VIRUS_ACTIVE;
							queue.offer(new Position(testX, testY));
						} 
					}
				}
			}

			depth++;

		}
		
		if(check(copyMap))
			return depth;
		else
			return -1;

	}

	private static boolean check(int[][] copyMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copyMap[i][j] == BLANK)
					return false;
			}
		}
		return true;
	}

}
