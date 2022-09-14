package baekjoon;

import java.io.*;
import java.util.*;

public class B_1251 {

	private static ArrayList<String> list = new ArrayList<>();
	private static String s;
	private static int[] index = new int[2];

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		s = in.readLine();

		// 8/3 = 2.6~~
		// 2 3 2
		comb(1, 0);

		Collections.sort(list, (o1, o2) -> o1.compareTo(o2));

		System.out.println(list.get(0));
	}

	private static String reverse(int start, int end) {
		String string = new String();
		for (int i = end - 1; i >= start; i--) {
			string += s.charAt(i);
		}
		return string;
	}

	private static void comb(int start, int cnt) {

		if (cnt == 2) {		
			String string = reverse(0, index[0]) + reverse(index[0], index[1]) + reverse(index[1], s.length());
			
			list.add(string);
			return;
		}

		for (int i = start; i < s.length(); i++) {
			index[cnt] = i;
			comb(i + 1, cnt + 1);
		}

	}

}
