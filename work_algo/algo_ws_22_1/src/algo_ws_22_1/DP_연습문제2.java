package algo_ws_22_1;

import java.io.*;
import java.util.*;

// DP 
// 1. 동적 테이블 생성
// 2. 베이스 값 채우기 (재귀에서 기저조건)
// 3. 점화식을 이용하여 상향식으로 동적 테이블 채우기

public class DP_연습문제2 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		// 1. 동적 테이블 생성
		int[] D = new int[N + 1]; // 0 ~ Ncm까지의 경우의 수 저장

		// 2. 베이스 값 채우기
		D[0] = 1;
		D[1] = 2;
		D[2] = 5;

		// 3. 점화식을 이용하여 상향식으로 동적 테이블 채우기
		for (int i = 3; i <= N; i++) {
			// 현재 icm의 경우의 수는 이전 경우의 수 * 2 + 전전 경우의 수
			D[i] = D[i - 1] * 2 + D[i - 2]; 
		}
		
		int answer = D[N];
		sb.append(answer);
		System.out.println(sb);

	}

}
