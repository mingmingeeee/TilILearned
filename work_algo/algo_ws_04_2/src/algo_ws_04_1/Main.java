package algo_ws_04_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * 네자리 소수 
 * 앞자리: 소수 {2, 3, 5, 7}
 * 			  *10 + ...
 * 			20+0 = 20 <- 소수 확인 
 * 			20*10 + 0~9
 * 			200*10 + 0~9
 * -> 이 결과: 소수면 네자리수 소수 
 */

public class Main {
	
	static int[] d = {2, 3, 5, 7};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		/**
		 * 1. 입력 파일 객체화
		 */
		
		int N = Integer.parseInt(in.readLine());

		/**
		 * 2. 알고리즘 풀기
		 */
		
		for(int i=0; i<d.length; i++) {
			de(N, d[i]);
		}

		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);
	}
	
	static boolean Prime(int n) {
		if(n == 1) {
			return false;
		}
	
		for(int i=2; i*i<=n; i++) {
			if(n%i==0)
				return false;
		}
		return true;
	}
	
	static void de(int cnt, int num) {

		if(cnt==1) {
			if(Prime(num)) { // 소수
				sb.append(num).append("\n");
			}
			return;
		}
		
		if(Prime(num)) { // 소수
			for(int i=0; i<=9; i++) {
				de(cnt-1, num*10 + i);
			}
		}

		
	}

}
