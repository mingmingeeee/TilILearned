package algo_ws_22_2;

import java.io.*;
import java.util.*;

public class Solution2 {
	
	private static final int INF = 999999;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {

			sb.append("#" + tc + " ");

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");

			int N = Integer.parseInt(st.nextToken());
			int[][] D = new int[N][N];


			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					D[i][j] = Integer.parseInt(st.nextToken());

					if (i != j && D[i][j] == 0)
						D[i][j] = INF;
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if(i==k) continue;
					for (int j = 0; j < N; j++) {
						if(j==i||j==k) continue;
						D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
			
			for(int i=0; i<N; i++) {
				int sum = 0;
				for(int j=0; j<N; j++) {
					sum += D[i][j];
				}
				min = Math.min(min, sum);
			}
			
			sb.append(min).append("\n");

		}

		System.out.println(sb);

	}

}
