package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_11399 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		String[] strings = in.readLine().split(" ");

		int[] time = new int[N];
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(strings[i]);
		}

		Arrays.sort(time);

		int[] sum = new int[N + 1];
		int result = 0;
		for (int i = 1; i <= N; i++) {
			sum[i] = sum[i - 1] + time[i - 1];
			result += sum[i];
		}

		sb.append(result);
		System.out.println(sb);
	}

}
