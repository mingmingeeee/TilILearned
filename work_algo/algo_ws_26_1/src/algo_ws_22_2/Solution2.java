package algo_ws_22_2;

import java.io.*;
import java.util.*;

public class Solution2 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			sb.append("#" + tc + " ");
			
			
			int N = Integer.parseInt(in.readLine());
			int[] arr = new int[N];
			int[] C = new int[N];
			
			String[] split = in.readLine().split(" ");
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(split[i]);
			}
			
			int size = 0;
			for(int i=0; i<N; i++) {
				int pos = Arrays.binarySearch(C, 0, size, arr[i]);
				
				if(pos >= 0) continue;
				
				pos = Math.abs(pos) - 1;
				C[pos] = arr[i];
				
				if(pos == size)
					size++;
			}
			
			sb.append(size).append("\n");
			
			
		}
		
		System.out.println(sb);
		
	}

}
