package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B_1931 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());

		int[][] time = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			time[i][0] = Integer.parseInt(st.nextToken());
			time[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(time, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[1] == o2[1])
					return o1[0] - o2[0];
				return o1[1] - o2[1];
			}

		});

		int count = 0;
		int time2 = 0;
		for (int i = 0; i < N; i++) {
			if (time2 <= time[i][0]) {
				time2 = time[i][1];
				count++;
			}
		}
		
		System.out.println(count);

	}

}
