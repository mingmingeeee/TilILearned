package baekjoon;

import java.io.*;
import java.util.*;

public class B_10610 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String s = in.readLine();
		char[] num = s.toCharArray();

		int sum = 0;
		boolean isZero = false;
		
		for (int i = 0; i < num.length; i++) {
			sum += num[i] - '0';
			if(num[i] - '0' == 0) {
				isZero = true;
			}
		}

		if (isZero && sum % 3 == 0) {
			Arrays.sort(num);
			for (int i = num.length - 1; i >= 0; i--) {
				sb.append(num[i]);
			}
		} else {
			sb.append(-1);
		}

		System.out.println(sb);
	}

}
