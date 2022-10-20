package test;

import java.io.*;
import java.util.*;

public class practice2 {

	public static void main(String[] args) throws Exception {

//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			
			int[] days = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			int sum = 0;
			for(int i=1; i<m; i++) {
				sum += days[i];
			}
			
			sum += d - 1 + 4;
			sb.append(sum % 7).append("\n");
		}
		
		System.out.println(sb);

	}

}
