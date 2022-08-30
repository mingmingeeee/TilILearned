
import java.io.*;
import java.util.*;

public class No2_2 {

	// 완전 탐색 - DFS
	/**
	 * 
	 * void DFS(방문한 개수, 현재까지의 시간 합, 현재 위치R, 현재 위치 C{
	 * 
	 * if 현재까지의 거리 >= 최소 거리 return if 모든 고객, 몬스터를 다 방문했다면 최소 시간 갱신 후 return
	 * 
	 * 1. 아직 방문하지 않은 몬스터를 DFS(개수 + 1, 늘어난 시간, 몬스터 R, 몬스터 C) 2. 아직 방문하지 않은 고객을 DFS(개수
	 * + 1, 늘어난 시간, 고객 R, 고객 C)
	 * 
	 * }
	 *
	 */

	static int N;
	static ArrayList<Node> monsters;
	static ArrayList<Node> customers;
	static boolean[] visitedMonsters;
	static boolean[] visitedCustomers;
	static int min; // 최소 시간 (정답)

	static class Node {

		int r;
		int c;
		int n; // 고객, 몬스터 번호

		public Node(int r, int c, int n) {
			this.r = r;
			this.c = c;
			this.n = n;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(in.readLine());

			monsters = new ArrayList<>();
			customers = new ArrayList<>();
			
			int count = 0;

			for (int i = 1; i <= N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 1; j <= N; j++) {
					int n = Integer.parseInt(split[j - 1]);
					if (n > 0) {
						monsters.add(new Node(i, j, n));
						count++;
					}
					else if (n < 0)
						customers.add(new Node(i, j, n));
				}
			}
			
			visitedMonsters = new boolean[count + 1];
			visitedCustomers = new boolean[count + 1];

			min = Integer.MAX_VALUE;

			DFS(0, 0, 1, 1);

			sb.append(min).append("\n");

		}

		System.out.println(sb);

	}

	private static void DFS(int cnt, int distance, int r, int c) {

		if (distance >= min)
			return;

		if (cnt == monsters.size() * 2) {
			min = Math.min(min, distance);
		}

		// 1. 몬스터 잡기
		for (Node monster : monsters) {
			if (visitedMonsters[monster.n])
				continue;

			// 아직 잡지 않은 몬스터가 있다면, 거리를 구한 다음
			int d = getDistance(monster.r, monster.c, r, c);

			// 방문 체크 한다.
			visitedMonsters[monster.n] = true;
			// 그 다음 그 몬스터 위치로 넘어가서 DFS 탐색 진행
			DFS(cnt + 1, distance + d, monster.r, monster.c);
			visitedMonsters[monster.n] = false;
		}

		// 2. 고객 방문
		for (Node customer : customers) {
			int n = Math.abs(customer.n);
			if (visitedCustomers[n] || !visitedMonsters[n])
				continue;

			// 아직 방문하지 않은 고객 중에서 몬스터를 잡은 고객만 거리를 구한 다음
			int d = getDistance(customer.r, customer.c, r, c);
			visitedCustomers[n] = true; // 방문 체크
			// 고객 위치로 이동해서 DFS 탐색 진행
			DFS(cnt + 1, distance + d, customer.r, customer.c);
			visitedCustomers[n] = false;
		}

	}

	private static int getDistance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}

}
