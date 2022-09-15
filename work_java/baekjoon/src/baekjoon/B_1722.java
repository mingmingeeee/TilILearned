package baekjoon;

import java.io.*;
import java.util.*;

public class B_1722 {

	private static int[] tmp;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		String[] s = in.readLine().split(" ");

		int n = Integer.parseInt(s[0]);

		boolean isTrue = false;
		if (n == 1) {
			isTrue = true;
		}

		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(s[i + 1]);
		}

		tmp = new int[N];

		for (int i = 0; i < N; i++) {
			tmp[i] = i + 1;
		}

		int cnt = 1;
		do {
			if(isTrue) {
				if(cnt == nums[0]) {
					for(int i=0; i<N; i++) {
						sb.append(tmp[i]).append(" ");
					}
					break;
				}
			} else {
				int flag = 0;
				for(int i=0; i<n; i++) {
					if(nums[i] != tmp[i]) {
						flag = 1;
						break;
					}
				}
				if(flag == 0) {
					sb.append(cnt);
					break;
				}
			}
			
			cnt++;
		} while (np());
		
		System.out.println(sb);

	}

	private static boolean np() {
		int N = tmp.length;

		int i = N - 1;
		while (i > 0 && tmp[i - 1] >= tmp[i]) {
			i--;
		}
		if (i == 0)
			return false;

		int j = N - 1;
		while (tmp[i - 1] >= tmp[j]) {
			j--;
		}
		swap(i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(i++, k--);
		}

		return true;
	}

	private static void swap(int i, int j) {
		int temp = tmp[i];
		tmp[i] = tmp[j];
		tmp[j] = temp;
	}

}
