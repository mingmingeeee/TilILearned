package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_1357 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] split = in.readLine().split(" ");
		
		char[] N = split[0].toCharArray();
		char[] M = split[1].toCharArray();
		
		int n = Rev(N);	
		int m = Rev(M);
		
		int sum = n + m;
		char[] Sum = sb.append(sum).toString().toCharArray();
		
		sum = Rev(Sum);
		System.out.println(sum);
		
		
		
	}
	
	static int Rev(char[] n) {
		StringBuilder sb2 = new StringBuilder();
		for(int i=0; i<n.length; i++) {
			sb2.append(n[n.length-i-1]); 
		}
		int m = Integer.parseInt(sb2.toString());
		return m;
	}

}
