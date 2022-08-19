package algo_ws_15_1;

import java.io.*;
import java.util.*;

public class Solution2 {
	
	static int N;
	static int[] input;
	static int answer;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			sb.append("#" + test_case + " ");

			N = Integer.parseInt(in.readLine());

			String[] split = in.readLine().split(" ");
			input = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(split[i]);
			}

			answer = 0;
			Arrays.sort(input);
			do {
				
				put(0, 0, 0);
				
			} while (np(input));
			
			sb.append(answer).append("\n");

		}
		
		System.out.println(sb);

	}
	
	private static void put(int index, int left, int right) {
		
		if(index == N) {
			answer++;
			return;
		}
		
		put(index + 1, left + input[index], right);
		
		if(left >= right + input[index]) {
			put(index + 1, left, right + input[index]);
		}
		
	}
	
//	private static void put(int index, int left, int right) {
//		
//		if(left > right) return;
//		
//		if(index == N) {
//			answer++;
//			return;
//		}
//		
//		put(index + 1, left + input[index], right);
//		
//		put(index + 1, left, right + input[index]);
//		
//	}

	private static boolean np(int[] data) {

		int N = data.length;

		int i = N - 1;
		while (i > 0 && data[i - 1] >= data[i]) {
			--i;
		}

		if (i == 0)
			return false;

		int j = N - 1;
		while (data[i - 1] >= data[j]) {
			--j;
		}

		swap(data, i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(data, i++, k--);
		}

		return true;

	}

	private static void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

}
