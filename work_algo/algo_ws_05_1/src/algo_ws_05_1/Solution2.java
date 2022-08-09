package algo_ws_05_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2 {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 2. 알고리즘 풀기
		 */
		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			/**
			 * 1. 입력 파일 객체화
			 */
			// 원본 암호문의 길이 N
			int N = Integer.parseInt(in.readLine());

			// 원본 암호문
			String[] split = in.readLine().split(" ");
			List<String> answer = new ArrayList<>(split.length);
			for (int i = 0; i < split.length; i++) {
				answer.add(split[i]);
			}

			// 명령어의 개수
			int CN = Integer.parseInt(in.readLine());

			// 명령어
			split = in.readLine().split(" ");
			List<String> commands = new ArrayList<>(split.length);
			for (int i = 0; i < split.length; i++) {
				commands.add(split[i]);
			}

			
			int cursor = -1; // 명령어를 해석할 인덱스 번호
			for (int i = 0; i < CN; i++) { // 명령어의 개수만큼 반복 실행
				String type = commands.get(++cursor);
				switch (type) {
				case "I": // 삽입
					int x = Integer.parseInt(commands.get(++cursor));
					int y = Integer.parseInt(commands.get(++cursor));
					++cursor; // 삽입할 숫자 덩어리 처음 가리킴

					// y개의 숫자를 x의 위치 바로 다음에 삽입
					List<String> subList = commands.subList(cursor, cursor + y);
					answer.addAll(x, subList); // x위치 다음에 subList 다 넣기
					cursor += y - 1; // 명령어의 커서를 y개의 숫자만큼 이동
				}
			}

			/**
			 * 3. 정답 출력
			 */

			for (int i = 0; i < 10; i++) {
				sb.append(answer.get(i));
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
