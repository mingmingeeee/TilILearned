package algo_ws_22_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// "시뮬레이션(구현)" + 중복 순열 + DFS 비교
// + 열 우선 탐색 !!!!!!!

// 1. 구슬: 좌, 우로만 움직일 수 있음 => 구슬이 떨어지는 위치 고려!!!
// 2. 벽돌: 숫자 1 ~ 9로 표현
//    => 구슬이 명중한 벽돌: 상하좌우 (벽돌에 적힌 숫자 - 1) 칸 만큼 같이 제거 

// 최대한 많은 벽돌 수 제거! = 남아있는 벽돌 수 최소
// 남은 벽돌 개수 구하기!

// 해야할 것
// (1) 구슬 던지기 => "중복 순열"
// (2) 구슬에 맞는 벽돌 처리 (연쇄적으로 처리) => 부서지는 벽돌 모두 처리 => "그래프 완전 탐색(BFS, DFS)"
// (3) 부서진 벽돌 제거, 빈 공간 없이 벽돌 내리기


// [1] 구슬이 한 번 던져질 때마다 남은 벽돌 개수 체크 
// [2] 남은 구슬 개수 0이면???? TC 완전 종료

public class Solution3 {

	static int N, W, H, min;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int TC = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			StringTokenizer st = new StringTokenizer(in.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			int[][] map = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(in.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			min = Integer.MAX_VALUE;
			go(map, 0);

			System.out.println("#" + tc + " " + min);
		}

	}

	// 구슬 던지기 게임
	static boolean go(int[][] map, int cnt) { // map: 직전 구슬까지 던진 상태의 배열
		
		int result = getRemain(map); // 남은 벽돌 구하기 
		if(result == 0) {
			min = result; // 무족권 최소값으로 세팅!
			return true; // 다 깨진 거 X => 더이상 남은 거 탐색 못하도록 알려줌 
		}

		if (cnt == N) { // 구슬을 다 던진 상태
			// 남은 벽돌 수 카운트, 최솟 값 갱신

			if (min > result)
				min = result;

			return false;
		}

		// 구슬 던지기 (중복 순열)
		int[][] newMap = new int[H][W];
		for (int c = 0; c < W; c++) {

			// 구슬에 맞는 시작 벽돌 찾기 (빈칸이 아닌 첫 벽돌)
			int r = 0;
			while (r < H && map[r][c] == 0) { // 열 우선 탐색
				++r;
			}
			if (r == H) { // 맞는 시작 벽돌이 없는 상태
				continue; // 지금 던져도 맞는 애가 없다면 그냥 옆 열에 던지면 된다~
			} else { // 맞는 시작 벽돌이 있는 상태
				copy(map, newMap);

				// 제거될 벽돌 연쇄 처리
				boom(newMap, r, c);

				// 벽돌 중력 처리
				down(newMap);

				// 다음 구슬 던지기
				if(go(newMap, cnt + 1)) return true; // 불렀는데 다 부서졌어용~ 더 부술 수 있는 게 없어용~ 하면 끝! 
			}

		}
		
		return false;

	}

	private static int getRemain(int[][] map) {
		int result = 0;
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				if (map[r][c] > 0)
					result++;
			}
		}
		return result;
	}

	static Stack<Integer> stack = new Stack<Integer>();

	private static void down(int[][] map) {

		for (int c = 0; c < W; c++) {
			// 윗행부터 남은 벽돌 스택에 넣기
			for (int r = 0; r < H; r++) {
				if (map[r][c] > 0) {
					stack.push(map[r][c]);
					map[r][c] = 0; // 넣으면 다 0!! 그래야 다시 넣을 수 있으니
				}
			}
			// 남은 벽돌은 스택에 들어있고 모든 칸은 빈칸 상태 
			int nr = H - 1;
//			for(int no : stack) {
//				map[nr--][c] = no;
//			}

			// OR
			while(!stack.isEmpty()) {
				map[nr--][c] = stack.pop();
			}
			
		}

//		for (int c = 0; c < W; c++) {
//
//			int r = H - 1; // 밑에서부터 보기
//			while (r > 0) {
//				if (map[r][c] == 0) { // 빈칸이면 내릴 벽돌 찾기
//					int nr = r - 1;
//					while (nr > 0 && map[nr][c] == 0)
//						nr--; // 빈칸이면 계속 올라가기
//
//					map[r][c] = map[nr][c]; // 칸을 내려주기
//					map[nr][c] = 0; // 내림처리한 벽돌은 빈칸 처리
//				}
//				
//				--r; // 빈칸이 아니면 계속해서 올라가야 함 
//			}
//
//		}

	}

	// r, c: 0이 아닌 위치 값 (1 이상 값)
	// 2 이상: 주변 애들한테 영향 ㅇㅇ -> 이런 애들만 queue에 넣겠다!!!
	private static void boom(int[][] map, int r, int c) { // BFS
		Queue<Point> queue = new ArrayDeque<Point>();
		// 벽돌이 있던 자리를 0으로 변경 : 빈 칸으로 만들어서 방문처리

		if (map[r][c] > 1) {
			queue.offer(new Point(r, c, map[r][c]));
		}
		map[r][c] = 0; // 방문 처리 ==> 제거 처리

		while (!queue.isEmpty()) {

			Point p = queue.poll(); // 주변에 영향 주는 벽돌 정보

			// 벽돌의 크기 - 1 만큼 주변 벽돌(4방) 연쇄 처리
			for (int d = 0; d < 4; d++) {
				int nr = p.r;
				int nc = p.c;

				for (int k = 1; k < p.cnt; k++) { // 현재 방향에서 cnt-1 벽돌 보기
					nr += dr[d];
					nc += dc[d];

					if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] > 0) {
						if (map[nr][nc] > 1) {
							queue.offer(new Point(nr, nc, map[nr][nc]));
						}
						map[nr][nc] = 0; // 방문 처리 ==> 제거 처리
					}
				}

			}

		}
	}

	private static void copy(int[][] map, int[][] newMap) {
		for (int r = 0; r < H; r++) {
			for (int c = 0; c < W; c++) {
				newMap[r][c] = map[r][c];
			}
		}
	}

	static class Point {

		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

	}

}
