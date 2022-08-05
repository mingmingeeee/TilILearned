package algo_ws_04_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {
	
	private static int[] ACGT;  // 파일로부터 입력받은 A, C, G, T 최소 개수
	private static int[] count = new int[4];  // 부분 문자열에서 A, C, G, T 개수 카운팅
	private static int satisfiedCount = 0;  // 조건 만족 카운터

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		// S와 P 값
		String[] split = in.readLine().split(" ");
		int S = Integer.parseInt(split[0]);
		int P = Integer.parseInt(split[1]);
		
		// DNA 문자열
		String dna = in.readLine();
		
		// 부분 문자열에 포함되어야 할 A, C, G, T의 최소 개수
		split = in.readLine().split(" ");
		ACGT = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();

		/**
		 * 2. 알고리즘 풀기
		 */
		int answer = 0;  // 정답을 저장할 변수
		
		// A, C, G, T 최소 개수가 0개 이상이라는 뜻은 없어도 된다는 뜻이므로 조건 만족 카운트 1 증가
		if (ACGT[0] == 0) {  // A의 최소 개수가 0 일 때
			satisfiedCount++;
		}
		
		if (ACGT[1] == 0) {  // C의 최소 개수가 0 일 때
			satisfiedCount++;
		}
		
		if (ACGT[2] == 0) {  // G의 최소 개수가 0 일 때
			satisfiedCount++;
		}
		
		if (ACGT[3] == 0) {  // T의 최소 개수가 0 일 때
			satisfiedCount++;
		}
		
		String subString = dna.substring(0, P);
		for (int i = 0; i < P; i++) {
			add(subString.charAt(i));
		}
		
		if (satisfiedCount == 4) {
			// 최소 개수를 이미 만족시켰다면 비밀번호 종류의 수 1 증가
			answer++;
		}
		// 여기까지가 최초 부분 문자열에 대한 조건 만족 판단 끝!
		
		// 슬라이딩 윈도우 적용 (한 칸씩 이동하면서 비밀번호 조건 만족 여부 확인)
		// (i는 새로 들어갈 문자의 인덱스 번호, j는 빠져나갈 문자의 인덱스 번호)
		for (int i = P; i < S; i++) {
			int j = i - P;
			add(dna.charAt(i));     // 슬라이딩 윈도우에 문자 하나가 들어옴
			remove(dna.charAt(j));  // 슬라이딩 윈도우에 문자 하나가 나감
			
			if (satisfiedCount == 4) {
				// 최소 개수를 만족시켰다면 비밀번호 종류의 수 1 증가
				answer++;
			}
		}

		/**
		 * 3. 정답 출력
		 */
		sb.append(answer);
		sb.append("\n");

		System.out.println(sb);
	}

	private static void add(char input) {
		switch (input) {
		case 'A':
			count[0]++;
			if (ACGT[0] == count[0]) {
				// 최소 개수를 만족하는 순간 조건 만족 카운트 1 증가
				satisfiedCount++;
			}
			break;
			
		case 'C':
			count[1]++;
			if (ACGT[1] == count[1]) {
				// 최소 개수를 만족하는 순간 조건 만족 카운트 1 증가
				satisfiedCount++;
			}
			break;
			
		case 'G':
			count[2]++;
			if (ACGT[2] == count[2]) {
				// 최소 개수를 만족하는 순간 조건 만족 카운트 1 증가
				satisfiedCount++;
			}
			break;
			
		case 'T':
			count[3]++;
			if (ACGT[3] == count[3]) {
				// 최소 개수를 만족하는 순간 조건 만족 카운트 1 증가
				satisfiedCount++;
			}
			break;
		}
	}
	
	private static void remove(char output) {
		switch (output) {
		case 'A':
			if (ACGT[0] == count[0]) {
				// 최소 개수를 만족하는 순간 조건 만족 카운트 1 감소
				satisfiedCount--;
			}
			count[0]--;  // 주의 : 먼저 빼고 if문을 수행하면 문제가 있음
			break;
			
		case 'C':
			if (ACGT[1] == count[1]) {
				// 최소 개수를 만족하는 순간 조건 만족 카운트 1 감소
				satisfiedCount--;
			}
			count[1]--;
			break;
			
		case 'G':
			if (ACGT[2] == count[2]) {
				// 최소 개수를 만족하는 순간 조건 만족 카운트 1 감소
				satisfiedCount--;
			}
			count[2]--;
			break;
			
		case 'T':
			if (ACGT[3] == count[3]) {
				// 최소 개수를 만족하는 순간 조건 만족 카운트 1 감소
				satisfiedCount--;
			}
			count[3]--;
			break;
		}
	}
}
