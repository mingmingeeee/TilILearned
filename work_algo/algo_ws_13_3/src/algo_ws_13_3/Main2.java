package algo_ws_13_3;

import java.io.*;
import java.util.*;

public class Main2 {

	private static int N; // 세로 크기
	private static int M; // 가로 크기
	private static int[][] data; // 맵 원본
	private static List<Cctv> cctvs; // CCTV 좌표들
	private static int[] directions; // 각 CCTV의 회전 각도 인덱스 번호(0: 0도, 1: 90도, 2: 180도, 3: 270도)
	private static int answer; // 사각 지대의 최소 크기

	// 우, 하, 좌, 상
	private static final int[] dx = { 0, 1, 0, -1 };
	private static final int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		cctvs = new ArrayList<Cctv>();
		data = new int[N][M];
		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				data[i][j] = Integer.parseInt(split[j]);

				// CCTV일 경우 좌표 저장
				if (1 <= data[i][j] && data[i][j] <= 5) {
					cctvs.add(new Cctv(data[i][j], i, j));
				}
			}
		}

		// 각 CCTV의 회전 각도 인덱스 번호 -> 중복순열에 쓰일 배열
		directions = new int[cctvs.size()];

		// 최소값 초기화
		answer = Integer.MAX_VALUE;

		/**
		 * 알고리즘 풀기
		 */
		// 중복 순열
		perm(0);

		System.out.println(answer);
	}

	static void dfs(int x, int y, int direction) {

		direction %= 4; // 0: 우, 1: 하, 2: 좌, 3: 상

		int testX = x + dx[direction];
		int testY = y + dy[direction];

		// 경계 체크
		if ((0 <= testX && testX < N) && (0 <= testY && testY < M)) {
			if (data[testX][testY] == 0) { // 빈 칸이면
				data[testX][testY] = 9; // 감시 영역 표기하고
				dfs(testX, testY, direction); // 다음 칸으로 이동
			}
			// CCTV일 경우 혹은 이미 감시하고 있는 영역일 경우
			else if ((1 <= data[testX][testY] && data[testX][testY] <= 5) || data[testX][testY] == 9) {
				dfs(testX, testY, direction); // 감시 영역 표기 안 하고 다음 칸으로 이동
			}
		}

		// 그 외 조건은 종료
		return;
	}

	static void perm(int cnt) {
		// 기저 부분
		if (cnt == cctvs.size()) {

			// 사본 만들기 (각 경우의 수 수행 후 처음 상대로 돌리기 위해)
			int[][] temp = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					temp[i][j] = data[i][j];
				}
			}

			// CCTV 하나씩 꺼내서 DFS 탐색 방법으로 감시 영역을 data 배열에 표시
			for (int cctvIndex = 0; cctvIndex < cctvs.size(); cctvIndex++) {

				// CCTV 하나를 선택
				Cctv cctv = cctvs.get(cctvIndex);

				// 선택한 CCTV 위치에서 4방향 탐색
				switch (cctv.type) { // cctv 타입이 뭔지
				case 1:
					// 회전 각도 인덱스 번호 0 기준 오른쪽 탐색
					dfs(cctv.x, cctv.y, directions[cctvIndex]);
					break;

				case 2:
					// 회전 각도 인덱스 번호 0 기준 오른쪽,왼쪽 탐색
					dfs(cctv.x, cctv.y, directions[cctvIndex]);
					dfs(cctv.x, cctv.y, directions[cctvIndex] + 2);
					break;

				case 3:
					// 회전 각도 인덱스 번호 0 기준, 오른쪽, 위쪽 탐색
					dfs(cctv.x, cctv.y, directions[cctvIndex]);
					dfs(cctv.x, cctv.y, directions[cctvIndex] + 3);
					break;

				case 4:
					// 회전 각도 인덱스 번호 기준 오른쪽, 왼쪽. 위쪽 탐색
					dfs(cctv.x, cctv.y, directions[cctvIndex]);
					dfs(cctv.x, cctv.y, directions[cctvIndex] + 2);
					dfs(cctv.x, cctv.y, directions[cctvIndex] + 3);
					break;

				case 5:
					// 회전 각도 인덱스 번호 기준, 오른쪽, 아래쪽, 왼쪽, 위쪽 탐색
					dfs(cctv.x, cctv.y, directions[cctvIndex]);
					dfs(cctv.x, cctv.y, directions[cctvIndex] + 1);
					dfs(cctv.x, cctv.y, directions[cctvIndex] + 2);
					dfs(cctv.x, cctv.y, directions[cctvIndex] + 3);
					break;
				}

			}

			// 사각지대 크기 구하기
			int sumOfBlank = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (data[i][j] == 0)
						sumOfBlank++;
				}
			}

			// 최소 크기 갱신
			if (sumOfBlank < answer) {
				answer = sumOfBlank;
			}

			// 원본 복구
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					data[i][j] = temp[i][j];
				}
			}

			return;
		}

		// 중복 순열 (유도 부분) -> [0, 0, 0] ~ [3, 3, 3]
		for (int i = 0; i <= 3; i++) { // 회전 방향이 총 4방향 (0 - 3)
			directions[cnt] = i;

			// 현재 회전 각도 인덱스 번호와 같은 번호부터 처리하도록 전달
			perm(cnt + 1);
		}
	}

}

class Cctv {

	int type;
	int x;
	int y;

	Cctv(int type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}

}
