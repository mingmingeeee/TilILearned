package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class B_1806 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] strings = in.readLine().split(" ");
		int N = Integer.parseInt(strings[0]);
		int S = Integer.parseInt(strings[1]);
		
		// 2부터 N까지
		strings = in.readLine().split(" ");
		int[] sum = new int[N + 1];
		for(int i=1; i<=N; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(strings[i-1]);
		}
		
		int min = Integer.MAX_VALUE;
		
		int start = 1;
		int end = 1;
		
		while(end <= N) {
			if(sum[end] - sum[start - 1] >= S) {
				if(min > end - start + 1) {
					min = end - start + 1;
				}
				start++;
			} else {
				end++;
			}
		}
		
		if(min == Integer.MAX_VALUE)
			min = 0;
		System.out.println(min);
		

		// 시간 초과 //
//		int[] sum = new int[N + 1];
//		for(int i=1; i<=N; i++) {
//			sum[i] = sum[i-1] + Integer.parseInt(strings[i-1]);
//		}
//		
//		
//		for(int i=1; i<=N; i++) {
//			int index_old = 1;
//			int index_new = i;
//			while(index_new<=N) {
//				
//				if(sum[index_new] - sum[index_old-1] >= S) {
//					System.out.println(i);
//					return;
//				}
//				index_old++;
//				index_new++;
//				
//			}
//		}
//		
		
	}

}
