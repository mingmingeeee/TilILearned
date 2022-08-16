package baekjoon;

import java.io.*;
import java.util.*;

public class B_9081 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int i = 0; i < T; i++) {
			String s = in.readLine();
			
			char[] word = s.toCharArray();			

			int k = 0;
			while(perm(word)) {
				for(int j=0; j<word.length; j++)
					sb.append(word[j]);
				sb.append("\n");
				k++;
				break;
			}
			
			if(k==0)
				sb.append(s + "\n");
		}
		System.out.println(sb);

	}

	public static boolean perm(char[] word) {
		int N = word.length;

		int i = N - 1;
		while (i > 0 && word[i - 1] >= word[i]) {
			--i;
		}
		if (i == 0) {
			return false;
		}

		int j = N - 1;
		while (word[i - 1] >= word[j]) {
			--j;
		}

		swap(word, i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(word, i++, k--);
		}

		return true;

	}

	public static void swap(char[] word, int i, int j) {
		char tmp = word[j];
		word[j] = word[i];
		word[i] = tmp;
	}

}
