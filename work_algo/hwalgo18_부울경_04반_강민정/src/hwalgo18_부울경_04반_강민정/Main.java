package hwalgo18_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main {

	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	private static char[][] map1;
	private static char[][] map2;
	private static boolean[][] visited1;
	private static boolean[][] visited2;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		map1 = new char[N][N];
		visited1 = new boolean[N][N];
		
		map2 = new char[N][N];
		visited2 = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			map1[i] = in.readLine().toCharArray();
			for(int j=0; j<N; j++) {
				if(map1[i][j] == 'R')
					map2[i][j] = 'G'; 
				else
					map2[i][j] = map1[i][j]; 
			}
		}
		
		int answer1 = 0;
		int answer2 = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				if(!visited1[i][j]) {
					dfs(i, j, map1, visited1);
					answer1++;
				}
				
				if(!visited2[i][j]) {
					dfs(i, j, map2, visited2);
					answer2++;
				}
				
			}
		}
		
		sb.append(answer1 + " " + answer2);
		
		System.out.println(sb);

	}

	private static boolean isRange(int x, int y) {

		if (0 <= x && x < map1.length && 0 <= y && y < map1[0].length) {
			return true;
		}
		return false;

	}

	private static void dfs(int x, int y, char[][] map, boolean[][] visited) {

		visited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int testX = x + dx[i];
			int testY = y + dy[i];
			if (isRange(testX, testY) && !visited[testX][testY] && map[x][y] == map[testX][testY]) {
				dfs(testX, testY, map, visited);
			}
		}

	}
	
}