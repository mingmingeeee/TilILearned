package baekjoon;

import java.io.*;
import java.util.*;

public class B_17213 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		int M = Integer.parseInt(in.readLine());

		int total = M - N;

		comb(0, 0, N, total);

		System.out.println(totalCnt);

	}

	static int totalCnt = 0;

	static void comb(int cnt, int start, int N, int total) {
		if (cnt == total) {
			totalCnt++;
			return;
		}

		for (int i = start; i < N; i++) {
			comb(cnt + 1, i, N, total);
		}

	}

}
