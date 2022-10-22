package baekjoon;

import java.io.*;
import java.util.*;

public class Main_1755 {
	
	private static class Number {
		int num;
		String e_num;
		public Number(int num, String e_num) {
			super();
			this.num = num;
			this.e_num = e_num;
		}
	}

	public static void main(String[] args) throws Exception {

		String[] e_numbers = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		List<Number> sorted_numbers = new ArrayList<>();
		for (int i = M, j = 0; j <= N - M; i++, j++) {
			Number num = null;
			if (i >= 10) {
				int one = i / 10;
				int two = i % 10;

				num = new Number(i, e_numbers[one] + " " + e_numbers[two]);
			} else {
				num = new Number(i, e_numbers[i]);
			}
			sorted_numbers.add(num);
		}

		Collections.sort(sorted_numbers, new Comparator<Number>(){

			@Override
			public int compare(Number o1, Number o2) {
				// TODO Auto-generated method stub
				return o1.e_num.compareTo(o2.e_num);
			}
			
		});

		int count = 1;
		for (Number i : sorted_numbers) {
			sb.append(i.num + " ");
			if(count++ % 10 == 0)
				sb.append("\n");
		}

		System.out.println(sb);
	}

}
