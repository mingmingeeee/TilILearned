
import java.io.*;
import java.util.*;

public class Main {

	static final char STONE = 'O';
	static final char WALL = 'X';
	static final char BLANK = '.';

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
		for (int i = 0; i < C; i++) {
			stones[i] = new Stack<>();
		}

		for (int i = 0; i < N; i++) {

			int column = Integer.parseInt(in.readLine()) - 1;

			while (!stones[column].isEmpty() && map[stones[column].peek().x][stones[column].peek().y] == STONE) {
				stones[column].pop();
			}

			if (stones[column].isEmpty()) {
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
				if (isRange(x, y - 1) && map[x][y - 1] == BLANK && isRange(x + 1, y - 1)
						&& map[x + 1][y - 1] == BLANK) {
					y--;
				} else if (isRange(x, y + 1) && map[x][y + 1] == BLANK && isRange(x + 1, y + 1)
						&& map[x + 1][y + 1] == BLANK) {
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