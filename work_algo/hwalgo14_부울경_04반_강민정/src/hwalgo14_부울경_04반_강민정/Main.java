package hwalgo14_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static boolean[] visited = new boolean[26];
	static char[][] data;
	static int R;
	static int C;
	static int max = 1;

	public static void main(String[] args) throws Exception {
		 
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] split = in.readLine().split(" ");
		
		R = Integer.parseInt(split[0]);
		C = Integer.parseInt(split[1]);
		
		data = new char[R][C];
		for(int i=0; i<R; i++) {
			char[] c = in.readLine().toCharArray();
			data[i] = c; 
		}
		
		dfs(0, 0, 1);
		
		System.out.println(max);
		
	}

	static void dfs(int x, int y, int depth) {
		
		max = Math.max(max, depth);
		
		visited[data[x][y] - 'A'] = true;
		for(int i=0; i < dx.length; i++) {
			int testX = x + dx[i];
			int testY = y + dy[i];
			
			if(0 <= testX && testX < R && 0 <= testY && testY < C
					&& !visited[data[testX][testY] - 'A']) {

				dfs(testX, testY, depth+1);
			}
		}

		visited[data[x][y] - 'A'] = false;
		
	}
	
}