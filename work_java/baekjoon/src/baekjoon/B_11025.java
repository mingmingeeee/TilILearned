package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_11025 {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
	
		String[] split = in.readLine().split(" ");
		int n = Integer.parseInt(split[0]);
		int k = Integer.parseInt(split[1]);
		
	
		int ans = 0;
		for (int i = 2; i <= n; ++i) {
			ans = (ans + k) % i;
		}
		System.out.println(ans+1);
		
	}

}
