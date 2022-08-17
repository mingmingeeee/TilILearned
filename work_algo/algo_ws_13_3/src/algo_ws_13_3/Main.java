package algo_ws_13_3;

import java.io.*;
import java.util.*;

public class Main {

	static int[] dx1 = { 0, 1, 0, -1 };
	static int[] dy1 = { 1, 0, -1, 0 };

	static int[] dx2_1 = { 0, 1, 0, 1 };
	static int[] dx2_2 = { 0, -1, 0, -1 };
	static int[] dy2_1 = { 1, 0, 1, 0 };
	static int[] dy2_2 = { -1, 0, -1, 0 };

	static int[] dx3 = { -1, 1, 1, -1 };
	static int[] dy3 = { 1, 1, -1, -1 };

	static int[] deltas;
	static int[][] map;
	static List<Position> cctv = new ArrayList<>();

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				if (0 < map[i][j] && map[i][j] < 6) {
					cctv.add(new Position(i, j, map[i][j]));
				}
			}
		}

		deltas = new int[cctv.size()];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 5일 때 사방 먼저 표시
				if (map[i][j] == 5) {
					for (int d = 0; d < dx1.length; d++)
						dfs(i, j, map, d);
				}
			}
		}
		
		perm(0);

		// 디버깅용
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		

	}

	// 감시: 9
	static void dfs(int x, int y, int[][] data, int d, int[] dx, int[] dy) {

		int testX = x + dx[d];
		int testY = y + dy[d];
		if (testX >= 0 && testX < data.length && testY >= 0 && testY < data[0].length) {
			if (data[testX][testY] == 0)
				data[testX][testY] = 9;
			dfs(testX, testY, data, d, dx, dy);
		}

	}

	static void find() {

		int[][] tmp = new int[map.length][map[0].length];
		for (int i = 0; i < tmp.length; i++) {
			for (int j = 0; j < tmp[0].length; j++) {
				tmp[i][j] = map[i][j];
			}
		}
		
		for (int i = 0; i < cctv.size(); i++) {
			
			int n = cctv.get(i).data;
			System.out.println(n);
			
			switch(n) {
			case 1:
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, deltas[0], dx1, dy1);
				break;
				
//			case 2:
//				dfs(cctv.get(i).x, cctv.get(i).y, tmp, deltas[0]);
//				break;
//				
			case 3:
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, deltas[1], dx3, dy3);
				break;
				
			case 4:
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, deltas[2], dx4, dy4);
				break;
				
			case 5:
				dfs(cctv.get(i).x, cctv.get(i).y, tmp, deltas[0]);
				break;
			}
			
		}
		

	}

	static void perm(int cnt) {

		if (cnt == cctv.size()) {
			find();
			return;
		}

		for (int i = 0; i < cctv.size(); i++) {
			deltas[cnt] = i;
			perm(cnt + 1);
		}

	}

}

class Position{
	int x;
	int y;
	int data;
	
	Position(int x, int y, int data){
		this.x = x;
		this.y = y;
		this.data = data;
	}
	
}
