package baekjoon;

import java.io.*;
import java.util.*;

// 무방향 그래프
// 두 선거구로 나누었을 때 두 선거구의 인구 차이 최솟값 출력
// 나눌 수 없는 경우는 -1 출력
// 0 <= N <= 10

public class B_17471 {

	private static int N;
	private static int[] population;
	private static int[][] adjMatrix;
	private static boolean[] isSelected;
	private static int[] area1;
	private static int[] area2;
	private static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 구역의 개수
		N = Integer.parseInt(in.readLine());

		String[] split = in.readLine().split(" ");

		// 구역 인구 수
		population = new int[N];
		for (int i = 0; i < N; i++)
			population[i] = Integer.parseInt(split[i]);

		adjMatrix = new int[N][N];

		// 각 구역과 인접한 구역 정보
		// 1. 구역과 인접한 구역의 수, 2. 인접한 구역의 번호
		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");

			int n = Integer.parseInt(split[0]);

			for (int j = 0; j < n; j++) {
				int to = Integer.parseInt(split[j + 1]) - 1;
				adjMatrix[i][to] = 1;
			}
		}

		// 조합으로 뽑고
		// 구역 가능한지 확인하고 가능하다면, 그룹의 인구 차이 최소 되는 것 뽑기 -> answer 계속 갱신
		for (int i = 1; i <= N / 2; i++) {
			area1 = new int[i];
			area2 = new int[N - i];
			isSelected = new boolean[N];
			comb(0, 0, i);
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(adjMatrix[i][j] + " ");
//			}
//			System.out.println();
//		}

		if (answer == Integer.MAX_VALUE)
			answer = -1;
		sb.append(answer);
		System.out.println(sb);

	}

	private static boolean bfs(int[] area) {
		List<Integer> list = new ArrayList<>();
		for (int a : area)
			list.add(a);
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(list.get(0));
		list.remove(0);

		while (!q.isEmpty()) {

			int p = q.poll();

			for (int i = list.size() - 1; i >= 0; i--) {
				if (adjMatrix[p][list.get(i)] == 1) {
					q.offer(list.get(i));
					list.remove(i);
				}
			}

		}
		if (list.size() == 0)
			return true;

		return false;

	}

	private static void comb(int cnt, int start, int R) {

		if (cnt == R) {

			int j = 0;
			for (int i = 0; i < N; i++) {
				if (!isSelected[i])
					area2[j++] = i;
			}

			// 구역 가능한지 검사1
			if (!bfs(area1))
				return;
			int sum1 = 0;
			for (int i = 0; i < R; i++)
				sum1 += population[area1[i]];

			// 구역 가능한지 검사2
			if (!bfs(area2))
				return;

			int sum2 = 0;
			for (int i = 0; i < N - R; i++)
				sum2 += population[area2[i]];

			answer = Math.min(answer, Math.abs(sum1 - sum2));

//			System.out.println("=========" + R);
//			System.out.println(Arrays.toString(area1));
//			System.out.println(Arrays.toString(area2));
			return;
		}

		for (int i = start; i < N; i++) {
			area1[cnt] = i;
			isSelected[i] = true;
			comb(cnt + 1, i + 1, R);
			isSelected[i] = false;
		}
	}

}
