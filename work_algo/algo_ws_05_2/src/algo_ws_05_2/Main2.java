package algo_ws_05_2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

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
		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);

		/**
		 * 2. 알고리즘 풀기
		 */
		// 1번부터 N번까지 사람 리스트에 넣기
		List<Integer> list = new ArrayList<>(N);
		for (int i = 1; i <= N; i++) {
			list.add(i);
		}

		sb.append("<");

		int index = 0;
		// N명의 사람이 모두 제거될 때까지 반복
		while (!(list.size() == 1)) {
			// K번째 사람 인덱스 번호 구하기
			index = (index + K - 1) % list.size();
			
			// K번째 사람의 인덱스 번호를 이용하여, 그 사람의 번호 구하고 출력
			int no = list.get(index);
			sb.append(no).append(", ");
			
			// K번째 사람 리스트에서 제거
			list.remove(index);
		}

		// 마지막 사람 출력
		sb.append(list.get(0)).append(">");
		/**
		 * 3. 정답 출력
		 */

		System.out.println(sb);
	}

}
