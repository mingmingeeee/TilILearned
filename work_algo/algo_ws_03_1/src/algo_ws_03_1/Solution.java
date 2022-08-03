package algo_ws_03_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
	
	static char[][] map;
	static int CurX;
	static int CurY;
	static int X;
	static int Y;

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			
			st = new StringTokenizer(in.readLine(), " ");
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			
			map = new char[X][Y];
			for(int i = 0; i<X; i++) {
				String line = in.readLine();
				for(int j=0; j<Y; j++) {
					map[i][j] = line.charAt(j);
					if(map[i][j] == '^' || map[i][j]== '>' || map[i][j]=='v' || map[i][j]=='<' ) {
						CurX = i;
						CurY = j;
					}
					
				}
			}
			
			int N = Integer.parseInt(in.readLine());
			char[] command = in.readLine().toCharArray();
			
			/**
			 * 2. 알고리즘 풀기
			 */
		
			// (U D L R S)
			for(int i=0; i<command.length; i++) {
				
				char c = command[i];
				
				switch(c) {
				
				case 'U':
					move(CurX-1, CurY, '^');
					break;
					
				case 'D':
					move(CurX+1, CurY, 'v');
					break;
					
				case 'L':
					move(CurX, CurY-1, '<');
					break;
					
				case 'R':
					move(CurX, CurY+1, '>');
					break;
					
				case 'S':
					Shoot();
					break;
				
				}
				
				
			}

			/**
			 * 3. 정답 출력
			 */
			
			for(int i = 0; i<X; i++) {
				for(int j=0; j<Y; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}


		}
		System.out.println(sb);
	}
	
	static boolean border_check(int x, int y) {
		if(x>=0 && x<X && y>=0 && y<Y) {
			return true;
		}
		return false;
	}
	
	static void move(int x, int y, char c) {
		if(border_check(x, y)) {
			if(map[x][y]=='.') {
				map[CurX][CurY] = '.';
				map[x][y]=c;
				CurX = x;
				CurY = y;
			} else  {
				map[CurX][CurY] = c;
			}
		} else  {
			map[CurX][CurY] = c;
		}
	}


	// Shoot: 현재 방향 기호대로 포탄 발사 -> 벽 or 경계 밖 -> 멈춤
	// -> 경계 "*" -> "." 됨
	// -> 경계 "#" -> 포탄만 사라짐
	static void Shoot() {
		int dx = 0;
		int dy = 0;
		char c = map[CurX][CurY];
	
		switch(c) {
		case '^': 
			dx = -1;
			break;
		case 'v':
			dx = 1;
			break;
		case '<':
			dy = -1;
			break;
		case '>':
			dy = 1;
			break;
		}
		
		int testX = CurX + dx;
		int testY = CurY + dy;
		while(border_check(testX, testY) && (map[testX][testY] == '.'||map[testX][testY]=='-')) {			
			testX += dx;
			testY += dy; 
		}
		
		if(border_check(testX, testY) && map[testX][testY] == '*') {
			map[testX][testY] = '.';
		}

	}
}
