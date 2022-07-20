package com.ssafy.ws02.step3;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {

	// 위부터 시계방향 8방향
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T = Integer.parseInt(in.readLine());  // 테스트 케이스
		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");
			// 알고리즘 작성
			int N = Integer.parseInt(in.readLine());  // 부지의 한 변 길이
			char[][] map = new char[N][N];  // 부지 정보를 담을 2차원 배열

			// 파일로부터 부지 정보를 불러와 2차원 배열에 담는다.
			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = split[j].charAt(0);
				}
			}
			
			int max = 0;
			
			for(int x = 0; x < N; x++) {
				for(int y = 0; y < N; y++) {
					
					int flag = 0; // 공원이 존재하면 1
					
					// 현 위치에서 주변 8방 탐색
					for(int i = 0; i < 8; i++) {
						int testX = x + dx[i];
						int testY = y + dy[i];
						
						if(0 <= testX && testX < N && 0 <= testY && testY < N) {
							
							// 주변에 공원이 있다면 탐색 중단
							if(map[testX][testY] == 'G') {
								flag = 1;
								break;
							}
						}
					}
					
					if(flag == 1) { // 인접 구획에 공원이 존재
						if(max < 2) {
							max = 2;
						}
					}
					
					else if(flag == 0) { // 인접 구획에 공원이 없을 경우
						int cnt = 0;
						// 가로 빌딩 합
						for(int i=0; i<N; i++) {
							if(map[x][i] == 'B') {
								cnt++;
							}
						}
						
						// 세로 빌딩 합
						for(int i=0; i<N; i++) {
							if(map[i][y] =='B') {
								cnt++;
							}
						} 
						
						// 나 자신 중복으로 하나 빼기
						cnt--;
						
						if(max < cnt) {
							max = cnt;
						}
						
					}
				}
			}
			System.out.println(max);
		}
	}
	
}
