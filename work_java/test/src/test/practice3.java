package test;

import java.io.*;
import java.util.*;

public class practice3 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			int N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");

			int[] blocks = new int[N];

			int max_up = 0;
			int max_down = 0;
			for (int i = 0; i < N; i++) {
				blocks[i] = Integer.parseInt(st.nextToken());

				if (i > 0) {
					if (blocks[i] > blocks[i - 1]) {
						max_up = Math.max(max_up, blocks[i] - blocks[i - 1]);
					} else {
						max_down = Math.max(max_down, blocks[i - 1] - blocks[i]);
					}
				}
			}
			
			sb.append(max_up + " " + max_down).append("\n");
		}
		
		System.out.println(sb);

	}

}
