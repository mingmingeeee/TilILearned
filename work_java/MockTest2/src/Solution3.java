
import java.io.*;
import java.util.*;

public class Solution3 {

	private static int N;
	private static int[] cards;
	private static int min;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(in.readLine());
			min = Integer.MAX_VALUE;
			
			cards = new int[N];

			String[] split = in.readLine().split(" ");
			for (int i = 0; i < N; i++) {
				cards[i] = Integer.parseInt(split[i]);
			}
			
			DFS(0, cards);
			
			if(min == Integer.MAX_VALUE)
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

		if (cnt > 5) {
			return;
		}

		if (cnt >= min) {
			return;
		}

		if (isSorted(cards)) {
			min = Math.min(min, cnt);
			return;
		}

		int[] left = new int[N / 2];
		int[] right = new int[N / 2];

		for (int i = 0; i < N / 2; i++) {
			left[i] = cards[i];
		}
		for (int i = N / 2; i < N; i++) {
			right[i - N / 2] = cards[i];
		}

		for (int x = 1; x < N; x++) {
			int[] next;
			if (x < N / 2) {
				next = shuffle(x, left, right);
			} else {
				next = shuffle(x - N / 2, right, left);
			}
			DFS(cnt + 1, next);
		}

	}

	private static int[] shuffle(int x, int[] left, int[] right) {

		int[] next = new int[N];
		int idx = 0;
		int leftIdx = 0;
		int rightIdx = 0;

		while (leftIdx < N / 2 - x) {
			next[idx++] = left[leftIdx++];
		}

		int order = 0;

		while (leftIdx < N / 2 && rightIdx < N / 2) {
			if (order % 2 == 0)
				next[idx++] = right[rightIdx++];
			else
				next[idx++] = left[leftIdx++];
			order++;
		}

		while (rightIdx < N / 2) {
			next[idx++] = right[rightIdx++];
		}

		return next;

	}

}
