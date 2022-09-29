package hwalgo22_부울경_04반_강민정;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		int[][] D = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			String[] split = in.readLine().split(" ");

			int r = Integer.parseInt(split[0]);
			int g = Integer.parseInt(split[1]);
			int b = Integer.parseInt(split[2]);

			D[i][0] = Math.min(D[i - 1][1], D[i - 1][2]) + r;
			D[i][1] = Math.min(D[i - 1][0], D[i - 1][2]) + g;
			D[i][2] = Math.min(D[i - 1][0], D[i - 1][1]) + b;
		}

		int answer = Math.min(D[N][0], Math.min(D[N][1], D[N][2]));

		System.out.println(answer);

	}

}
