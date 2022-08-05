package algo_ws_04_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 9개가 주어지고 그 중에 8개 뽑아야 하고
// 각 문자마다 개수 제한 있음

public class Main {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		st = new StringTokenizer(in.readLine(), " ");
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		// A C G T 최소 개수
		int[] n = new int[4];
		// A C G T 각 각의 카운트 저장할 배열
		int[] c = new int[4];
		
		// 민호가 만들 password
		char[] password = new char[S];
		
		char[] word = {'A', 'C', 'G', 'T'};

		String s = in.readLine();
		for (int i = 0; i < S; i++) {
			password[i] = s.charAt(i);
		}
		

		int count = 0;

		st = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			n[i] = Integer.parseInt(st.nextToken());
			if(n[i]==0)
				count++;
		}

		/**
		 * 2. 알고리즘 풀기
		 */
		int answer = 0;

		int index1 = 0; // 시작 인덱스 -> 계속 들어오는 인덱스 
		int index2 = 0; // 빠질 인덱스

		
		while (index1 < S) {

			if (password[index1] == 'A') {
				c[0]++;
				if(c[0] == n[0])
					count++;
			} else if (password[index1] == 'C') {
				c[1]++;
				if(c[1] == n[1])
					count++;
			} else if (password[index1] == 'G') {
				c[2]++;
				if(c[2] == n[2])
					count++;
			} else if (password[index1] == 'T') {
				c[3]++;
				if(c[3] == n[3])
					count++;
			}

			if(count==4)
				answer++;
			
			index1++;
			
			if (index1 == index2 + P) {
				if (password[index2] == 'A') {
					if(c[0] == n[0])
						count--;
					c[0]--;
				} else if (password[index2] == 'C') {
					if(c[1] == n[1])
						count--;
					c[1]--;
				} else if (password[index2] == 'G') {
					if(c[2] == n[2])
						count--;
					c[2]--;
				} else if (password[index2] == 'T') {
					if(c[3] == n[3])
						count--;
					c[3]--;
				}
				index2++; // 다음 인덱스 시작
			}
			
			
		}
		
		sb.append(answer);

		/**
		 * 3. 정답 출력
		 */

		System.out.println(sb);
	}

}
