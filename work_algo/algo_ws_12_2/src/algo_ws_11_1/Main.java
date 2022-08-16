package algo_ws_11_1;

import java.io.*;
import java.util.*;

public class Main {

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

		cut(0, 0, (int) Math.pow(2, N));

	}

	static void cut(int x, int y, int size) {

		if (size == 2) { // 지그재그 될 수 있는 마지막
			for (int i = 0; i < 4; i++) {
				int x1 = x + dx[i];
				int y1 = y + dy[i];

				if (x1 == r && y1 == c) {
					System.out.println(count);
					System.exit(0);
				}
				count++;
			}
			return;
		}

		if(x > r || x + size <r || y > c || y + size<c) {
			count += size*size;
			return;
		}

		int half = size / 2;
		cut(x, y, half);
		cut(x, y + half, half);
		cut(x + half, y, half);
		cut(x + half, y + half, half);

	}

}