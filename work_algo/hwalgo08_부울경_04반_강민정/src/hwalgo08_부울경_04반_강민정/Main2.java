package hwalgo08_부울경_04반_강민정;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main2 {

	private static int N;
	private static int M;
	private static int K;
	private static int[][] RCS;  // 선택된 회전 연산을 저장하는 배열
	private static int[][] input;  // 회전 연산들을 저장하는 배열
	private static boolean[] isSelected;  // 순열에서 사용할 flag

	private static int[][] A;  // 문제에서 주어진 2차원 배열
	private static int min;  // 각 행의 합 중에서 최솟값

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */

		// 배열 크기 N, M, 회전 연산의 개수 K
		String[] split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		K = Integer.parseInt(split[2]);

		// 배열 A에 값 넣기
		A = new int[N + 1][M + 1];  // 1번 인덱스 부터 사용
		for (int i = 1; i <= N; i++) {
			split = in.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				A[i][j] = Integer.parseInt(split[j - 1]);
			}
		}

		// 회전 연산의 정보 r, c, s 배열
		input = new int[K][3];
		for (int i = 0; i < K; i++) {
			split = in.readLine().split(" ");
			int[] temp = new int[3];
			temp[0] = Integer.parseInt(split[0]);
			temp[1] = Integer.parseInt(split[1]);
			temp[2] = Integer.parseInt(split[2]);

			input[i] = temp;
		}

		// 초기화
		RCS = new int[K][3];
		isSelected = new boolean[K];
		min = 100 * 50;  // 한 칸에 들어갈 수 있는 최댓값 * 최대 열 수

		/**
		 * 2. 알고리즘 풀기
		 */
		perm(0);

		/**
		 * 3. 정답 출력
		 */
		sb.append(min);
		System.out.println(sb);
	}

	private static void perm(int cnt) {
		// 기저 부분 (종료 조건)
		if (cnt == K) {
			
			// A배열을 B배열로 복사
			int[][] B = new int[N + 1][M + 1];
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					B[i][j] = A[i][j];
				}
			}
			
			// 순열에 의해 RCS에 입력된 회전 연산 순서대로 실행
			for (int k = 0; k < K; k++) {

				// 모든 연산 중 하나를 선택하여 RCS 배열에 담는다.
				// 선택된 연산을 RCS 배열을 이용하여 수행한다.
				//RCS[0] = input[1];

				// 회전 연산의 정보 r, c, s
				int r = RCS[k][0];
				int c = RCS[k][1];
				int s = RCS[k][2];

				// 가장 왼쪽 윗칸
				int startX = r - s;
				int startY = c - s;

				// 가장 오른쪽 아랫칸
				int endX = r + s;
				int endY = c + s;

/*				System.out.println(startX + " " + startY);
				System.out.println(endX + " " + endY);*/

				// 돌려야 할 사각형 개수
				int rectCount = Math.min(endX - startX + 1, endY - startY + 1) / 2;

				for (int j = 0; j < rectCount; j++) {

					// 돌려야 할 사각형 기준점 변경
					startX = r - s + j;
					startY = c - s + j;
					endX = r + s - j;
					endY = c + s - j;

					int temp = A[startX][startY];  // 사각형 좌측 상단의 값을 임시저장

					// 좌변: 아래에서 위로 이동
					for (int i = startX; i < endX; i++) {
						A[i][startY] = A[i + 1][startY];
					}

					// 아랫변: 오른쪽에서 왼쪽 이동
					for (int i = startY; i < endY; i++) {
						A[endX][i] = A[endX][i + 1];
					}

					// 우변: 위에서 아래로 이동
					for (int i = endX; i > startX; i--) {
						A[i][endY] = A[i - 1][endY];
					}

					// 윗변: 왼쪽에서 오른쪽 이동
					for (int i = endY; i > startY; i--) {
						A[startX][i] = A[startX][i - 1];
					}

					// 임시 값 다시 넣기
					A[startX][startY + 1] = temp;
				}

/*				for (int i = 1; i < N + 1; i++) {
					for (int j = 1; j < M + 1; j++) {
						System.out.print(A[i][j] + " ");
					}
					System.out.println();
				}*/
			}
			
			// 각 행의 합을 구해서 최솟값 저장
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= M; j++) {
					sum += A[i][j];
				}
				
				if (sum < min) {
					min = sum;
				}
			}
			
			// A배열 초기화
			A = B;
			return;
		}

		// 유도 부분
		for (int i = 0; i < K; i++) {

			// 시도하는 연산이 선택되었는지 판단
			if (isSelected[i]) {
				continue;
			}

			// 선택되지 않았다면 연산을 사용
			RCS[cnt] = input[i];
			isSelected[i] = true;

			// 다음 수 뽑으러 가기
			perm(cnt + 1);

			// 사용했던 수에 대한 선택 되돌리기
			isSelected[i] = false;
		}
	}
}
