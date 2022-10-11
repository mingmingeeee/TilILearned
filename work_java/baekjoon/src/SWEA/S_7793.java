package SWEA;

import java.io.*;
import java.util.*;

public class S_7793 {

	// 악마의 손아귀
	// 매 초마다 상하좌우 인접한 영역들 부식시키며 확장
	// "지은"이라는 이름가진 여신 있는 공간: 이 스킬로부터 자유로울 수 있음
	// 수연이는 여신님이 있는 곳으로 가야함
	// N행 M열
	// 수연 이동: 동, 서, 남, 북 이동 가능
	// 돌 이동 X 악마의 손아귀도 X
	// 안전 지역에 도달하는 최소 시간 구하기

	// 수연 위치: S
	// 여신 공간: D
	// 돌의 위치: X
	// 악마: *
	private static final char SOO = 'S';
	private static final char GOD = 'D';
	private static final char ROCK = 'X';
	private static final char DEMON = '*';
	private static final char BLANK = '.';

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static int N;
	private static int M;
	private static char[][] map;

	private static int answer;

	private static Queue<Position> soo;
	private static Queue<Position> demon;

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

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			String[] split = in.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			M = Integer.parseInt(split[1]);

			map = new char[N][M];

			soo = new ArrayDeque<>();
			demon = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {
				split = in.readLine().split("");
				for (int j = 0; j < M; j++) {
					map[i][j] = split[j].charAt(0);
					if (map[i][j] == SOO) {
						soo.offer(new Position(i, j));
					}
					if (map[i][j] == DEMON) {
						demon.offer(new Position(i, j));
					}
				}
			}

			answer = bfs();
			if (answer == -1)
				sb.append("GAME OVER").append("\n");
			else
				sb.append(answer).append("\n");

		}

		System.out.println(sb);

	}

	private static int bfs() {

		int depth = 0;

		while (!soo.isEmpty()) {

			int size = demon.size();
			for (int i = 0; i < size; i++) {
				Position cur = demon.poll();

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX >= 0 && testX < N && testY >= 0 && testY < M
							&& (map[testX][testY] == BLANK || map[testX][testY] == SOO)) {
						demon.offer(new Position(testX, testY));
						map[testX][testY] = DEMON;
					}
				}
			}

			size = soo.size();
			for (int i = 0; i < size; i++) {
				Position cur = soo.poll();

				for (int d = 0; d < 4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];

					if (testX >= 0 && testX < N && testY >= 0 && testY < M) {
						if (map[testX][testY] == BLANK) {
							soo.offer(new Position(testX, testY));
							map[testX][testY] = SOO;
						} else if (map[testX][testY] == GOD) {
							return ++depth;
						}
					}
				}
			}

			depth++;

		}

		return -1;
	}

}
