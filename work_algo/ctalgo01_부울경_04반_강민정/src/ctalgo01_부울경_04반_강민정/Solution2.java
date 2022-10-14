package ctalgo01_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Solution2 {

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
			dfs(0, 0);

			/**
			 * 3. 정답 출력
			 */
			sb.append(min).append("\n");
		}

		System.out.println(sb);

	}

	private static void dfs(int month, int sum) {
		if(min <= sum)
			return;
		if(month > 12) {
			min = Math.min(min, sum);
			return;
		}
		
		if(table[month] == 0) {
			dfs(month + 1, sum);
		}
		else {
			dfs(month + 1, sum + ticket[0] * table[month]);
			
			dfs(month + 1, sum + ticket[1]);
			
			dfs(month + 3, sum + ticket[2]);
		}
	}

}
