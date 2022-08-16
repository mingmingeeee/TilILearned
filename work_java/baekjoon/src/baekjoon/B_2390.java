package baekjoon;

import java.io.*;
import java.util.*;


// np 조합 
public class B_2390 {
	static int flag = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[] dwarfs = new int[9];
		for(int i=0; i<9; i++) {
			dwarfs[i] = Integer.parseInt(in.readLine()); 
		}
		
		int[] mask = new int[9];
		for(int i=8; i>=2; i--) {
			mask[i] = 1; 
		}
		
		int[] real_dwarfs = new int[7];
		do {
			int sum = 0;
			for(int i=0, j=0; i<9; i++) {
				if(mask[i]==1) {
					real_dwarfs[j++] = dwarfs[i];
					sum += dwarfs[i];
				}
			}
			if(sum==100) {
				Arrays.sort(real_dwarfs);
				for(int i=0; i<7; i++) {
					sb.append(real_dwarfs[i]+"\n");
				}
				break;
			}
		}while(np(mask));
	
		System.out.println(sb);
	}
	
	private static boolean np(int[] numbers) {
		
		int N = numbers.length;
		
		int i = N - 1;
		while(i>0 && numbers[i-1] >= numbers[i]) {
			--i;
		}
		
		if(i==0)
			return false;
		
		int j = N - 1;
		while(numbers[i-1]>=numbers[j]) {
			--j;
		}
		
		swap(numbers, i-1, j);
		
		int k = N - 1;
		while(i<k) {
			swap(numbers, i++, k--);
		}
		
		return true;
	}
	
	private static void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp; 
	}
	
}
