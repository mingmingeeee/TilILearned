package baekjoon;

import java.io.*;
import java.util.*;

public class B_1074 {

	static int[] dx = { 0, 0, 1, 1 };
	static int[] dy = { 0, 1, 0, 1 };

	static int r;
	static int c;
	static int count;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		r = Integer.parseInt(split[1]);
		c = Integer.parseInt(split[2]);

		co_de(N, 0, 0);

	}

	static void co_de(int N, int x, int y) {

		if (N == 2) {
			for (int i = 0; i < 4; i++) {
				int x1 = x + dx[i];
				int y1 = y + dy[i];
				if (x1 == c && y1 == r) {
					System.out.println(count);
					System.exit(0);
				}
				count++;
			}
			return;
		}

		co_de(N / 2, x, y);
		co_de(N / 2, x, y + N / 2);
		co_de(N / 2, x + N / 2, y);
		co_de(N / 2, x + N / 2, y + N / 2);

	}

}
