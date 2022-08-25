import java.io.*;
import java.util.*;

public class No2 {

	private static int N;
	private static int[][] map;
	private static List<Position> list;
	private static Position[] perm;
	private static Position[] tmp;
	private static int killer_x = 0;
	private static int killer_y = 0;
	private static int min;

	private static boolean[] isSelected;

	private static int[] dx = { 0, 0, 1, -1 };
	private static int[] dy = { 1, -1, 0, 0 };

	private static class Position {
		int number;
		int x;
		int y;

		public Position(int number, int x, int y) {

			this.number = number;
			this.x = x;
			this.y = y;

		}

		@Override
		public String toString() {
			return "Position [number=" + number + ", x=" + x + ", y=" + y + "]";
		}

	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			min = Integer.MAX_VALUE;

			N = Integer.parseInt(in.readLine()); // 낚시터 자리 개수
			map = new int[N][N];

			// 양수: 몬스터
			// 음수: 고객
			// 헌터는 항상 (1, 1)

			// 헌터는 몬스터를 죽이면 고객에게 갈 수 있음

			list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
					if (map[i][j] != 0) {
						list.add(new Position(map[i][j], i, j));
					}
				}
			}

			isSelected = new boolean[list.size()];

			perm = new Position[list.size()];
			for (int i = 0; i < list.size(); i++) {
				perm[i] = list.get(i);
			}

			tmp = new Position[list.size()];
			permutation(0);
			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}

	private static void permutation(int cnt) {
		if (cnt == list.size()) {

			boolean[] isKilled = new boolean[list.size() / 2];

			int distance = 0;
			int x = killer_x;
			int y = killer_y;

			for (int i = 0; i < list.size(); i++) {

				if (tmp[i].number > 0) { // 몬스터라면
					isKilled[tmp[i].number - 1] = true;
					distance += Math.abs(x - tmp[i].x) + Math.abs(y - tmp[i].y);
					x = tmp[i].x;
					y = tmp[i].y;
				} else if (tmp[i].number < 0) { // 고객이라면
					if (isKilled[Math.abs(tmp[i].number) - 1]) { // 앞에서 내 번호 몬스터를 죽였다면
						distance += Math.abs(x - tmp[i].x) + Math.abs(y - tmp[i].y);
						x = tmp[i].x;
						y = tmp[i].y;
					} else // 아니라면 중단
						return;
				}

			}

			min = Math.min(min, distance);

			return;
		}

		for (int i = 0; i < list.size(); i++) {
			if (isSelected[i])
				continue;
			tmp[cnt] = perm[i];
			isSelected[i] = true;
			permutation(cnt + 1);
			isSelected[i] = false;

		}
	}

}