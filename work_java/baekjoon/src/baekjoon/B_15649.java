package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_15649 {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] s = in.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int M = Integer.parseInt(s[1]);
		
		int[] nums = new int[M];

		com(0, 1, N, M, nums);
		
		System.out.println(sb);
		
	}
	
	static void com(int cnt, int start, int N, int M, int[] nums) {
		if(cnt == M) {
			for(int i=0; i<M; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<=N; i++) {
			nums[cnt] = i;
			com(cnt + 1, i, N, M, nums);
		}
	}

}
