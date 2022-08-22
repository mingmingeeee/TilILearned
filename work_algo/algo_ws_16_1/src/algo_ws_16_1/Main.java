package algo_ws_16_1;

import java.io.*;
import java.util.*;

public class Main {

	// 최소 한 개의 모음(aeiou), 최소 두 개의 자음

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] split = in.readLine().split(" ");
		int L = Integer.parseInt(split[0]);
		int C = Integer.parseInt(split[1]);

		char[] words = new char[C];
		char[] new_word = new char[L];

		split = in.readLine().split(" ");
		for (int i = 0; i < C; i++) {
			words[i] = split[i].charAt(0);
		}
		Arrays.sort(words);

		comb(L, 0, 0, new_word, words);

		System.out.print(sb);
	}

	private static void comb(int L, int cnt, int start, char[] new_word, char[] words) {

		if (cnt == L) {
			int count1 = 0;
			int count2 = 0;
			for (int i = 0; i < L; i++) {
				StringBuilder tmp = new StringBuilder();
				tmp.append(new_word[i]);
				if ("aeiou".contains(tmp)) {
					count1++;
				} else {
					count2++;
				}
			}
			if (count1 >= 1 && count2 >= 2) {
				for (int i = 0; i < L; i++)
					sb.append(new_word[i]);
				sb.append("\n");
			}
			return;
		}

		for (int i = start; i < words.length; i++) {
			new_word[cnt] = words[i];
			comb(L, cnt + 1, i + 1, new_word, words);
		}

	}

}
