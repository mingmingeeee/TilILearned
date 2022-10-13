package algo_ws_29_1;

import java.io.*;
import java.util.*;

// 제일 가까운 정점이 제일 먼저 도착하니까 횟수는 제일 멀리 있는 정점에 달려있음
// 1. 모든 정점의 거리가 홀수 혹은 짝수여야 함
// 2. 제일 먼 거리의 정점의 이동 횟수 구해야 함

public class Solution {

	private static class Point {
		int x;
		int y;
		int distance; // 원점과의 거리

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.distance = Math.abs(x) + Math.abs(y);
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(in.readLine().trim());

		for (int tc = 1; tc <= TC; ++tc) {

			sb.append("#" + tc + " ");

			int N = Integer.parseInt(in.readLine()); // 격자점 개수

			Point[] points = new Point[N];
			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				int x = Integer.parseInt(split[0]);
				int y = Integer.parseInt(split[1]);
				points[i] = new Point(x, y);
			}

			int odd = 0; // 거리가 홀수인 격자점의 개수
			int even = 0; // 거리가 짝수인 격자점의 개수
			int max = 0; // 거리가 가장 긴 격자점의 거리

			for (int i = 0; i < N; i++) {

				// 모든 격자점 중 가장 먼 거리의 격자점 거리 구하기
				if (max < points[i].distance)
					max = points[i].distance;

				if (points[i].distance % 2 == 0) {
					even++;
				} else {
					odd++;
				}

			}

			// 모든 점의 거리가 모두 짝수이거나 홀수가 아니라면, 원점으로 이동시킬 수 없다.
			if (odd != 0 && even != 0) {
				sb.append(-1).append("\n");
				continue; // 다음 케이스로 이동
			}

			int answer = 0; // 모든 점이 원점으로 모이도록 하기 위한 이동 횟수
			int i = 0; // i번째에는 i번 만큼 움직일 수 있다.
			while (true) {
				answer += i;

				// 1. 제일 먼 거리의 점은 이동거리가 max 값보다 최소 같거나 커야하고,
				// 2 . 제일 먼 거리의 점이 원점까지 오는 이동 거리가 짝수여야
				// 나머지 점들이 원점에 도착할 수 있다.
				if (answer >= max && (answer - max) % 2 == 0) {
					sb.append(i).append("\n");
					break;
				}
				i++;
			}

		}

		System.out.println(sb);

	}

}
