package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_6603 {

	static StringBuilder sb = new StringBuilder();
	static int[] lottos = new int[6];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		while (true) {

			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			if (N == 0)
				break;

			int[] nums = new int[N];
			for (int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}

			com(0, 0, nums, N);
			
			sb.append("\n");

		}

		System.out.println(sb);

	}

	private static void com(int cnt, int start, int[] nums, int N) {

		if (cnt == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(lottos[i] + " ");
			}
			sb.append("\n");
			return;
		}

		for (int i = start; i < N; i++) {

			lottos[cnt] = nums[i];
			com(cnt + 1, i + 1, nums, N);

		}

	}

}
