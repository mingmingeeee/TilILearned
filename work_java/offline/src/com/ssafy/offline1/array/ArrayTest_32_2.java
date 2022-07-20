package com.ssafy.offline1.array;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.cert.X509CRLEntry;

import javax.naming.directory.DirContext;

public class ArrayTest_32_2 {

	public static int[] dx = { 0, -1, 0, 1, 0 };
	public static int[] dy = { 0, 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ArrayTest_32_input.txt"));

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine()); // 테스트 케이스

		// 테스트케이스 for ###
		for (int test_case = 1; test_case <= T; test_case++) {

			System.out.print("#" + test_case + " ");

			String[] split = in.readLine().split(" ");
			int N = Integer.parseInt(split[0]);
			int start_x = Integer.parseInt(split[1]);
			int start_y = Integer.parseInt(split[2]);
			int jumper_count = Integer.parseInt(split[3]);

			int[][] jumper = new int[jumper_count][2];

			split = in.readLine().split(" ");
			for (int i = 0; i < jumper_count; i++) {
				jumper[i][0] = Integer.parseInt(split[i * 2]);
				jumper[i][1] = Integer.parseInt(split[i * 2 + 1]);
			}

			int order_count = Integer.parseInt(in.readLine());
			int[][] order = new int[order_count][2];

			split = in.readLine().split(" ");
			for (int i = 0; i < order_count; i++) {
				order[i][0] = Integer.parseInt(split[i * 2]);
				order[i][1] = Integer.parseInt(split[i * 2 + 1]);
			}

			// 맵 생성
			int[][] map = new int[N + 1][N + 1];

			// 점퍼 설정
			for (int i = 0; i < jumper_count; i++) {
				map[jumper[i][0]][jumper[i][1]] = 1;
			}

			int x = start_x;
			int y = start_y;
			
			external:
			for(int i=0; i<order_count; i++) {
				for(int j=0; j<order[i][1]; j++) {
					x = x + dx[order[i][0]];
					y = y + dy[order[i][0]];
					if(x<1 || x>=N+1 || y<1 || y>=N+1 || map[x][y]==1) {
						x=0; y=0;
						break external;
					}
				}
			}
			
			System.out.println(x + " " + y);
		}
	}

}
