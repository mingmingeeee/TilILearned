package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_10974 {

	static StringBuilder sb = new StringBuilder();
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());

			count = 0;
			for(int j=1; j<=N; j++) {
				int[] number = new int[j];
				perm(0, j, number, N);
			}
			
			sb.append(count + "\n");
		}

		System.out.println(sb);
	}

	private static void perm(int cnt, int j, int[] number, int N) {

		if(cnt==j) {
			int sum = 0;

			for(int i=0; i<cnt; i++) {
				sum += number[i];
			}
			if(sum==N)
				count++;
			return;
		}
		
		for(int i=1; i<=3; i++) {
			number[cnt] = i;
			perm(cnt + 1, j, number, N);
		}
	}

}
