package baekjoon;

import java.io.*;
import java.util.*;

// N x N 크기 공간 -> 물고기 M마리, 아기 상어 1마리
// 아기 상어: 2로 시적 (1초에 상 하 좌 우 인접한 한 칸씩 이동)
// 아기 상어는 자신의 크기보다 큰 물고기가 있는 칸은 지날 수 없고
// 나머지 칸은 다 가능
// 자신의 크기보다 작은 물고기만 먹을 수 있음
// -> 크기가 같은 물고기: 먹을 수 X 지나갈 수는 있음

// 아기 상어 이동 결정 방법
// 1. 더 이상 먹을 수 있는 물고기가 공간에 없다면 -> 엄마 상어에게 도움 요청 -> 프로그램 종료 
// 2. 먹을 수 있는 물고기 1마리: 먹으러 감
// 3. 먹을 수 있는 고기가 1마리보다 많다면? -> 가장 가까운 물고기 먹으러 감
// 3-1. 거리: 아기 상어가 있는 칸에서 물고기가 있는 칸으로 갈 때 지나야하는 칸의 개수의 최솟값
// 3-2. 거리가 가까운 물고기가 많다면? 가장 위에 있는 물고기.
// 3-2-1. 그러한 물고기가 여러마리라면? 가장 왼쪽에 있는 물고기.

// 우선 순위:	1. 가장 가까운
//		  	2. 가장 위에 있는
//			3. 가장 왼쪽에 있는

// 자신과 크기가 같은 수의 물고기를 먹을 때마다 크기 + 1
// 크기가 2인 아기 상어가 크기가 2인 물고기를 먹으면 크기가 3이 됨
// 아기 상어가 몇 초동안 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있나?

public class B_16236 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int N;
	static int shark_size = 2;
	static int[][] map;
	static PriorityQueue<Fish> feedList;
	static boolean[][] visited;
	static Fish shark;
	static int answer;

	static int count;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(in.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(split[j]);
				if (map[i][j] == 9) {
					shark = new Fish(i, j, 0);
					map[i][j] = 0;
				}
			}
		}

		visited = new boolean[N][N];
		feedList = new PriorityQueue<>(new Comparator<Fish>() {
			
			public int compare(Fish o1, Fish o2) {
				
				
				if(o1.time==o2.time) {
					if(o1.x == o2.x) {
						return o1.y - o2.y;
					}
					return o1.x - o2.x;
				}
				
				return o1.time - o2.time;
				
			};
			
		});

		while (true) {
			// 상어 현재 위치에서 먹을 수 있는 물고기 탐색 (BFS)
			bfs(shark);

			// 먹을 수 있는 물고기 중 가장 우선순위 높은 것 먹기
			if(!feedList.isEmpty()) {
				Fish current = feedList.poll();
				count++;
				if(count == shark_size) {
					shark_size++;
					count = 0;
				}
				
				map[current.x][current.y] = 0;
				
				shark.x = current.x;
				shark.y = current.y;
				
				answer += current.time;
				
				// ====================================== //
				// 식사 끝났으면 초기화
				feedList.clear();
				visited = new boolean[N][N];
				
			} else { // 더 이상 먹을 수 있는 물고기 없다면 탈출
				break;
			}
		}
		
		sb.append(answer);
		System.out.println(sb);

	}

	private static void bfs(Fish shark) {

		Queue<Fish> queue = new ArrayDeque<>();
		queue.offer(shark);

		visited[shark.x][shark.y] = true;

		while (!queue.isEmpty()) {

			Fish cur = queue.poll();

			for (int i = 0; i < dx.length; i++) {
				int testX = cur.x + dx[i];
				int testY = cur.y + dy[i];

				if (isRange(testX, testY) && !visited[testX][testY]) {
					if (map[testX][testY] <= shark_size) { // 식사 가능 && 이동 가능
						visited[testX][testY] = true;
						queue.offer(new Fish(testX, testY, cur.time + 1));
						if (0 < map[testX][testY] && map[testX][testY] < shark_size) { // 먹을 수 있을 때 먹이 리스트 추가
							feedList.offer(new Fish(testX, testY, cur.time + 1));
						}
					}
				}

			}
		}

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < N && 0 <= y && y < N)
			return true;
		return false;
	}

}

class Fish {
	int x;
	int y;
	int time;

	Fish(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}
