package algo_ws_16_1;

import java.io.*;
import java.util.*;

public class Main2 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		int L = Integer.parseInt(split[0]); // 만들어야 하는 암호 문자 L개
		int C = Integer.parseInt(split[1]); // C개의 문자

		int[] input = new int[C]; // 전체 요소 C개를 저장하는 배열
		char[] password = new char[L]; // 전체 요소 C개 중 L개를 선택하여 저장하는 배열

		split = in.readLine().split(" ");

		for (int i = 0; i < C; i++) {
			input[i] = split[i].charAt(0) - 'a'; // 알파벳을 숫자로 치환하여 저장
		}
		
		// 가능성 있는 암호들을 담을 리스트
		List<String> answer = new ArrayList<>();

		int[] P = new int[C]; // flag 배열 -> np 조합 

		int cnt = 0;
		while (++cnt <= L) {
			P[C - cnt] = 1; // 뒤에서 부터 L만큼 채우기
		}

		do {
			int cursor = 0; // 원래로 치면 cnt
			for (int i = 0; i < C; i++) {
				if (P[i] == 1) { // flag 있는 배열 
					password[cursor++] = (char) (input[i] + 'a'); // input의 숫자를 문자로 치환
				}
			}

			// 알파벳 오름차순 정렬
			Arrays.sort(password);

			// 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음으로 구성되어 있는지 확인
			int vowel = 0; // 모음의 수
			int consonant = 0; // 자음의 수
			boolean isAvaliable = false;

			for (int i = 0; i < L; i++) {
				if (password[i] == 'a' || password[i] == 'e' || password[i] == 'i' || password[i] == 'o'
						|| password[i] == 'u') {
					vowel++;
				} else {
					consonant++;
				}
				
				// 암호 조건을 충분히 만족하면 for문 바로 중단 (가지치기)
				if(vowel >= 1 && consonant >= 2) {
					isAvaliable = true;
					break;
				}
			}
			
			// 가능한 경우이면 리스트에 저장
			if(isAvaliable) {
				answer.add(new String(password)); 
				// char[] -> String: new String(char[])
			}

		} while (np(P));
		
		// 리스트에 저장된 암호들을 오름차순으로 정렬
		Collections.sort(answer);
		
		for(String aString : answer) {
			sb.append(aString).append("\n");
		}
		
		System.out.println(sb);

	}

	static boolean np(int[] input) {
		int N = input.length;

		int i = N - 1;
		while (i > 0 && input[i - 1] >= input[i]) {
			--i;
		}
		if (i == 0)
			return false;

		int j = N - 1;
		while (input[i - 1] >= input[j]) {
			--j;
		}

		swap(input, i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(input, i++, k--);
		}

		return true;

	}

	static void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

}
