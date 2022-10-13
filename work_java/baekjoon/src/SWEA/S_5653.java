package SWEA;

import java.io.*;
import java.util.*;

public class S_5653 {

	private static final int DEAD = 0;
	private static final int DEACTIVE = 1;
	private static final int ACTIVE = 2;
	
	private static int MAX1, MAX2;

	private static final int[] dx = { -1, 1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static int[][] map;
	private static Queue<Cell> cells;
	private static Queue<Cell> spreads;
	private static int N;
	private static int M;

	// 상태: 비활성, 활성, 죽음 => 3차원?
	// 1. 비활성: X시간 동안 비활성
	// 2. 활성: X시간 동안 살아있을 수 있음
	// 3. 죽음: X시간 지나면 죽음(셀은 차지함)

	// 1. 활성화되면 첫 1시간 동안 상, 하, 좌, 우 네 방향으로 동시 번식
	// 2. 번식된 줄기 세포: 비활성 상태
	// 2-1. 번식 방향에 이미 줄기 세포 존재하는 경우? 번식 X
	// 2-2. 두 개 이상의 줄기 세포가 하나의 셀에 동시 번식하려고 하는 경우
	// 생명력 수치가 높은 줄기 세포가 해당 그리드 셀 혼자 차지

	// K시간 후 살아있는 줄기 세포(비활성 상태 + 활성 상태)

	private static class Cell implements Comparable<Cell> {
		int x;
		int y;
		int time;
		int life;
		int status;

		public Cell(int x, int y, int time, int life, int status) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
			this.life = life;
			this.status = status;
		}

		@Override
		public int compareTo(Cell o) {
			// TODO Auto-generated method stub
			return o.time - this.time;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			String[] split = in.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			M = Integer.parseInt(split[1]);
			int K = Integer.parseInt(split[2]);
			
			MAX1 = N + 300;
			MAX2 = M + 300;

			map = new int[MAX1][MAX2];

			cells = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {
				split = in.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					int x = MAX1 / 2 - N / 2 + i;
					int y = MAX2 / 2 - M / 2 + j;
					map[x][y] = Integer.parseInt(split[j]);
					if (map[x][y] > 0)
						cells.offer(new Cell(x, y, map[x][y], map[x][y], DEACTIVE));
				}
			}

			for (int i = 0; i <= K; i++) {
				if(i > 0)
					spread();
				spreads = new PriorityQueue<>();
				start(i);
//				System.out.println("======" + i + "=======");
//				for (int k = 0; k < MAX1; k++) {
//					for (int j = 0; j < MAX2; j++) {
//						System.out.print(map[k][j] + " ");
//					}
//					System.out.println();
//				}
			}

			sb.append(cells.size()).append("\n");
		}

		System.out.println(sb);

	}

	private static void spread() {
		// TODO Auto-generated method stub
		int size = spreads.size();

		for (int i = 0; i < size; i++) {
			Cell cur = spreads.poll();

			for (int d = 0; d < 4; d++) {
				int testX = cur.x + dx[d];
				int testY = cur.y + dy[d];

				if (testX >= 0 && testX < MAX1 && testY >= 0 && testY < MAX2 && map[testX][testY] == 0) {
					map[testX][testY] = cur.time;
					cells.offer(new Cell(testX, testY, cur.time, cur.time, DEACTIVE));
				}
			}
		}
	}

	private static void start(int k) {

		int size = cells.size();
		for (int i = 0; i < size; i++) {

			Cell cur = cells.poll();

			if (cur.life == 0 && cur.status == DEACTIVE) { // 활성화 됐을 때
				spreads.offer(new Cell(cur.x, cur.y, cur.time, cur.time, ACTIVE));
				cells.offer(new Cell(cur.x, cur.y, cur.time, cur.time - 1, ACTIVE));
			} else if (0 < cur.life && cur.status == ACTIVE) { // 활성화중인 상태
				cells.offer(new Cell(cur.x, cur.y, cur.time, cur.life - 1, ACTIVE));
			} else if (0 < cur.life && cur.status == DEACTIVE) { // 아직 비활성화
				cells.offer(new Cell(cur.x, cur.y, cur.time, cur.life - 1, DEACTIVE));
			}

		}


	}

}
