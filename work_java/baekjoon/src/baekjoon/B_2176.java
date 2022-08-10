package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.crypto.Data;

public class B_2176 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N;
	static int M;
	static int[][] data;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] strings = in.readLine().split(" ");
		N = Integer.parseInt(strings[0]);
		M = Integer.parseInt(strings[1]);

		data = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = in.readLine();
			for (int j = 0; j < M; j++) {
				data[i][j] = s.charAt(j) - '0';
			}
		}

		dfs(0, 0);

	}

	static void dfs(int x, int y) {
		
		for(int i=0; i<4; i++) {
			int testX = x + dx[i];
			int testY = y + dy[i];
			
			if(testX>=0 && testX <N && testY >=0 && testY < M
					&& data[testX][testY] == 1) {
				System.out.println(testX + " " + testY);
				dfs(testX, testY);
			} 
		}
		
	}

}
