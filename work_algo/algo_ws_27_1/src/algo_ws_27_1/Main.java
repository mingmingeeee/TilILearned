package algo_ws_27_1;

import java.io.*;
import java.util.*;

public class Main {

	private static int N;
	private static boolean[] isSelected;
	private static int[][] area;
	private static int[] population;

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());

		isSelected = new boolean[N + 1];

		// 구역별 인구
		population = new int[N + 1];
		String[] split = in.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(split[i - 1]);
		}

		// 각 구역과 인접한 구역의 정보
		area = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			split = in.readLine().split(" ");
			int n = Integer.parseInt(split[0]);
			for (int j = 1; j <= n; j++) {
				int pos = Integer.parseInt(split[j]);
				area[i][pos] = 1;
			}
		}

		// 구역을 두 개의 선거구로 나눠야 함
		// 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 함
		subset(0);
		
		min = min == Integer.MAX_VALUE ? -1 : min;
		
		System.out.println(min);

	}

	private static int min = Integer.MAX_VALUE;

	private static boolean bfs(List<Integer> a) {

		if(a.size() == 0)
			return false;
		
		Queue<Integer> q = new ArrayDeque<>();

		q.offer(a.get(0));
		a.remove(0);

		while (!q.isEmpty()) {

			int size = q.size();

			for (int i = 0; i < size; i++) {

				int cur = q.poll();

				for (int d = a.size() - 1; d >= 0; d--) {
					if (area[cur][a.get(d)] == 1) {
						q.offer(a.get(d));
						a.remove(d);
					}
				}

			}

		}

		if (a.size() == 0)
			return true;

		return false;

	}

	private static void subset(int idx) {
		if (idx == N) {
			List<Integer> area1 = new ArrayList<>();
			List<Integer> area2 = new ArrayList<>();

			// 구역 나누기
			for (int i = 1; i <= N; i++) {
				if (isSelected[i]) {
					area1.add(i);
				} else {
					area2.add(i);
				}
			}
			

			// 구역 검사 1
			if (!bfs(area1))
				return;

			// 구역 검사 2
			if (!bfs(area2))
				return;

			for (int i = 1; i <= N; i++) {
				if (isSelected[i]) {
					area1.add(i);
				} else {
					area2.add(i);
				}
			}

			min = Math.min(min, count(area1, area2));

			return;
		}

		isSelected[idx] = true;
		subset(idx + 1);
		isSelected[idx] = false;
		subset(idx + 1);
	}

	private static int count(List<Integer> area1, List<Integer> area2) {
		int sum1 = 0;
		int sum2 = 0;
		
		for (int a : area1) {
			sum1 += population[a];
		}
		for (int a : area2) {
			sum2 += population[a];
		}

		return Math.abs(sum1 - sum2);
	}

}
