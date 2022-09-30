package algo_ws_22_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		// 1. 동적 테이블 생성 (연산 횟수 저장)
		int[] D = new int[N + 1]; // 1부터  N까지 사용 
		
		// 2. 베이스 값 채우기
		D[1] = 0; // 1을 1로 만들기 위한 최소 연산 횟수는 0
		// D[2] = 1; // 2를 1로 만들기 위한 최소 연산 횟수는 1
		// D[3] = 1; // 3을 1로 만들기 위한 최소 연산 횟수는 1
		// D[4] = 2; // 4를 1로 만들기 위한 최소 연산 횟수는 2
		
		// 3. 점화식을 이용하여 상향식으로 동적 테이블 채우기
		for(int i = 2; i <= N; i++) {
			int min = Integer.MAX_VALUE;
			
			// 연산 횟수가 가장 작은 녀석 구하기
			min = Math.min(min,  D[i - 1] + 1); // (1) 1을 빼면 이전 연산 횟수에 1 증가
			if(i % 2 == 0) min = Math.min(min,  D[i / 2] + 1); // (2) 2로 나누어지면 2로 나눈 수의 연산 횟수에 1 증가 
			if(i % 3 == 0) min = Math.min(min,  D[i / 3] + 1); // (3) 3로 나누어지면 3으로 나눈 수의 연산 횟수에 1 증가
			
			D[i] = min;
		} 
		
		sb.append(D[N]);
		System.out.println(sb);
		
	}

}