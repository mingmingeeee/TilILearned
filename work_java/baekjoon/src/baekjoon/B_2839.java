package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2839 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int input = Integer.parseInt(in.readLine());

		int k = 0;
		int min = -1;
		while (true) {
			if ((input - (k * 5)) % 3 == 0)
				min = k; // 5kg 봉지
			k++;
			if (k >= input / 5 + 1)
				break;
		}
		if (min > -1)
			min = min + (input - min * 5) / 3; // 5kg + 3kg
		sb.append(min);

		sb.append("\n");

		System.out.println(sb);

	}

}
