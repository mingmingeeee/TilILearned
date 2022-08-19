package algo_ws_15_1;

import java.io.*;
import java.sql.Array;
import java.util.*;

public class Solution {

	private static int N; // N개의 무게 추
	private static int[] input; // 순열을 이용해 N개의 무게 추를 줄세운 하나의 경우
	private static int answer; // 오른쪽 위에 올라가있는 무게의 총합이 왼족에 올라가 있는 무게의 총합보다 커지지 않는 경우의 수
	// 왼쪽 >= 오른쪽

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			// N개의 무게 추
			N = Integer.parseInt(in.readLine());

			// 무게 추를 input 배열에 담기
			String[] split = in.readLine().split(" ");
			input = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(split[i]);
			}

			// 정답 초기화
			answer = 0;

			Arrays.sort(input);
			do {
				put(0, 0, 0);
			} while (np(input));

			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	// index: input 배열의 인덱스 번호
	// left: 왼쪽 저울의 무게 추 총 합
	// right: 오른쪽 저울의 무게 추 총 합
	private static void put(int index, int left, int right) {

		// 기저 부분
		if (index == N) { // 끝까지 가면 종료
			// 여기까지 온 경우는 왼쪽 올라가 있는 무게의 총합이
			// 오른쪽 보다 크거나 같은 경우이므로 정답 카운트 1 증가
			answer++;
			return;
		}

		// 유도 부분

		// index에 해당하는 무게 추를 왼쪽 저울에 올리고
		// 다음 무게 추를 처리하러 가기
		put(index + 1, left + input[index], right);

		// 왼쪽 >= 오른쪽
		// 왼쪽에 있는 무게 추를 하나씩 오른쪽으로 옮기는데
		// 왼쪽에 올려진 추들의 무게 합이 오른쪽보다 크거나 같은 경우만 오른쪽 저울에 무게 추를 올린다.
		if (left >= right + input[index]) {
			put(index + 1, left, right + input[index]);
		}

	}

	private static boolean np(int[] data) {

		int N = data.length;

		int i = N - 1;
		while (i > 0 && data[i - 1] >= data[i]) {
			--i;
		}

		if (i == 0)
			return false;

		int j = N - 1;
		while (data[i - 1] >= data[j]) {
			--j;
		}
		swap(data, i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(data, i++, k--);
		}

		return true;

	}

	private static void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}

}
