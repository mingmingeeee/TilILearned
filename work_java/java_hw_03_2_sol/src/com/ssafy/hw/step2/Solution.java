package com.ssafy.hw.step2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
/**
 * 2029. 데일리과제 3-2. 섬나라 다리짓기 Lv2
 */ 
public class Solution {

	// 위부터 시계방향 4방향 (상, 우, 하, 좌)
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());  // 지형의 한 변 길이
		int[][] map = new int[N][N];  // 지형 정보를 담을 2차원 배열

		// 파일로부터 지형 정보를 불러와 2차원 배열에 담는다.
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		int max = 0;  // 가장 길게 세울 수 있는 다리 길이를 저장하는 변수

		// 2차원 배열 완전탐색
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				
				// 현재 위치가 섬일 경우, 4방향 탐색하여 다리 길이를 구한다.
				if (map[x][y] == 1) {
					for (int i = 0; i < 4; i++) {
						int testX = x + dx[i];
						int testY = y + dy[i];
						int cnt = 0;  // 한 방향 탐색 시, 세울 수 있는 다리 길이를 저장하는 변수
						int flag = 0;  // 다리 건설 가능하다면 1 (섬에서 시작해서 섬으로 끝나야 한다)

						while (0 <= testX && testX < N && 0 <= testY && testY < N) {
							if (map[testX][testY] == 1) {  // 섬이라면 다리 건설 후 탐색 중단
								flag = 1;  // 다리 건설 가능
								cnt++;
								break;
							}
							else if (map[testX][testY] == 0) {  // 섬이 아니라면 다리 건설
								cnt++;
							}

							// 다음으로 이동
							testX = testX + dx[i];
							testY = testY + dy[i];
						}

						// 다리 건설이 가능하다면, 가장 긴 다리 길이 갱신
						if (flag == 1) {
							if (max < cnt) {
								max = cnt;
							}
						}
					}
				}
			}
		}

		System.out.println(max);
	}
}