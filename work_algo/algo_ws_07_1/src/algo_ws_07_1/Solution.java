package algo_ws_07_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
// 연산 => stack에 숫자 넣어놓고 연산자 만나면 나와서 계산함 = 단말 => 숫자, 중간 => 연산자
// 후위 => 좌, 우, 부 

public class Solution {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			/**
			 * 1. 입력 파일 객체화
			 */
			sb.append("#" + test_case + " ");
			int num = Integer.parseInt(in.readLine());

			int result = 1;
			
			/**
			 * 2. 알고리즘 풀기
			 */
			for (int i = 0; i < num; i++) {
				String[] strings = in.readLine().split(" ");
				
				if(strings.length > 2 && strings[1].charAt(0) != '-' && 
						strings[1].charAt(0) != '*' && strings[1].charAt(0) != '/'
						&& strings[1].charAt(0) != '+') {
					result = 0;
				} else if(strings.length == 2 && strings[1].charAt(0) == '-' && 
						strings[1].charAt(0) == '*' && strings[1].charAt(0) == '/'
						&& strings[1].charAt(0) == '+') {
					result = 0;
				}
			}
			


			sb.append(result).append("\n");

		}

		/**
		 * 3. 정답 출력
		 */

		System.out.println(sb);
	}

}
