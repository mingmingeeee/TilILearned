package algo_ws_13_2;

import java.io.*;
import java.util.*;

public class Main2 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split("-");

		int[] numbers = new int[split.length];
		for (int i = 0; i < split.length; i++) {
			String s = split[i];

			String[] exp = s.split("\\+");
			int sum = 0;
			for (int j = 0; j < exp.length; j++) {
				sum += Integer.parseInt(exp[j]); // 더하기 더한 다음
			}

			numbers[i] = sum; // 숫자 배열에 저장 
		}
		
		// numbers에 담긴 수를 순서대로 뺄셈한다.
		int answer = numbers[0];
		for(int i=1; i<numbers.length; i++)
			answer -= numbers[i];
		
		sb.append(answer);
		System.out.println(answer);

	}

}
