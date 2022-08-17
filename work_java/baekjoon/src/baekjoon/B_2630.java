package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_2630 {

	static int[][] map;
	static int white;
	static int green;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}
		Divide_and_conquer(map, 0, 0, map.length);
		
		sb.append(white+"\n"+green);
		System.out.println(sb);

	}

	static void Divide_and_conquer(int[][] map, int x, int y, int size) {

		int count = 0;
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] == 1)
					count++;
			}
		}

		if (count == size*size)
			green++;
		else if (count == 0)
			white++;
		else {
			int half = size / 2;
			Divide_and_conquer(map, x, y, half);
			Divide_and_conquer(map, x + half, y, half);
			Divide_and_conquer(map, x, y + half, half);
			Divide_and_conquer(map, x + half, y + half, half);
		}

	}

}
