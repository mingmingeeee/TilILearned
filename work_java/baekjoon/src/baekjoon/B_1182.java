package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1182 {
	
	static StringBuilder sb = new StringBuilder();
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int[] input = new int[N];
		boolean[] isSelected = new boolean[N];
		for(int i=0; i<N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, input, isSelected, N, M);
		sb.append(count);
		System.out.println(sb);

	}
	
	private static void subset(int index, int[] input, boolean[] isSelected, int N, int M) {
		
		if(index == N) {
			int sum = 0;
			int flag = 0;
			for(int i=0; i<N; i++) {
				if(isSelected[i]) {
					flag = 1;
					sum += input[i];
				}
			}
			
			if(sum==M && flag == 1) {
				count++;
			}
			
			return;
		}
		
		isSelected[index] = true; 
		subset(index + 1, input, isSelected, N, M);
		isSelected[index] = false;
		subset(index + 1, input, isSelected, N, M);
	}
	

}
