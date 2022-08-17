package baekjoon;

import java.io.*;
import java.util.*;

public class B_1920 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		int[] A = new int[N];
		String[] split = in.readLine().split(" ");
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(split[i]);
		}
		
		Arrays.sort(A);
		
		int M = Integer.parseInt(in.readLine());
		int[] B = new int[M];
		split = in.readLine().split(" ");
		for(int i=0; i<M; i++) {
			sb.append(BinarySearch2(A, Integer.parseInt(split[i]), 0, A.length - 1)).append("\n");
		}
		
		System.out.println(sb);
		
	}

	private static int BinarySearch(int[] A, int n) {
		
		int start = 0;
		int end = A.length - 1;
		
		while(start <= end) {
			
			int mid = (start + end) / 2;
			
			if(A[mid] == n) {
				return 1;
			}
			
			else if(A[mid] < n)
				start = mid + 1;
			
			else if(A[mid] > n)
				end = mid - 1;
			
		}
		
		return 0;
		
	}
	
	private static int BinarySearch2(int[] A, int n, int start, int end) {
		
		if(start > end)
			return 0;
		
		
		int mid = (start + end) / 2;
		
		if(A[mid] == n)
			return 1;
		
		else if(A[mid] < n)
			return BinarySearch2(A, n, mid + 1, end);
		
		else
			return BinarySearch2(A, n, start, mid - 1);
		
	}

	
}
