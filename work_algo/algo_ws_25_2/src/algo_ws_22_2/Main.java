package algo_ws_22_2;

import java.io.*;
import java.util.*;

public class Main {

	private static final char WALL = '#';
	private static final char SIK = '0';
	private static final char EXIT = '1';

	private static final int[] dx = { 1, -1, 0, 0 };
	private static final int[] dy = { 0, 0, -1, 1 };

	private static char[][] map;
	private static int N;
	private static int M;
	
	private static class Position {
		int x;
		int y;
		int key;

		public Position(int x, int y, int key) {
			super();
			this.x = x;
			this.y = y;
			this.key = key;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		Position cur = null;

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			split = in.readLine().split("");
			for (int j = 0; j < M; j++) {
				map[i][j] = split[j].charAt(0);
				if (map[i][j] == SIK) {
					cur = new Position(i, j, 0);
				}
			}
		}

		sb.append(bfs(cur));
		
		System.out.println(sb);

	}

	private static int bfs(Position p) {

		boolean[][][] visited = new boolean[N][M][64];
		
		Queue<Position> queue = new ArrayDeque<>();
		queue.offer(p);
		visited[p.x][p.y][0] = true;

		int depth = 0;
		while (!queue.isEmpty()) {	

			int size = queue.size();

			for (int i = 0; i < size; i++) {
				
				Position cur = queue.poll();
				
				if(map[cur.x][cur.y] == EXIT) {
					return depth;
				}
				
				for(int d=0; d<4; d++) {
					int testX = cur.x + dx[d];
					int testY = cur.y + dy[d];
					
					if(testX >= 0 && testX < N && testY >= 0 && testY < M) { // 맵 안에 있을 때 
						if(map[testX][testY] == WALL)
							continue;
						
						if(map[testX][testY] - 'a' >= 0 && map[testX][testY] - 'a' < 6) { // 열쇠 만났을 때
							int position = map[testX][testY] - 'a';
							int key = cur.key | (1 << position);
							if(!visited[testX][testY][key]) {
								queue.offer(new Position(testX, testY, key));
								visited[testX][testY][key] = true;
							}
						}
						else if(map[testX][testY] - 'A' >= 0 && map[testX][testY] - 'A' < 6) { // 문 만났을 때
							int position = map[testX][testY] - 'A';
							if((cur.key & (1 << position)) != 0 && !visited[testX][testY][cur.key]) {
								queue.offer(new Position(testX, testY, cur.key));
								visited[testX][testY][cur.key] = true;
							}
						} else if(!visited[testX][testY][cur.key]) { // 그 외
							queue.offer(new Position(testX, testY, cur.key));
							visited[testX][testY][cur.key] = true;
						}
					}
				}
				
			}
			
			depth++;

		}

		return -1;
	}

}