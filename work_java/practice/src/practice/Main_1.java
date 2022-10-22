package practice;

import java.io.*;
import java.util.*;

public class Main_1 {

	private static class Number implements Comparable<Number>{
		int num;
		String en_num;

		public Number(int num, String en_num) {
			super();
			this.num = num;
			this.en_num = en_num;
		}

		@Override
		public int compareTo(Number o) {
			return this.en_num.compareTo(o.en_num);
		}

		
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		String[] en_nums = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Number> list = new ArrayList<>();
		
		for (int i = N; i <= M; i++) {
			char[] number = Integer.toString(i).toCharArray();
			
			StringBuilder sb = new StringBuilder();
			for(int j=0; j<number.length; j++) {
				sb.append(en_nums[number[j] - '0'] + " ");
			}
			
			list.add(new Number(i, sb.toString()));
		}
		
		Collections.sort(list);
		
		StringBuilder sb = new StringBuilder();
		
		int count = 1;
		for(Number num : list) {
			sb.append(num.num).append(" ");
			if(count++ % 10 == 0)
				sb.append("\n");
		}
		
		System.out.println(sb);

	}

}
