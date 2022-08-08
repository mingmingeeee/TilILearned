package algo_ws_05_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// List -> subList(p1, p2);, addAll(p1, p2);

public class Solution {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		/**
		 * 1. 입력 파일 객체화
		 */
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			int N1 = Integer.parseInt(in.readLine());
			List<String> password = new ArrayList<>();
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 0; i < N1; i++) {
				password.add(st.nextToken());
			}

			int N2 = Integer.parseInt(in.readLine());
			st = new StringTokenizer(in.readLine(), " ");

			/**
			 * 2. 알고리즘 풀기
			 */

			for (int i = 0; i < N2; i++) {
				char l = st.nextToken().charAt(0);
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				List<String> pass_front = password.subList(0, x);

				for (int j = 0; j < y; j++) {
					pass_front.add(st.nextToken());
				}
			}

			for (int i = 0; i < 10; i++) {
				sb.append(password.get(i));
				sb.append(" ");
			}
			sb.append("\n");
		}

		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);
	}

}
