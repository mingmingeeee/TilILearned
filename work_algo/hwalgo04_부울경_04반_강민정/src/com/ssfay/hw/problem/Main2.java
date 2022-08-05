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
		 * 0. �Է����� �о���̱�
		 */
		// System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// ����� �� ���� ����ϱ� ���� StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. �Է� ���� ��üȭ
		 */
		// ī�� ����
		int N = Integer.parseInt(in.readLine());

		/**
		 * 2. �˰��� Ǯ��
		 */
		// 1�� ī�尡 ���� ����, N�� ī�尡 ���� �Ʒ��� ���·� ���´�.
		Queue<Integer> queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			queue.offer(i);
		}
		
		// ������ �� ���� ���� ������ �ݺ�
		while(queue.size() > 1) {
			// ���� ���� �ִ� ī��� ������.
			queue.poll();
			
			// �� ����, ���� ���� �ִ� ī��� ���� �Ʒ��� �ű��.
			int topCard = queue.poll();
			queue.offer(topCard);
		}

		/**
		 * 3. ���� ���
		 */
		int answer = queue.poll();
		sb.append(answer);
		
		System.out.println(sb);
	}

}
