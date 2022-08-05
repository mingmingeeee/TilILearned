package com.ssfay.hw.problem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main2 {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		// 카드 갯수
		int N = Integer.parseInt(in.readLine());

		/**
		 * 2. 알고리즘 풀기
		 */
		// 1번 카드가 제일 위에, N번 카드가 제일 아래인 상태로 놓는다.
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		// 마지막 한 장이 남을 때까지 반복
		while(queue.size() > 1) {
			// 제일 위에 있는 카드는 버린다.
			queue.poll();
			
			// 그 다음, 제일 위에 있는 카드는 제일 아래로 옮긴다.
			int topCard = queue.poll();
			queue.offer(topCard);
		}

		/**
		 * 3. 정답 출력
		 */
		int answer = queue.poll();
		sb.append(answer);
		
		System.out.println(sb);
	}

}
