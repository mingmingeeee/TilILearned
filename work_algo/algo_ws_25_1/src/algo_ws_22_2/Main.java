package algo_ws_22_2;

import java.io.*;
import java.util.*;

public class Main {

	private static int R;
	private static int C;
	private static int M;
	// 위, 아래, 오른쪽, 왼쪽
	private static int[] dr = { 0, -1, 1, 0, 0 };
	private static int[] dc = { 0, 0, 0, 1, -1 };

	private static List<Shark> sharks = new ArrayList<>();

	private static Shark[][] map;

	private static class Shark {
		int r, c, s, d, z;
		char alpha;

		public Shark(int r, int c, int s, int d, int z, char alpha) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
			this.alpha = alpha;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		M = Integer.parseInt(split[2]);

		for (int i = 0; i < M; i++) {
			split = in.readLine().split(" ");

			Shark shark = new Shark(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]),
					Integer.parseInt(split[3]), Integer.parseInt(split[4]), (char) ('A' + i));
			sharks.add(shark);

		}

		int position = 1;
		while (position <= C) {
			map = new Shark[R + 1][C + 1];

			int ground = Integer.MAX_VALUE;
			int index = 0;

			// 2. 낚시왕이 있는 열에 있는 상어 중 땅과 가장 가까운 상어 잡음 => 잡고 격자판에서 지움
			for (int i = 0; i < sharks.size(); i++) {
				if (sharks.get(i).c == position) {
					if (ground > sharks.get(i).r) {
						ground = sharks.get(i).r;
						index = i;
					}
				}
			}

			if (ground != Integer.MAX_VALUE) {
				answer += sharks.get(index).z;
				sharks.remove(index);
			}


			// 3. 상어 이동
			for (Shark shark : sharks) {
				int move = 0;
				if (shark.d == 1 || shark.d == 2) {
					move = shark.s % ((R - 1) * 2); // R-1 * 2 번 움직이면 방향 그대로인 채로 처음 자리로 돌아옴
				} else {
					move = shark.s % ((C - 1) * 2);
				}
				
				for (int i = 0; i < move; i++) {
					int testC = shark.c + dc[shark.d];
					int testR = shark.r + dr[shark.d];
					if (!isRange(testC, testR)) {
						if (shark.d == 1)
							shark.d = 2;
						else if (shark.d == 2)
							shark.d = 1;
						else if (shark.d == 3)
							shark.d = 4;
						else if (shark.d == 4)
							shark.d = 3;
					}
					shark.r = shark.r + dr[shark.d];
					shark.c = shark.c + dc[shark.d];
				}

			}

			// 한 칸에 상어가 두 마리 이상 있을 경우 크기 가장 큰 상어가 나머지 상어를 다 먹는다.
			for (int i = sharks.size() - 1; i >= 0; i--) {
				if (map[sharks.get(i).r][sharks.get(i).c] != null
						&& map[sharks.get(i).r][sharks.get(i).c].z >= sharks.get(i).z) {
					sharks.remove(i);
				} else {
					if (map[sharks.get(i).r][sharks.get(i).c] != null) {
						for (int k = sharks.size() - 1; k >= 0; k--) {
							if (map[sharks.get(i).r][sharks.get(i).c].alpha == sharks.get(k).alpha) {
								sharks.remove(k);
								break;
							}
						}
					}
					map[sharks.get(i).r][sharks.get(i).c] = sharks.get(i);
				}
			}

			position++;
		}
		System.out.println(answer);

	}

	private static int answer = 0;

	private static boolean isRange(int c, int r) {
		if (c > 0 && c <= C && r > 0 && r <= R)
			return true;
		return false;
	}

}