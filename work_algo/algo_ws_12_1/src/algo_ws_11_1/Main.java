package algo_ws_11_1;

import java.io.*;

public class Main {
	static int N;
	static int[] numbers;
	static int[] bongji = {5, 3};
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());

		for(int i=N/5; i<=N/3; i++) {
			numbers = new int[i];
			comb(0, 0, i);
		}
		
		System.out.println(-1);
		
	}
	
	static void comb(int cnt, int start, int R){
		
		if(cnt == R) {
			int sum = 0;
			for(int i=0; i<R; i++) {
				sum += numbers[i];
			}
			if(sum==N) {
				System.out.println(R);
				System.exit(0);
			}
			return;
		}
		
		for(int i=start; i<2; i++) {
			numbers[cnt] = bongji[i];
			comb(cnt + 1, i, R);
		}
		
	}

}
