package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2839 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int A = Integer.parseInt(in.readLine());

		int b1 = A/5;
		int b2 = A%5;
		int b3 = b2/3;
		
		int result1 = b1*5 + b3*3;
		int result2 = b1+b3;
		
		if(result1 != A) {
			if(A%3==0) {
				b1 = 0; 
				b3 = A/3;
				result2 = b1+b3;
			}else {
				result2 = -1;
			}
		}
		
		sb.append(result2);

		sb.append("\n");

		System.out.println(sb);

	}

}
