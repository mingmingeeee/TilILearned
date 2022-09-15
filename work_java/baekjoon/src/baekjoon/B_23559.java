package baekjoon;

import java.io.*;
import java.util.*;

public class B_23559 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] s = in.readLine().split(" ");

		int N = Integer.parseInt(s[0]);
		int X = Integer.parseInt(s[1]);

		int sum = 0;

		int[][] AB = new int[N][2];

		for (int i = 0; i < N; i++) {
			s = in.readLine().split(" ");
			AB[i][0] = Integer.parseInt(s[0]);
			AB[i][1] = Integer.parseInt(s[1]);

			sum += AB[i][1];
		}

		Arrays.sort(AB, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return (o1[1] - o1[0]) - (o2[1] - o2[0]);
			}
		});

		int cnt = (X - 1000 * N) / 4000;

		for (int i = 0; AB[i][0] > AB[i][1] && cnt > 0; i++) {
			sum -= AB[i][1];
			sum += AB[i][0];
			cnt--;
		}
		
		sb.append(sum);

		System.out.println(sb);
	}

}
