package ctalgo01_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Solution3 {

	private static int[] ticket;
	private static int[] table;
	private static int min;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			ticket = new int[4]; // 1일, 1달, 3달, 1년
			table = new int[13]; // 12개월 이용 계획, 0번은 사용안함

			String[] split = in.readLine().split(" ");
			for (int i = 0; i < 4; i++) {
				ticket[i] = Integer.parseInt(split[i]);
			}

			split = in.readLine().split(" ");
			for (int i = 1; i <= 12; i++) {
				table[i] = Integer.parseInt(split[i - 1]);
			}

			/**
			 * 2. 알고리즘 풀기
			 */
			min = ticket[3]; // 최솟값은 1년 이용권으로 초기화

			// 1. 동적 테이블 생성
			int[] D = new int[13];

			// 2. 베이스 값 채우기
			// 1일 이용권과 1달 이용궈 중 최소 비용 저장
			D[1] = D[0] + Math.min(ticket[0] * table[1], ticket[1]); // 1월
			D[2] = D[1] + Math.min(ticket[0] * table[2], ticket[2]); // 2월

			// 3. 점화식을 이용하여 상향식으로 동적테이블 채우기
			for (int i = 3; i <= 12; i++) {
				// 전 달 최소 비용 + 현 달 최소 비용
				int min = D[i - 1] + Math.min(ticket[0] * table[i], ticket[1]);
				
				min = Math.min(min, D[i - 3] + ticket[2]); // 윗 줄에서 구헌 최솟값과 3달
				D[i] = min;
			}

			/**
			 * 3. 정답 출력
			 */
			min = Math.min(min, D[12]);
			sb.append(min).append("\n");
		}

		System.out.println(sb);

	}

}
