import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2 {

	static int N = 0, INF = Integer.MAX_VALUE;
	static int map[][];
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= TC; ++tc) {
			N = Integer.parseInt(in.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; ++i) {
				char[] ch = in.readLine().toCharArray();
				for (int j = 0; j < N; ++j) {
					map[i][j] = ch[j] - '0';
				}
			}

			System.out.println("#" + tc + " " + dijkstra(0, 0));
		}

	}

	private static int dijkstra(int startR, int startC) {

		// int[] : r, c, minTime
		// 행 좌표, 열 좌표, 그 때의 최소 시간
		// 최소 시간 기준으로 !!! 빠른 녀석을 뽑을 것임
		PriorityQueue<int[]> pQueue = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2]; // minTime 기준 오름차순이 되도록
			}

		});

		// 출발지에서 자신으로의 최소비용을 저장할 배열 생성 후 초기화
		int[][] minCost = new int[N][N];
		boolean[][] visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				minCost[i][j] = INF;
			}
		}

		// 출발지에서 출발지로의 최소 비용 0 처리
		minCost[startR][startC] = 0;
		pQueue.offer(new int[] { startR, startC, minCost[startR][startC] });
		int r = 0, c = 0, nr, nc, minTime;

		while (!pQueue.isEmpty()) {

			// step1. 미방문 정점중 최소 비용 정점 찾기
			int[] current = pQueue.poll(); // 현재 탐색해야 하는 곳 정보 담고 있는 배열이 나옴
			r = current[0];
			c = current[1];
			minTime = current[2];

			if (visited[r][c]) // 처리된 정점이면 다음으로 
				continue;

			// 갈 수 있는 정점이 있다면 방문 체크
			visited[r][c] = true;

			// 정답 리턴
			if (r == N - 1 && c == N - 1)
				return minTime;

			// step2. 현재 정점 기준으로 인접한 정점을 들여다보며 경유 비용이 유리한지 계산
			for (int d = 0; d < 4; d++) { // 인접 정점: 4방
				nr = r + dr[d];
				nc = c + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]
						&& minCost[nr][nc] > minTime + map[nr][nc]) {
					minCost[nr][nc] = minTime + map[nr][nc];
					pQueue.offer(new int[] { nr, nc, minCost[nr][nc] });
				}
			}

		}
		return -1;

	}

}
