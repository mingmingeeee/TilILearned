
import java.io.*;
import java.util.*;

public class No3_2 {

	// 1. 카드를 입력한 값에 따라 셔플하는 기능
	// 2. 카드를 왼쪽 카드와 오른쪽 카드로 나누는 기능
	// 3. 정렬이 제대로 이루어졌는지 확인하는 기능
	// 4. 모든 경우의 수를 탐색하는 기능

	private static int N;
	private static int min;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {

			sb.append("#" + tc + " ");

			N = Integer.parseInt(in.readLine());

			int[] cards = new int[N];

			String[] split = in.readLine().split(" ");

			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(split[i]);
			}

			DFS(0, cards);

			if (min == Integer.MAX_VALUE)
				min = -1;

			sb.append(min).append("\n");

		}

		System.out.println(sb);

	}

	private static boolean isSorted(int[] cards) {
		boolean isUp = true;
		boolean isDown = true;
		for (int i = 0; i < N; i++) {
			if (cards[i] != i + 1)
				isUp = false;
			if (cards[i] != N - i)
				isDown = false;

			if (!isUp && !isDown)
				return false;
		}
		return true;
	}

	private static void DFS(int cnt, int[] cards) {
		if (cnt > 5)
			return;
		if (cnt >= min)
			return;

		// 1. 오름차순, 내림차순인지 검사
		if (isSorted(cards)) {
			min = Math.min(min, cnt);
			return;
		}

		// 2. 셔플 위해 카드 나누기
		int[] left = new int[N / 2];
		int[] right = new int[N / 2];

		for (int i = 0; i < N / 2; i++) { // 왼쪽
			left[i] = cards[i];
		}
		for (int i = N / 2; i < N; i++) { // 오른쪽
			right[i - N / 2] = cards[i];
		}

		// 3. 카드 셔플
		for (int x = 1; x < N; x++) {
			int[] next = x < N / 2 ? shuffle(x, left, right) : shuffle(x - N / 2, right, left);
			// x 가 N/2보다 크면 카드 뭉치 순서를 바꾼 뒤 셔플 -> 반만 해주면 되기 때문
			DFS(cnt + 1, next);
		}

	}

	// 일정 횟수만큼 왼쪽 반복하고
	// 왼쪽 다 섞을 때까지 오른쪽, 왼쪽 순서 반복 -> 남은 오른쪽 카드를 다 붙이는 식으로 구현함
	// 1. 일정 횟수 (N/2 - x)만큼 L이 반복
	// 2. L을 모두 섞을 때까지 R, L, R, L 을 반복
	// 3. 남은 R을 모두 가져다 붙임

	// 셔플된 카드 반환
	// x: 섞는 시점
	private static int[] shuffle(int x, int[] left, int[] right) {
		int[] next = new int[N];
		int idx = 0;
		int leftIdx = 0;
		int rightIdx = 0;

		while (leftIdx < N / 2 - x) { // N/2 - x -> 왼쪽 카드 배치
			next[idx++] = left[leftIdx++];
		}

		int order = 0; // 순서 배치 기억하기 위한 변수
		// 짝수인지 홀수인지에 따라 왼쪽인지 오른쪽인지 ? 배치

		while (leftIdx < N / 2 && rightIdx < N / 2) { // 둘 중 하나가 다 섞일 때까지 번갈아가면서 오른쪽, 왼쪽 순서대로 카드 배치
			next[idx++] = order % 2 == 0 ? right[rightIdx++] : left[leftIdx++];
			order++;
		}

		while (rightIdx < N / 2) { // 남은 오른쪽 카드 모두 결과 배열에 넣음
			next[idx++] = right[rightIdx++];
		}

		return next; // 셔플에 끝남
	}

}
