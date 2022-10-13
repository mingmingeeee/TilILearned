
import java.io.*;
import java.util.*;

public class SWTest_2 {

	private static int n, cnt, min;
	private static int[] di = { -1, 1, 0, 0 };
	private static int[] dj = { 0, 0, -1, 1 };
	private static boolean[][] visited;
	private static ArrayList<Point> cores;

	private static class Point {
		int i;
		int j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(in.readLine());

		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(in.readLine()); // 가로/세로 길이
			visited = new boolean[n][n]; // 방문 가능 표시 배열 => 전선을 겹치지 않게 설치하기 위해
			min = Integer.MAX_VALUE; // 최소 전선 길이 합 => 정답 담을 변수
			cores = new ArrayList<Point>(); // 가장자리코어 제외한 코어 위치 담을 리스트

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				for (int j = 0; j < n; j++) {
					if (Integer.parseInt(st.nextToken()) == 1) { // 해당 위치가 core라면
						visited[i][j] = true; // 코어면 방문 표시 => 전선을 설치할 수 없기 때문
						if (i != 0 && i != n - 1 && j != 0 && j != n - 1) { // 가장 자리 코어가 아닌 코어만 cores에 추가
							cores.add(new Point(i, j));
						}
					}
				}
			}
			
			cnt = cores.size(); // 연결 가능한 최대 코어 개수

			// cnt가 0이라면 정답 0 출력후 다음 테스트 케이스로
			if (cnt == 0) {
				sb.append("#" + t + " " + 0 + "\n");
				continue;
			}

			// 차례로 cnt(최대 코어수), cnt - 1, .... , 1개의 코어를 뽑는 경우의 수 구한다.
			// 내림차순으로 호출!
			for (int i = cnt; i >= 1; i--) {
				comb(new int[i], 0, 0, i); // comb(뽑은 원소 넣을 배열, 시작 인덱스, 뽑은 개수, 뽑을 개수)

				if (min != Integer.MAX_VALUE) // min 값이 업데이트 되었다면 for문 탈출 => 모든 코어들이 연결가능하다는 뜻
					break;
			}

			// 정답 min 출력
			sb.append("#" + t + " " + min + "\n");

		}

		System.out.println(sb);

	}

	private static void comb(int[] output, int start, int depth, int r) {
		// r개 뽑았으면
		if (depth == r) {
			// dfs(뽑은 코어들의 인덱스 담은 배열, 현재 인덱스, 연결한 전선 길이)
			dfs(output, 0, 0);
			return;
		}

		for (int i = start; i < cnt; i++) {
			output[depth] = i;
			comb(output, i + 1, depth + 1, r);
		}
	}

	// dfs(뽑은 코어들의 인덱스 담은 배열, 현재 인덱스, 연결한 전선 길이)
	private static void dfs(int[] output, int idx, int sum) {
		// 뽑은 모든 코어 확인했으면 전선 개수 최솟값 min 갱신
		if (idx == output.length) {
			if (sum < min)
				min = sum;
			return;
		}

		Point now = cores.get(output[idx]); // now : 뽑은 코어들 중 현재 코어 위치값

		// 현재 코어에서 상하좌우 탐색
		for (int k = 0; k < 4; k++) {
			int tmp = 1; // tmp: 현재 코어에서 k 방향의 가장자리까지 탐색하기 위한 변수
			boolean flag = true;

			// 상하좌우 중, 현재 방향 (k)으로 가장자리가 나올 때까지 탐색해보기
			while (true) {
				int ni = now.i + di[k] * tmp;
				int nj = now.j + dj[k] * tmp;
				if (ni < 0 || nj < 0 || ni >= n || nj >= n) {
					break;
				}
				if (!visited[ni][nj]) { // 방문 가능하면
					visited[ni][nj] = true; // 방문 표시후
					tmp++; // 한 칸 더 가기
				} else { // 방문 가능하지 않으면 더 이상 갈 수 없으므로 flag = false 처리 후 while문 탈출
					flag = false;
					break;
				}
			}

			// 가장자리까지 전선 열결 성공했으면 다음 코어로 dfs 탐색
			// 이 때 tmp - 1이 연결한 전선의 길이이므로 sum에 (tmp - 1) 더해줌
			if (flag) {
				dfs(output, idx + 1, sum + (tmp - 1));
			}
			// 현재 코어에서 현재 방향으로 탐색할 때 방문표시 해놓았던 거 돌려놓기
			while (true) {
				int ni = now.i + di[k] * (tmp - 1);
				int nj = now.j + dj[k] * (tmp - 1);

				if (ni == now.i && nj == now.j)
					break;
				if (visited[ni][nj]) {
					visited[ni][nj] = false;
				}
				tmp--;
			}
		}
	}

}
