package SWEA;

import java.io.*;
import java.util.*;

public class S_1952 {

	private static int[] price; // 1일, 1달, 3달, 1년
	private static int[] plans;
	private static int min;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			price = new int[4];
			plans = new int[13];
			min = Integer.MAX_VALUE;

			String[] split = in.readLine().split(" ");
			for (int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(split[i]);
			}

			split = in.readLine().split(" ");
			for (int i = 1; i <= 12; i++) {
				plans[i] = Integer.parseInt(split[i - 1]);
			}

			cal(1, 0);

			sb.append(min).append("\n");

		}

		System.out.println(sb);

	}

	private static void cal(int cnt, int sum) {

		if(sum > min) {
			return;
		}

		if(cnt == 13) {
			min = Math.min(min, sum);
			return;
		}
		
		if(plans[cnt] == 0) 
			cal(cnt + 1, sum);
		
		// 1일 사용권
		if(plans[cnt] > 0) 
			cal(cnt + 1, sum + price[0] * plans[cnt]);
		
		// 1달 사용권
		cal(cnt + 1, sum + price[1]);
		
		// 3달 사용권
		if(cnt <= 10)
			cal(cnt + 3, sum + price[2]);
		
		// 1년 사용권
		if(cnt == 1)
			cal(cnt + 12, sum + price[3]);

	}

}
