package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_2178 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N;
	static int M;
	static int[][] data;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] strings = in.readLine().split(" ");
		N = Integer.parseInt(strings[0]);
		M = Integer.parseInt(strings[1]);

		data = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = in.readLine();
			for (int j = 0; j < M; j++) {
				data[i][j] = s.charAt(j) - '0';
			}
		}

		bfs(0, 0, 1);
	}

	static void bfs(int x, int y, int count) {

		Queue<int[]> q = new ArrayDeque<>();
		int[] cur = { x, y };
		q.add(cur);
		data[cur[0]][cur[1]] = 2;

		while (!q.isEmpty()) {
			int c = q.size();
			System.out.println(c);
			for (int j = 0; j < c; j++) { // depth
				cur = q.poll();
				if (cur[0] == N - 1 && cur[1] == M - 1) {
					System.out.println(count);
					return;
				}
				for (int i = 0; i < 4; i++) {
					int testX = cur[0] + dx[i];
					int testY = cur[1] + dy[i];

					if (testX >= 0 && testX < N && testY >= 0 && testY < M && data[testX][testY] == 1) {
						int[] cur2 = { testX, testY };
						q.offer(cur2);
						data[testX][testY] = 2;
					}
				}
			}
			count++;
		}

	}

//	static void dfs(int x, int y, int count) {
//		if(x==N-1 && y == M-1) {
//			if(min > count) 
//				min = count;
//			return;
//		}
//		count++;
//		for(int i=0; i<4; i++) {
//			int testX = x + dx[i];
//			int testY = y + dy[i];
//			
//			if(testX>=0 && testX <N && testY >=0 && testY < M
//					&& data[testX][testY] == 1) {
//				data[testX][testY] = 2;
//				dfs(testX, testY, count);
//				data[testX][testY] = 1;
//			} 
//		}
//		
//	}

}
