
import java.io.*;
import java.util.*;

public class Solution2 {

	private static int N;
	private static List<Position> customerList;
	private static List<Position> monsterList;
	private static boolean[] monsterVisited;
	private static boolean[] customerVisited;
	private static int min;

	private static class Position {
		int x;
		int y;
		int number;

		Position(int x, int y, int number) {
			this.x = x;
			this.y = y;
			this.number = number;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			
			sb.append("#" + tc + " ");

			N = Integer.parseInt(in.readLine());

			min = Integer.MAX_VALUE;
			
			monsterList = new ArrayList<>();
			customerList = new ArrayList<>();

			for (int i = 1; i <= N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 1; j <= N; j++) {
					int n = Integer.parseInt(split[j - 1]);
					if (n > 0)
						monsterList.add(new Position(i, j, n));
					else if (n < 0)
						customerList.add(new Position(i, j, n));
				}
			}

			monsterVisited = new boolean[monsterList.size() + 1];
			customerVisited = new boolean[customerList.size() + 1];

			DFS(0, 0, 1, 1);
			
			sb.append(min).append("\n");

		}
		System.out.println(sb);

	}

	private static void DFS(int cnt, int sum, int x, int y) {

		if (sum >= min) {
			return;
		}

		if (cnt == monsterList.size() * 2) {
			min = Math.min(min, sum);
			return;
		}

		for (Position monster : monsterList) {

			if (monsterVisited[monster.number])
				continue;

			int d = getDistance(monster.x, monster.y, x, y);

			monsterVisited[monster.number] = true;
			DFS(cnt + 1, sum + d, monster.x, monster.y);
			monsterVisited[monster.number] = false;

		}

		for (Position customer : customerList) {
			int n = Math.abs(customer.number);
			
			if(customerVisited[n] || !monsterVisited[n]) continue;
			
			int d = getDistance(customer.x, customer.y, x, y);
			
			customerVisited[n] = true;
			DFS(cnt + 1, sum + d, customer.x, customer.y);
			customerVisited[n] = false;
			

		}

	}

	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}
