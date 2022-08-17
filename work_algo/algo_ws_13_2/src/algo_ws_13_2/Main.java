package algo_ws_13_2;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String string = in.readLine();

		String[] s = string.split("-");

		int sum = 0;
		for (int i = 0; i < s.length; i++) {
			int nums = 0;
			String[] number = s[i].split("\\+");
			for (int j = 0; j < number.length; j++) {
				nums += Integer.parseInt(number[j]);
			}
			if(i==0) // 첫 수는 더함 + "+"만 있을 때
				sum += nums;
			else // 그 다음은 "-"도 있단 의미 
				sum -= nums;
		}
		sb.append(sum);

		System.out.println(sb);
	}

}
