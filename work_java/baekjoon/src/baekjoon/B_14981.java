package baekjoon;

import java.io.*;
import java.util.*;

public class B_14981 {

	// 톱니바퀴 번호 -> 가장 왼쪽: 1번, 오른쪽: 2번, 그 오른쪽: 3번, 가장 오른쪽: 4번
	// 톱니바퀴 회전: 한 칸 기준 -> 시계 방향, 반시계 방향
	// 회전할 때 맞닿은 극에 따라 옆에 있는게 회전할 수도 있고 아닐 수도 있음
	// 맞닿은 톱니의 극이 다르다면? 반대 방향으로 회전
	// 극이 같다면? 회전 안 함

	// 1번 톱니바퀴 12시 방향 N극: 0점, S극: 1점
	// 2번 S극: 2점
	// 3번 S극: 4점
	// 4번 S극: 8점

	// N극: 0, S극: 1

	private static int[][] Wheels;
	private static int[][] tmp_Wheels;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Wheels = new int[4][8];

		for (int i = 0; i < 4; i++) {
			String[] split = in.readLine().split("");
			for (int j = 0; j < 8; j++) {
				Wheels[i][j] = Integer.parseInt(split[j]);
			}
		}

		int K = Integer.parseInt(in.readLine()); // 회전 횟수
		for (int i = 0; i < K; i++) {
			String[] split = in.readLine().split(" ");
			int N = Integer.parseInt(split[0]) - 1; // 톱니바퀴 번호
			int D = Integer.parseInt(split[1]); // 방향
			// 1: 시계 방향, -1: 반시계 방향
			tmp_Wheels = new int[4][8];
			for (int k = 0; k < 4; k++) {
				for (int j = 0; j < 8; j++) {
					tmp_Wheels[k][j] = Wheels[k][j];
				}
			}
			boolean[] visited = new boolean[4];
			rotate(N, D, visited);

		}

		int answer = 0;
		if (Wheels[0][0] == 1)
			answer++;
		if (Wheels[1][0] == 1)
			answer += 2;
		if (Wheels[2][0] == 1)
			answer += 4;
		if (Wheels[3][0] == 1)
			answer += 8;

		sb.append(answer);

		System.out.println(sb);

	}

	private static void real_rotate(int N, int D) {
		if (D == 1) {
			int tmp = Wheels[N][Wheels[0].length - 1];
			for (int i = Wheels[0].length - 1; i > 0; i--) {
				Wheels[N][i] = Wheels[N][i - 1];
			}
			Wheels[N][0] = tmp;
		} else {
			int tmp = Wheels[N][0];
			for (int i = 0; i < Wheels[0].length - 1; i++) {
				Wheels[N][i] = Wheels[N][i + 1];
			}
			Wheels[N][Wheels[0].length - 1] = tmp;
		}

	}

	private static void rotate(int N, int D, boolean[] visited) {

		visited[N] = true;
		real_rotate(N, D);

		if (N + 1 < Wheels.length && !visited[N + 1]) {
			if (tmp_Wheels[N][2] != tmp_Wheels[N + 1][tmp_Wheels[0].length - 2]) { // 나와 내 앞에 거가 다른 극이라면
				rotate(N + 1, D * -1, visited);
			}
		}
		if (N - 1 >= 0 && !visited[N - 1]) {
			if (tmp_Wheels[N][tmp_Wheels[0].length - 2] != tmp_Wheels[N - 1][2]) { // 나와 내 뒤에 거가 다른 극
				rotate(N - 1, D * -1, visited);

			}
		}
	}

}
