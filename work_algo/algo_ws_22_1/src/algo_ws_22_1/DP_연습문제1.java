package algo_ws_22_1;

import java.io.*;
import java.util.*;

// DP 
// 1. 동적 테이블 생성
// 2. 베이스 값 채우기 (재귀에서 기저조건)
// 3. 점화식을 이용하여 상향식으로 동적 테이블 채우기

public class DP_연습문제1 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine()); // N: 아파트 층수
		// DP: 처음에 기저 조건 세팅 (초기화) => 베이스로 해서 점화식을 이용해 풀어나가는 것

		// 1. 동적 테이블 생성
		// D[N][0]: N층을 노란색으로 칠했을 때
		// D[N][1]: N층을 파란색으로 칠했을 때
		int[][] D = new int[N + 1][2]; // 인덱스 번호 0을 사용하지 않으려고 N + 1 로!

		// 2. 베이스 값 채우기 (재귀에서 기저조건)
		D[1][0] = 1;
		D[1][1] = 1;

		// 3. 점화식을 이용하여 상향식으로 동적 테이블 채우기
		for (int i = 2; i <= N; i++) {
			D[i][0] = D[i - 1][0] + D[i - 1][1]; // 현재 층이 노란색인 경우의 수는 이전 층의 노란색 + 이전 층의 파란색의 경우의 수
			D[i][1] = D[i - 1][0]; // 현재 층이 파란색인 경우의 수는 이전 층의 노란색의 경우의 수
		}
		
		int answer = D[N][0] + D[N][1];
		
		sb.append(answer);
		System.out.println(answer);

	}

}
