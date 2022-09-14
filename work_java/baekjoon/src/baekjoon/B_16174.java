package baekjoon;

import java.io.*;
import java.util.*;

public class B_16174 {

	// 쩰리 점프
	// 1. 정사각형 구역 내부에서만 움직일 수 있음 -> 외부로 나가는 경우 게임에서 패배
	// 2. 출발점은 정사각형의 가장 왼쪽 가장 위 칸
	// 3. 이동 가능한 방향: 오른쪽, 아래
	// 4. 가장 오른쪽, 가장 아래 도달: 쩰리 승리
	// 5. 한 번에 이동할 수 있는 칸 수: 현재 밟고 있는 칸에 쓰여 있는 수 만큼

	private static final int[] dx = {0, 1};
	private static final int[] dy = {1, 0};
	private static int N;
	private static int[][] map;
	
	static class Position {
		int x, y;
		
		Position(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] s = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(s[j]);
			}
		}
		
		sb.append(bfs(0, 0));
		
		System.out.println(sb);
	}
	
	private static String bfs(int x, int y) {
		Queue<Position> q = new ArrayDeque<>();		
		q.offer(new Position(x, y));
		
		boolean[][] visited = new boolean[N][N];
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			
			Position p = q.poll();
			
			if(p.x == N-1 && p.y == N-1)
				return "HaruHaru";
			
			for(int i=0; i<dx.length; i++) {
				int testX = p.x + dx[i] * map[p.x][p.y];
				int testY = p.y + dy[i] * map[p.x][p.y];
				
				if(isRange(testX, testY) && !visited[testX][testY]) {
					visited[testX][testY] = true;
					q.offer(new Position(testX, testY));
				}
			}
			
		}
		
		return "Hing";
	}

	private static boolean isRange(int x, int y) {
		return (x >= 0 && x < N && y >= 0 && y < N) ? true : false;
	}

}
