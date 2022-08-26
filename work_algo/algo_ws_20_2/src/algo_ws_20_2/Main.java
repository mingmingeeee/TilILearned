package algo_ws_20_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 각 칸에 도둑 루피
// 잃을 수 있는 최소 금액 출력

public class Main {

	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { -1, 1, 0, 0 };

	private static int[][] map;
	private static int N;
	private static int min;
	private static boolean[][] visited;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int test_case = 0;
		while (true) {
			N = Integer.parseInt(in.readLine());
			
			if (N == 0)
				break;

			map = new int[N][N];
			min = Integer.MAX_VALUE;
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			dfs(0, 0, 0);
			
			sb.append("Problem " + (++test_case) + ": " + min).append("\n");
			
			
		}

		System.out.println(sb);

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N)
			return true;
		return false;
	}

	private static void dfs(int x, int y, int sum) {

		// 가지 치기
		if (sum > min)
			return;

		// 기저 조건
		if (x == N - 1 && y == N - 1) {
			sum += map[x][y];
			min = Math.min(min, sum);
			return;
		}
		
		sum += map[x][y];
		visited[x][y] = true; 
		// 유도 부분
		for(int i=0; i<dx.length; i++) {
			int testX = x + dx[i];
			int testY = y + dy[i];
			if(isRange(testX, testY) && !visited[testX][testY]) {
				dfs(testX, testY, sum);
			}
		}
		
		visited[x][y] = false; 

	}

}
