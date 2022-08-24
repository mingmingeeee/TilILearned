package algo_ws_18_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

public class Main {

	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static boolean[][] visited;

	static int shark_size = 2;
	static Fish shark;
	static List<Fish> fishes;

	static class Fish {
		int x;
		int y;

		public Fish(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				if (map[i][j] == 9) {
					shark = new Fish(i, j);
					map[i][j] = 0; 
				}
			}
		}
		
		int eat_count = 0;
		int answer = 0;
		while (true) {
			visited = new boolean[N][N];

			fishes = new ArrayList<>();
			
			answer += bfs(shark.x, shark.y);

			Collections.sort(fishes, new Comparator<Fish>() {

				public int compare(Fish o1, Fish o2) {

					if (o1.x == o2.x)
						return o1.y - o2.y;

					return o1.x - o2.x;

				};

			});
			
			
			if(!fishes.isEmpty()) {
				int x = fishes.get(0).x;
				int y = fishes.get(0).y;
				map[x][y] = 0;
				shark.x = x;
				shark.y = y;
				
				eat_count++;
				if(eat_count == shark_size) {
					eat_count = 0;
					shark_size++;
				}
			}
			else
				break;

		}
		
		sb.append(answer);
		System.out.println(answer);

	}

	private static boolean isRange(int x, int y) {

		if (0 <= x && x < map.length && 0 <= y && y < map[0].length) {
			return true;
		}
		return false;

	}

	public static int bfs(int x, int y) {

		Queue<Fish> queue = new ArrayDeque<>();

		queue.offer(new Fish(x, y));
		visited[x][y] = true;

		int depth = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();

			if(!fishes.isEmpty())
				return depth;
			
			for (int i = 0; i < size; i++) {
				Fish f = queue.poll();

				for (int d = 0; d < dx.length; d++) {
					int testX = f.x + dx[d];
					int testY = f.y + dy[d];

					if (isRange(testX, testY) && map[testX][testY] <= shark_size
							&& !visited[testX][testY]) {
						visited[testX][testY] = true;
						queue.offer(new Fish(testX, testY));
						if (0 < map[testX][testY] && map[testX][testY] < shark_size)
							fishes.add(new Fish(testX, testY));
					}
				}

			}

			depth++;

		}

		return 0;

	}

}