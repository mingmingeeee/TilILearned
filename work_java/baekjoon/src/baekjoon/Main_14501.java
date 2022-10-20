package baekjoon;

import java.io.*;
import java.util.*;

// 백준이 퇴사
// N + 1일째 되는 날 퇴사 => 남은 N일 동안 최대한 많은 상담 하려고 ...
// 하루 하나씩 서로 다른 사람의 상담 잡아놓음
// T: 상담 완료하는데 걸리는 기간
// P: 상담했을 때 받을 수 있는 금액
// 상담 적절히 했을 때 백준이가 얻을 수 있는 최대 수익 구하기

// 완탐 => DFS하면 될듯???]

public class Main_14501 {

	private static List<Node> list = new ArrayList<>();
	private static int N;
	private static int max;

	private static class Node {
		int day, price;

		public Node(int day, int price) {
			super();
			this.day = day;
			this.price = price;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			int day = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());

			list.add(new Node(day, price));
		}

		dfs(0, 0);

		sb.append(max).append("\n");
		System.out.println(sb);
	}

	private static void dfs(int cnt, int sum) {
		if(cnt > N)
			return;
		
		if (cnt == N) {
			max = Math.max(max, sum);
			return;
		}

		int day = list.get(cnt).day;
		int price = list.get(cnt).price;

		// 상담 하기
		dfs(cnt + day, sum + price);

		// 안 하기
		dfs(cnt + 1, sum);
	}

}
