package baekjoon;

import java.io.*;
import java.util.*;

public class B_7569 {

	static int[] dz = { 1, -1, 0, 0, 0, 0 };
	static int[] dx = { 0, 0, 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 0, 0, 1, -1 };

	static boolean[][][] visited;
	static int[][][] tomato;
	static Queue<Position> q = new ArrayDeque<>();

	static int count_not;
	static int count_yes;
	static int M;
	static int N;
	static int H;
	
	static int answer = 0;
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] split = in.readLine().split(" ");
		M = Integer.parseInt(split[0]);
		N = Integer.parseInt(split[1]);
		H = Integer.parseInt(split[2]);

		tomato = new int[H][N][M];
		visited = new boolean[H][N][M];

		count_not = 0;
	
		for (int z = 0; z < H; z++) {
			for (int x = 0; x < N; x++) {
				split = in.readLine().split(" ");
				for (int y = 0; y < M; y++) {
					int n = Integer.parseInt(split[y]);
					tomato[z][x][y] = n;
					if (n == 0)
						count_not++;
				}
			}
		}

		for (int z = 0; z < H; z++) {
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < M; y++) {

					if (tomato[z][x][y] == 1) {
						q.offer(new Position(z, x, y));
					}
					
				}
			}
		}
		
		bfs();

		if(count_not!=count_yes)
			sb.append(-1);
		else
			sb.append(answer);

		System.out.println(sb);
	}

	static void bfs() {

		int depth = 0;
		
		while (!q.isEmpty()) {

			int size = q.size();

			if(count_yes==count_not) {
				if(answer < depth)
					answer = depth;
			}

			for (int i = 0; i < size; i++) {
				Position p = q.poll();

				visited[p.z][p.x][p.y] = true;
				for (int d = 0; d < dz.length; d++) {
					int testZ = p.z + dz[d];
					int testX = p.x + dx[d];
					int testY = p.y + dy[d];

					if (testZ >= 0 && testZ < H && testX >= 0 && testX < N && testY >= 0 && testY <M) {
						if (tomato[testZ][testX][testY] == 0) {
							q.offer(new Position(testZ, testX, testY));
							tomato[testZ][testX][testY] = 1;
							count_yes++;
						}

					}
				}
			}
			depth++;
		}		

	}

}

class Position {

	int z;
	int x;
	int y;

	public Position(int z, int x, int y) {

		this.z = z;
		this.x = x;
		this.y = y;

	}

}