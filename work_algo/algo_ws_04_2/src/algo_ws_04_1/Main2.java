package algo_ws_04_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import javax.crypto.SealedObject;

public class Main2 {

	private static StringBuilder sb = new StringBuilder();
	private static int N;
	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */

		N = Integer.parseInt(in.readLine());
		
		/**
		 * 2. 알고리즘 풀기
		 */
		// 맨 앞 첫 자리는 소수로 고정
		int[] prime = {2, 3, 5, 7};
		
		// 1과 자기 자신(num)을 제외한 숫자로 나눠 떨어지면 소수가 아님 
		for(int i=0; i<prime.length; i++) {
			appendNumber(prime[i]);
		}

		/**
		 * 3. 정답 출력
		 */

		System.out.println(sb);
	}
	
	// num 값이 소수인지 판별하는 메서드
	private static boolean isPrime(int num) {
		if(num < 2) {
			return false;
		}
		
		// Math.sqrt(num) <- 소수 구할 때 사용!!!
		for(int i=2; i<=Math.sqrt(num); i++) {
			if(num%i==0) {
				return false;
			}
		}
		
		return true;
	}
	
	private void appendNumber(int seed) {
		
		// 기저 부분 
		if(String.valueOf(seed).length() == N) {
			// String.valueOf -> int를 String으로 
			sb.append(seed); // 네자리수 출력 후 
			sb.append("\n"); // 끝 
			return;
		}
		
		// 유도 부분 
		for(int i=0; i<10; i++) {
			int testNum = seed * 10 + i; // seed * 10 + (0~9까지 숫자)
			if(isPrime(testNum)) { // testNum이 소수인지 판별 후 소수이면 다음 소수를 구하기 위해 재귀 호출 
				appendNumber(testNum);
			}
		}
		
	}

}
