package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_10026 {
	
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());
		
		char[][] map1 = new char[N][N];
		char[][] map2 = new char[N][N];
		
		visited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			String split = in.readLine();
			for(int j=0; j<N; j++) {
				char c = split.charAt(j);
				map1[i][j] = c;
				if(c == 'R')
					c = 'G';
				map2[i][j] = c;
					
			}
		}
		
		int count1 = 0;
		int count2 = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					dfs(i, j, map1);
					count1++;
				}
			}
		}  
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j] = false;
			}
		}  
		
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!visited[i][j]) {
					dfs(i, j, map2);
					count2++;
				}
			}
		}  
		
		System.out.println(count1 + " " + count2);
	}
	
	public static void dfs(int i, int j, char[][] map) {
		
		visited[i][j] = true;
		
		for(int d=0; d<dx.length; d++) {
			int testX = i + dx[d];
			int testY = j + dy[d];
			
			if(testX >=0 && testX < N && testY >=0 && testY < N && !visited[testX][testY]
					&& map[i][j] == map[testX][testY]) {
				dfs(testX, testY, map);
			}
			
		}
		
		
	}
	
}
