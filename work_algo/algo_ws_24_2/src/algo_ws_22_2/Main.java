package algo_ws_22_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

// 2: virus
// 1: wall
// 0: blank

// "완탐"
// 바이러스 사방으로 퍼짐 
// 벽 꼭 3개 세우기 
// 안전지대가 최대인 모양 : 남아있는 0의 개수 

public class Main {

	private static final int BLANK = 0;
	private static final int WALL = 1;
	private static final int VIRUS = 2;

	private static int[] dx = { 1, -1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static int N;
	private static int M;

	private static int[][] map;

	private static Position[] wall;

	private static List<Position> viruses = new ArrayList<>();
	private static List<Position> positions = new ArrayList<>();

	private static int answer = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		map = new int[N][M];
		wall = new Position[3];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				if (map[i][j] == BLANK) {
					positions.add(new Position(i, j));
				} else if (map[i][j] == VIRUS) {
					viruses.add(new Position(i, j));
				}
			}
		}

		start(0, 0);

		System.out.println(answer);

	}

	private static void spread(int[][] map) {
		Queue<Position> virus = new ArrayDeque<>();
		for (int i = 0; i < viruses.size(); i++) {
			virus.offer(viruses.get(i));
		}

		// 4방으로 퍼트려짐
		while (!virus.isEmpty()) {

			Position cur = virus.poll();

			for (int d = 0; d < 4; d++) {
				int testX = cur.x + dx[d];
				int testY = cur.y + dy[d];

				if (testX >= 0 && testX < N && testY >= 0 && testY < M && map[testX][testY] == BLANK) {
					map[testX][testY] = VIRUS;
					virus.offer(new Position(testX, testY));
				}
			}

		}

	}

	private static void copy(int[][] map, int[][] newMap) {
		for (int i = 0; i < N; i++) {
			newMap[i] = map[i].clone();
		}
		for (int i = 0; i < 3; i++) {
			newMap[wall[i].x][wall[i].y] = WALL;
		}
	}

	private static int count(int[][] map) {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == BLANK) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void start(int cnt, int start) {

		// 벽 3개 세우기 => 조합
		if (cnt == 3) {
			int[][] newMap = new int[N][M];

			copy(map, newMap);


			// 바이러스 퍼트리기
			spread(newMap);


			// 0 카운트
			answer = Math.max(answer, count(newMap));

			return;
		}

		for (int i = start; i < positions.size(); i++) {
			wall[cnt] = positions.get(i);

			start(cnt + 1, i + 1);
		}

	}

	private static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}