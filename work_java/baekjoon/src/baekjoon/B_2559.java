package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2559 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(in.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(in.readLine());
		int[] sum = new int[N + 1];
		for(int i=1; i<=N; i++) {
			sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken()); 
		}
		
		int max = Integer.MIN_VALUE;
		int index_old = 1;
		int index_new = K;
		while(index_new <= N) {
			int tmp = sum[index_new] - sum[index_old - 1];
			if(max <= tmp) {
				max = tmp;
			}
			index_old++;
			index_new++;
		}
		sb.append(max);
		System.out.println(sb);

	}

}
