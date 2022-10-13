package SWEA;

import java.io.*;
import java.util.*;

// 모든 사용자가 총 충전한 양의 합의 최댓값

public class S_5644 {

	// 이동 방향
	private static final int[] dx = { 0, -1, 0, 1, 0 };
	private static final int[] dy = { 0, 0, 1, 0, -1 };

	private static int M;
	private static int A;

	private static User a;
	private static User b;

	private static int[] userA_d;
	private static int[] userB_d;
	private static Battery[] batteries;

	private static class User {
		int x;
		int y;

		public User(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	private static class Battery {
		int x, y, c, p;

		public Battery(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(in.readLine().trim());

		for (int tc = 1; tc <= TC; ++tc) {

			sb.append("#" + tc + " ");

			////////// 입력 //////////
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 총 이동 시간
			A = Integer.parseInt(st.nextToken()); // BC의 개수

			userA_d = new int[M + 1];
			userB_d = new int[M + 1];

			a = new User(1, 1); // (1, 1)에서 시작
			b = new User(10, 10); // (10, 10)에서 시작

			// 사용자 A 이동 정보
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				userA_d[i] = Integer.parseInt(st.nextToken());
			}

			// 사용자 B 이동 정보
			st = new StringTokenizer(in.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				userB_d[i] = Integer.parseInt(st.nextToken());
			}

			// BC 정보
			batteries = new Battery[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				batteries[i] = new Battery(y, x, c, p);
			}
			////////// 입력 //////////

			int sum = 0;
			////////// 알고리즘 시작 //////////
			for (int k = 0; k <= M; k++) {
				// 유저들이 움직일 때마다 충전량 비교
				// 이동한 거리
				a = new User(a.x + dx[userA_d[k]], a.y + dy[userA_d[k]]);
				b = new User(b.x + dx[userB_d[k]], b.y + dy[userB_d[k]]);

				int[] ap = new int[A];
				int[] bp = new int[A];

				for (int i = 0; i < A; i++) {
					int d_a = distance(a.x, a.y, batteries[i].x, batteries[i].y);
					int d_b = distance(b.x, b.y, batteries[i].x, batteries[i].y);

					int c = batteries[i].c;
					int p = batteries[i].p;
					if (d_a <= c) {
						ap[i] = p;
					}
					if (d_b <= c) {
						bp[i] = p;
					}
				}

				int max = Integer.MIN_VALUE;
				for (int i = 0; i < A; i++) {
					for (int j = 0; j < A; j++) {
						if(i == j && ap[i] == bp[j]) {
							max = Math.max(max, ap[i]);
						}
						else {
							max = Math.max(max, ap[i] + bp[j]);
						}
					}
				}

				sum += max;
			}

			sb.append(sum).append("\n");
		}
		System.out.println(sb);
	}
	// 10 10 7 10 3

	// 맨하튼 거리
	private static int distance(int x1, int y1, int x2, int y2) {

		return Math.abs(x1 - x2) + Math.abs(y1 - y2);

	}

}
