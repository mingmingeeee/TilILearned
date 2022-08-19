package baekjoon;

import java.io.*;
import java.util.*;

public class B_7562 {

	static int[] dx = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dy = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int N;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			N = Integer.parseInt(in.readLine());

			String[] split = in.readLine().split(" ");
			int startX = Integer.parseInt(split[0]);
			int startY = Integer.parseInt(split[1]);

			split = in.readLine().split(" ");
			int targetX = Integer.parseInt(split[0]);
			int targetY = Integer.parseInt(split[1]);

			
			visited = new boolean[N][N];
			sb.append(bfs(startX, startY, targetX, targetY)).append("\n");

		}

		System.out.println(sb);
	}

	private static int bfs(int startX, int startY, int targetX, int targetY) {
		
		
		Queue<Position8> q = new ArrayDeque<>();
		q.offer(new Position8(startX, startY));
		visited[startX][startY] = true;
		
		int depth = 0;
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			for(int i=0; i<size; i++) {
				
				Position8 p = q.poll();
				
				if(p.x == targetX && p.y == targetY)
					return depth;
				
				
				for(int d=0; d<dx.length; d++) {
					int testX = p.x + dx[d];
					int testY = p.y + dy[d];
					
					if(range(testX, testY) && !visited[testX][testY]) {
						visited[testX][testY] = true;
						q.offer(new Position8(testX, testY));
					}
					
				}
				
			}
			
			depth++;
			
		}
		
		return 0;
		
	}

	private static boolean range(int x, int y) {

		if (0 <= x && x < N && 0 <= y && y < N) {
			return true;
		}
		return false;

	}

}

class Position8 {
	int x;
	int y;

	Position8(int x, int y) {
		this.x = x;
		this.y = y;
	}
}