package baekjoon;

import java.io.*;
import java.util.*;

// 1. N개의 공을 K개의 바구니에 빠짐없이 나누어 담기
// 2. 각 바구니에는 1개 이상의 공이 들어있어야 함
// 3. 각 바구니에 담긴 공의 개수는 모두 달라야 함
// 4. 가장 많이 담긴 바구니와 가장 적게 담긴 바구니의 공의 개수 차이가 치소가 되어야 함
// 출력: 가장 많이 담긴 바구니의 공의 개수 - 가장 적게 담긴 바구니의 공의 개수

public class B_19939 {

	private static int N;
	private static int K;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] s = in.readLine().split(" ");

		N = Integer.parseInt(s[0]);
		K = Integer.parseInt(s[1]);

		for (int i = 1; i <= K; i++) {
			N -= i;
			if (N < 0) {
				break;
			}
		}
		
		if (N >= 0) {
			if (N % K == 0) {
				sb.append(K - 1);
			} else {
				sb.append(K);
			}
		} else {
			sb.append(-1);
		}

		System.out.println(sb);

	}

}
