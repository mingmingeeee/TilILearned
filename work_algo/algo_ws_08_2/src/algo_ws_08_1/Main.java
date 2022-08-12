package algo_ws_08_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int M;
	static int R;
	static int[][] data;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		R = Integer.parseInt(split[2]);

		data = new int[N][M];
		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				data[i][j] = Integer.parseInt(split[j]);
			}
		}

		split = in.readLine().split(" ");
		for (int i = 0; i < split.length; i++) {

			int mode = Integer.parseInt(split[i]);
			switch (mode) {

			case 1:
				UpDown();
				break;

			case 2:
				LeftRight();
				break;

			case 3:
				Right90();
				break;

			case 4:
				Left90();
				break;

			case 5:
				Rotate_group1();
				break;

			case 6:
				Rotate_group2();
				break;

			}

		}
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				sb.append(data[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void Rotate_group2() {
		int[][] n = new int[data.length][data[0].length];

		// 1->4
		for (int i = 0; i < data.length / 2; i++) {
			for (int j = 0; j < data[0].length / 2; j++) {
				n[i + data.length / 2][j] = data[i][j];
			}
		}

		// 2->1
		for (int i = 0; i < data.length / 2; i++) {
			for (int j = data[0].length / 2; j < data[0].length; j++) {
				n[i][j - data[0].length / 2] = data[i][j];
			}
		}

		// 3->2
		for (int i = data.length / 2; i < data.length; i++) {
			for (int j = data[0].length / 2; j < data[0].length; j++) {
				n[i - data.length / 2][j] = data[i][j];
			}
		}

		// 4->3
		for (int i = data.length / 2; i < data.length; i++) {
			for (int j = 0; j < data[0].length / 2; j++) {
				n[i][data[0].length / 2 + j] = data[i][j];
			}
		}

		data = n;

	}

	private static void Rotate_group1() {
		int[][] n = new int[data.length][data[0].length];

		// 1->2
		for (int i = 0; i < data.length / 2; i++) {
			for (int j = 0; j < data[0].length / 2; j++) {
				n[i][data[0].length / 2 + j] = data[i][j];
			}
		}

		// 2->3
		for (int i = 0; i < data.length / 2; i++) {
			for (int j = data[0].length / 2; j < data[0].length; j++) {
				n[data.length / 2 + i][j] = data[i][j];
			}
		}

		// 3->4
		for (int i = data.length / 2; i < data.length; i++) {
			for (int j = data[0].length / 2; j < data[0].length; j++) {
				n[i][j - data[0].length / 2] = data[i][j];
			}
		}

		// 4->1
		for (int i = data.length / 2; i < data.length; i++) {
			for (int j = 0; j < data[0].length / 2; j++) {
				n[i - data.length / 2][j] = data[i][j];
			}
		}

		data = n;

	}

	private static void Left90() {
		int[][] n = new int[data[0].length][data.length];
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				n[j][i] = data[i][data[0].length - 1 - j];
			}
		}
		data = n;
	}

	private static void Right90() {

		int[][] n = new int[data[0].length][data.length];
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				n[j][data.length - 1 - i] = data[i][j];
			}
		}
		data = n;
	}

	private static void LeftRight() {
		int[][] n = new int[data.length][data[0].length];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				n[i][data[0].length - 1- j] = data[i][j];
			}
		}
		data = n;
	}

	private static void UpDown() {
		int[][] n = new int[data.length][data[0].length];

		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				n[data.length - 1 - i][j] = data[i][j];
			}
		}
		data = n;
	}

}
