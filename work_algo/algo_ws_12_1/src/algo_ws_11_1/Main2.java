package algo_ws_11_1;

import java.io.*;
import java.util.*;

// numbers의 칸 2개에 사용 개수를 담음
// 1667H2 -> 1667 * 1667 
// 그리디로 안 되는 것은 완전 탐색으로!

public class Main2 {

	private static int N; // 배달해야 할 설탕 무게 (킬로그램)
	private static int[] bags; // 봉지의 종류
	private static int answer; // 봉지의 최소 개수

	private static int[] numbers; // 각 봉지 종류마다 봉지 개수 저장 (준복 순열에서 사용)

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 배달해야 할 설탕 무게 (킬로그램)
		N = Integer.parseInt(in.readLine());

		// 봉지 종류
		bags = new int[] { 5, 3, };
		numbers = new int[bags.length];

		// 봉지의 최소 개수 초기화
		answer = Integer.MAX_VALUE;

		// 중복 순열로 풀기
		perm(0);
		
		if(answer == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(answer);

	}

	// cnt: 현재까지 고려한 원소 수
	private static void perm(int cnt) {

		if (cnt == bags.length) {

			// 원본 손실을 막기 위해 값 복사
			int sugar = N;
			for (int i = 0; i < bags.length; i++) {
				// 남은 설탕 = 현재 남은 설탕 - (봉지의 종류 * 중복 순열로 뽑은 봉지 수)
				sugar = sugar - (bags[i] * numbers[i]);
			}

			// 정확하게 N킬로그램을 만들 수 있는 경우
			if (sugar == 0) {

				// 사용한 봉지 개수
				int bagCnt = 0;
				for (int i = 0; i < bags.length; i++) {
					bagCnt += numbers[i];
				}

				if (bagCnt < answer) {
					answer = bagCnt;
				}

			}

			return;
		}

		// 유도 부분
		// 1667 = 최도 5000 킬로그램 / 3 킬로그램 봉지
		// 최악의 경우의 수가 1667 * 1667 = 2,778,889 이므로 1초 안에 충분히 수행 가능
		for (int i = 0; i <= 1667; i++) {
			numbers[cnt] = i;
			perm(cnt + 1); // 중복 순열
		}

	}

}
