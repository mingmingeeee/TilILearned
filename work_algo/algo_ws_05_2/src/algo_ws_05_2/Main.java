package algo_ws_05_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// List -> subList(p1, p2);, addAll(p1, p2);

public class Main {

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
		List<Integer> poo = new ArrayList<>();

		String[] s = in.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int K = Integer.parseInt(s[1]);

		/**
		 * 2. 알고리즘 풀기
		 */
		for (int i = 1; i <= N; i++) {
			poo.add(i);
		}

		int q = 0;
		sb.append("<");
		while (poo.size() > 1) {
			q += K-1;
			while(q>=poo.size())
				q = q - poo.size();
			sb.append(poo.remove(q) + ", ");
		}
		if(q>=poo.size())
			q = q - poo.size();
		sb.append(poo.remove(q) + ">");
		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);
	}

}
