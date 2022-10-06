package algo_ws_22_2;

import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			int n = Integer.parseInt(in.readLine());
			int[] data = new int[n];
			int[] dp = new int[n];

			String[] split = in.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				data[i] = Integer.parseInt(split[i]);
			}
			
			int max = 0;
			for(int i=0; i<n; i++) {
				dp[i] = 1;
				for(int j=0; j<i; j++) {
					if(data[j] < data[i] && dp[i] < dp[j] + 1) {
						dp[i] = dp[j] + 1;
					}
				}
				max = Math.max(max, dp[i]);
			}
			
			sb.append(max).append("\n");

		}
		
		System.out.println(sb);
	}

}