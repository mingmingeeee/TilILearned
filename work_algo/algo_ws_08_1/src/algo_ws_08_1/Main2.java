package algo_ws_08_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main2 {

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

		// endX - startX : 세로
		// endY - startY : 가로
		int rectCount = Math.min(N, M);

		for (int k = 0; k < R; k++) {
			for (int j = 0; j < rectCount / 2; j++) {
				int startX = j;
				int startY = j;
				int tmp = data[startX][startY];

				int endX = N - 1 - j;
				int endY = M - 1 - j;

				// 윗변: 오른쪽에서 왼쪽 이동
				for (int i = startY; i < endY; i++) {
					data[startX][i] = data[startX][i + 1];
				}

				// 우변: 아래에서 위로 이동
				for (int i = startX; i < endX; i++) {
					data[i][endY] = data[i + 1][endY];
				}

				// 아랫변: 왼쪽에서 오른쪽 이동
				for (int i = endY; i > startY; i--) {
					data[endX][i] = data[endX][i - 1];
				}

				// 좌변: 위에서 아래 이동
				for (int i = endX; i > startX; i--) {
					data[i][startY] = data[i - 1][startY];
				}

				// 처음 빼낸 값 다시 넣기
				data[startX + 1][startY] = tmp;
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
