package baekjoon;

import java.io.*;
import java.util.*;

public class B_2870 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(in.readLine());

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			String[] s = in.readLine().split("\\D");
			for (int j = 0; j < s.length; j++) {
				if (s[j].length() >= 1) {
					list.add(Integer.parseInt(s[j]));
				}
			}
		}
		
		Collections.sort(list);
		
		for(int i : list) {
			sb.append(i);
			sb.append("\n");
		}
		
		System.out.println(sb);

	}

}
