package algo_ws_08_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int R = Integer.parseInt(split[2]);

		int[][] data = new int[N][M];
		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				data[i][j] = Integer.parseInt(split[j]);
			}
		}

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		int x = 0, y = 0;

		int group = 0;

		if (N > M)
			group = M / 2;
		else
			group = N / 2;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < group; j++) {
				x = j;
				y = j;
				int dir = 0;
				int tmp1 = -1;
				int tmp2 = data[x][y];

				while (true) {
					
					int testX = x + dx[dir];
					int testY = y + dy[dir];
					if (testX >= j && testX < N - j && testY >= j && testY < M - j) {
						tmp1 = data[testX][testY];
						data[testX][testY] = tmp2;
						tmp2 = tmp1;
						x = testX;
						y = testY;
					} else {
						dir = (dir + 1) % 4;
						if(dir == 0) {
							break;
						}
					}
				}
			}
		}


		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(data[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}

/*
 * 
 * 
4 4 1 
1 2 3 4 
5 6 7 8 
9 8 7 6 
5 4 3 2
 */
