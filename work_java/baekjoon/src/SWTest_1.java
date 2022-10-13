
import java.io.*;
import java.util.*;
// 집의 위치 및 최대 충전 가능 범위가 주어졌을 때,
// 충전소와 각 집들 간의 거리들의 합이 최소가 되는 값을 구하라
// - 충전소의 개수는 최대 2개
// - 1개로 가능하면 무조건 1개

// 집의 개수: 2 <= N <= 20
// 좌표: -15 <= x, y <= 15
// 범위: 1 <= d <= 15
public class SWTest_1 {

	private static class House {
		int i, j, len;

		public House(int i, int j, int len) {
			super();
			this.i = i;
			this.j = j;
			this.len = len;
		}
	}

	private static class Point {
		int i, j;

		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}

	}

	private static int n;
	private static int ans;
	private static boolean[][] visited;
	private static House[] houses;
	private static ArrayList<Point> candidate;
	private static int[] di = { -1, 1, 0, 0 };
	private static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {

			n = Integer.parseInt(in.readLine().trim()); // 집 개수
			ans = Integer.MAX_VALUE; // 집들-충전소 최소 거리 합을 정수형 MAX 값으로 초기화
			visited = new boolean[31][31]; // 방문 가능 표시 배열 -> 충전소 설치 가능 위치 파악 위해
			houses = new House[n]; // 집들의 정보 담을 배열

			for (int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int len = Integer.parseInt(st.nextToken());

				// 좌표값을 2차원 배열의 인덱스 값으로 변경
				x = 15 + x;
				y = 15 - y;

				houses[i] = new House(y, x, len); // 집의 정보를 houses 리스트에 추가
				visited[y][x] = true; // 방문 표시 -> 집이 있는 곳에는 충전소를 설치하지 못하므로
			}

			// 충전소 건설 가능한 후보들 위치 담을 리스트
			candidate = new ArrayList<>();

			// 모든 집들의 상하좌우 충전가능한 범위내의 위치를 candidate에 추가한다.
			// 이 때 중복된 위치를 추가하지 않기 위해 visited 배열을 사용한다.
			// + 충전소 건설 위치 후보를 탐색하는 김에, 해당 위치에서 모든 집을 연결가능한지도 함께 파악해본다 => 충전소 1개로 모든집 연결
			// 가느안디
			for (int i = 0; i < n; i++) {
				House house = houses[i];

				// 현재 집의 상하좌우 탐색
				for (int k = 0; k < 4; k++) {
					int cnt = 1;
					// 현재 집의 충전 가능한 범위내까지 현재방향(k)로 탐색
					while (cnt <= house.len) {
						int ni = house.i + di[k] * cnt;
						int nj = house.i + dj[k] * cnt;

						// 배열 끝에 도달하면 break
						if (ni < 0 || nj < 0 || ni >= 31 || nj >= 31)
							break;

						// 방문하지 않은 위치라면
						if (!visited[ni][nj]) {
							visited[ni][nj] = true; // 방문 처리 후
							candidate.add(new Point(ni, nj)); // candidate에 위치 추가

							int temp_sum = 0;
							boolean success = true;

							// 해당 위치에서 모든 집 연결 가능한지 파악하기
							for (int num = 0; num < n; num++) {
								int temp_len = check(ni, nj, num); // 충선소 위치 (ni, nj), 현재 집 인덱스(num)

								// 현재 집에서 충전소까지의 거리가 Integer.MAX_VALUE이면
								if (temp_len == Integer.MAX_VALUE) {
									success = false; // success 값 false 할당 후 달출
									break;
								}
								// 충전소와 집 거리의 합
								temp_sum += temp_len;
							}

							// success=true이면 충전소 1개로 모든 집 연결 가능
							if (success) {
								// 최소 거리 합 갱신
								if (ans > temp_sum)
									ans = temp_sum;
							}
						}

						cnt++;
					}
				}
			}

			// 충전소 한개로 모든 집 연결 가능하면 정답 출력후 다음 테스트 케이스로
			if (ans != Integer.MAX_VALUE) {
				sb.append("#" + tc + " " + ans + "\n");
				continue;
			}

			// 충전소 두 개 건설하기
			comb(new int[2], 0, 0);

			// ans가 갱신이 안 되었다면, 충전소 2개로 모든 집 연결 불가
			if (ans == Integer.MAX_VALUE) {
				ans = -1;
			}
			sb.append("#" + tc + " " + ans + "\n");

		}
		System.out.println(sb);

	}

	// 뽑은 충전소와 모든 집들간의 최소 거리 값 구하는 메서드
	private static void solve(int[] output) {
		// 뽑은 충전소 a와 b의 위치
		Point a = candidate.get(output[0]);
		Point b = candidate.get(output[1]);

		int total_len = 0;

		for (int i = 0; i < n; i++) {
			// i번째 현재 집과 충전소 a와 b까지의 거리 len_a, len_b 구하기
			int len_a = check(a.i, a.j, i);
			int len_b = check(b.i, b.j, i);

			// min: 두 충전소 중 가까운 거리
			int min = Math.min(len_a, len_b);

			// min이 Integer.MAX_VALUE이면 리턴
			// => 현재 집과 a, b 둘 중 어떤 충전소도 연결 불가
			if (min == Integer.MAX_VALUE) {
				return;
			}
			// 아니라면 거리합 구함
			else
				total_len += min;
		}

		// 최소 거리합 갱신
		if (total_len < ans)
			ans = total_len;
	}

	// 조합 메서드
	// 충전소 설치 위치 2개 뽑기
	private static void comb(int[] output, int depth, int start) {
		if (depth == 2) {
			solve(output);
			return;
		}
		for (int i = start; i < candidate.size(); i++) {
			output[depth] = i;
			comb(output, depth + 1, i + 1);
		}
	}

	// 충전소와 집간의 거리 반환해주는 메서드
	// i, j : 충전소 위치
	// house_num: houses 배열의 인덱스 값
	private static int check(int i, int j, int house_num) {

		House house = houses[house_num];
		int len = Math.abs(i - house.i) + Math.abs(j - house.j); // 주어진 충전소와의 거리
		// 충전 가능한 범위 내면 거리 반환
		if (len <= house.len)
			return len;
		// 아니면 Integer.MAX_VALUE 반환
		else
			return Integer.MAX_VALUE;

	}

}
