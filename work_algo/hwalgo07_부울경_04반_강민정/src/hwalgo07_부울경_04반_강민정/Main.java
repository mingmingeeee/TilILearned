package hwalgo07_부울경_04반_강민정;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		int[][] squares = new int[100][100];
		int answer = 0;

		for (int i = 0; i < T; i++) {
			String[] split = in.readLine().split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);

			for (int j = x; j < x + 10; j++) {
				for (int k = y; k < y + 10; k++) {
					squares[j][k] += 1;
				}
			}
		}

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (squares[i][j] == 1)
					answer += squares[i][j];
				else if (squares[i][j] > 1)
					answer += 1;
			}
		}

		System.out.println(answer);
	}

}
