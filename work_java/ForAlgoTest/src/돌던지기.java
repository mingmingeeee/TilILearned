
// https://www.acmicpc.net/problem/3025

import java.io.*;
import java.util.*;

public class 돌던지기 {

	static final char BLANK = '.';
	static final char WALL = 'X';
	static final char STONE = 'O';

	static List<List<Position>> stones = new ArrayList<>();

	static char[][] map;

	static int R;
	static int C;

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);

		map = new char[R + 1][C + 1];

		// 초기 상태 돌
		for (int i = 1; i <= R; i++) {
			String s = in.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = s.charAt(j - 1);
			}
		}

		int N = Integer.parseInt(in.readLine());

		for (int i = 0; i < N + 1; i++) { // 돌 경로 저장
			stones.add(new ArrayList<Position>());
		}

		for (int i = 0; i < N; i++) {
			int idx = Integer.parseInt(in.readLine()); // 돌 놓을 열

			int x, y;

			int size = stones.get(idx).size();

			// 지나온 경로가 존재할 때 돌인 부분 빼고 다시 지나갈 수 있는 곳부터 탐색
			while (size > 0 && map[stones.get(idx).get(size - 1).x][stones.get(idx).get(size - 1).y] == STONE) {
				stones.get(idx).remove(size - 1);
				size--;
			}

			if (size > 0) {
				x = stones.get(idx).get(size - 1).x;
				y = stones.get(idx).get(size - 1).y;
				move(x, y, idx);
			} else {
				move(1, idx, idx);
			}

		}

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

	static void move(int x, int y, int idx) {

		while (true) {

			stones.get(idx).add(new Position(x, y)); // 경로 저장

			if (x == R || (x + 1 <= R) && map[x + 1][y] == WALL) { // 마지막 행이거나 밑이 벽이라면
				map[x][y] = 'O'; // 돌 놓음
				return;
			}

			else if (x + 1 <= R && map[x + 1][y] == STONE) { // 밑에가 돌이라면
				if (y - 1 >= 0 && map[x][y - 1] == BLANK && map[x + 1][y - 1] == BLANK) { // 왼쪽, 왼쪽 아래 빈칸
					y--; // 이동
				} else if (y + 1 <= C && map[x][y + 1] == BLANK && map[x + 1][y + 1] == BLANK) { // 오른쪽, 오른쪽 아래 빈칸
					y++; // 이동
				} else { // 둘 다 아니면
					map[x][y] = 'O'; // 돌 놓음
					return;
				}
			}

			x++; // 밑으로 계쏙 이동

		}

	}

}

class Position {
	int x;
	int y;

	Position(int x, int y) {

		this.x = x;
		this.y = y;

	}
}

/*
import java.io.*;
import java.util.*;

public class Main {

	static final char BLANK = '.';
	static final char WALL = 'X';
	static final char STONE = 'O';

	static int R;
	static int C;
	static char[][] map;
	static Stack<P>[] stones;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().toCharArray();
		}

		int N = Integer.parseInt(in.readLine());

		stones = new Stack[C];
		for (int i = 0; i < C; i++)
			stones[i] = new Stack<P>();

		for (int i = 0; i < N; i++) {

			int column = Integer.parseInt(in.readLine()) - 1;

			while (!stones[column].isEmpty() && map[stones[column].peek().x][stones[column].peek().y] == STONE) {
				stones[column].pop();
			}

			if (stones[column].isEmpty()) { // 첫 경로라면
				move(0, column, column);
			} else {
				int x = stones[column].peek().x;
				int y = stones[column].peek().y;
				move(x, y, column);
			}

		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		System.out.println(sb);

	}

	private static void move(int x, int y, int column) {

		while (true) {

			stones[column].push(new P(x, y));

			if (x == R - 1 || isRange(x + 1, y) && map[x + 1][y] == WALL) {
				map[x][y] = STONE;
				return;
			}

			else if (isRange(x + 1, y) && map[x + 1][y] == STONE) {
				if (isRange(x, y - 1) && map[x][y - 1] == BLANK && map[x + 1][y - 1] == BLANK) {
					y--;
				} else if (isRange(x, y + 1) && map[x][y + 1] == BLANK && map[x + 1][y + 1] == BLANK) {
					y++;
				} else {
					map[x][y] = STONE;
					return;
				}
			}

			x++;

		}

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C) {
			return true;
		}
		return false;
	}

}

class P {
	int x;
	int y;

	P(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

 * */