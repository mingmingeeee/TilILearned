package com.ssafy.hw.problem;

import java.util.*;
import java.io.*;

public class Main {

	static int[] switches; // 스위치 배열
	static int number; // 받을 수

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		String[] strings = in.readLine().split(" ");

		// 스위치 입력 받기
		switches = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			switches[i] = Integer.parseInt(strings[i]);
		}

		// 몇 명 받을 건지
		int num = Integer.parseInt(in.readLine());

		for (int i = 0; i < num; i++) {
			strings = in.readLine().split(" ");

			int gender = Integer.parseInt(strings[0]); // 성별
			number = Integer.parseInt(strings[1]); // 받은 수 

			// 남자일 때
			if (gender == 1) {
				man(switches.length - 1);
			}

			// 여자일 때
			else if (gender == 2) {
				woman(0);
			}

		}

		for (int i = 0; i < switches.length; i++) {
			if (i > 0 && i % 20 == 0) // 한 줄 20개
				sb.append("\n");
			sb.append(switches[i] + " ");
		}
		System.out.println(sb);

	}

	static void exchange(int i) { // 수 바꿈
		if (switches[i] == 1)
			switches[i] = 0;
		else
			switches[i] = 1;
	}

	static void man(int n) { // 남자일 때 -> 받은 수의 배수 자리 스위치 바꿈

		if (n < 0)
			return;

		if ((n + 1) % number == 0) {
			exchange(n);
		}

		man(n - 1);

	}

	static void woman(int count) { // 여자일 때 -> 대칭으로 이동하면서 다 바꿈 
		if (number - 1 - count < 0 || number - 1 - count >= switches.length || number - 1 + count < 0
				|| number - 1 + count >= switches.length
				|| switches[number - 1 - count] != switches[number - 1 + count]) {
			return;
		}

		exchange(number - 1 - count);
		if (count > 0)
			exchange(number - 1 + count);

		woman(++count);

	}

}
