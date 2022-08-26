package algo_ws_20_1;

import java.io.*;
import java.util.*;

public class Solution2 {

	private static final int CW = 1; // 시계 방향
	private static final int CCW = -1; // 반시계 방향

	private static int K; // 자석을 회전시키는 횟수
	private static int[][] magnets; // 자성 정보

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			// 자석을 회전 시키는 횟수
			K = Integer.parseInt(in.readLine());

			// 자성 정보
			magnets = new int[5][8]; // 자석 번호는 1부터 시작

			for (int i = 1; i <= 4; i++) {
				String[] split = in.readLine().split(" ");
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(split[j]);
				}
			}

			for (int i = 0; i < K; i++) {
				String[] split = in.readLine().split(" ");
				int magnetNo = Integer.parseInt(split[0]); // 자석 번호는 1번부터
				int direction = Integer.parseInt(split[1]); // 회전 방향(1: CW, -1: CCW)

				// 각각의 자석별 회전 정보를 배열에 저장
				int[] rotationInfo = new int[5]; // 자석 번호를 1번부터 사용
				rotationInfo[magnetNo] = direction;

				// 이 회전이 오른쪽, 왼쪽으로 어떻게 영향을 주는지 체크
				effectRight(magnetNo, direction, rotationInfo);
				effectLeft(magnetNo, direction, rotationInfo);

				// rotationInfo(자석의 회전 정보)를 이용하여 실제 자석 회전시키기
				for (int j = 1; j <= 4; j++) {
					int dir = rotationInfo[j];	
					
					switch (dir) {
					// 1. 시계방향
					case CW:
						// 자석의 맨 마지막 극성이 제일 처음으로 온다.
						int temp1 = magnets[j][7];
						for (int k = magnets[j].length - 1; k >= 1; k--) {
							magnets[j][k] = magnets[j][k - 1];
						}
						magnets[j][0] = temp1;
						break;
						
					// 2. 반시계방향
					case CCW:
						// 자석의 맨 처음 극성이 제일 마지막으로 온다.
						int temp2 = magnets[j][0];
						for (int k = 0; k < magnets[j].length - 1; k++) {
							magnets[j][k] = magnets[j][k + 1];
						}
						magnets[j][7] = temp2;
						break;
					}
				}


			}

			int answer = 0;
			for (int i = 1; i < magnets.length; i++) {
				// N:0, S:1
				answer += magnets[i][0] * Math.pow(2, i - 1);
			}

			sb.append(answer).append("\n");

		}

		System.out.println(sb);
	}

	private static void effectRight(int magnetNo, int direction, int[] rotationInfo) {

		// 기저 부분 (4번 자석의 오른쪽에는 더이상 자석이 없으므로 종료)
		if (magnetNo == 4) {
			return;
		}

		// 내 자석의 2번 극성과 오른쪽 자석의 6번의 극성이 다르면 회전
		// rotationInfo에 회전 정보를 저장
		if (magnets[magnetNo][2] != magnets[magnetNo + 1][6]) {
			rotationInfo[magnetNo + 1] = direction * -1; // -1은 반대 방향 회전

			// 재귀 호출하여 다음 오른쪽 자석으로 이동
			effectRight(magnetNo + 1, direction * -1, rotationInfo); // -1은 반대 방향 회전
		}

	}


	private static void effectLeft(int magnetNo, int direction, int[] rotationInfo) {

		// 기저 부분 (4번 자석의 왼쪽에는 더이상 자석이 없으므로 종료)
		if (magnetNo == 1) {
			return;
		}

		// 내 자석의 2번 극성과 오른쪽 자석의 6번의 극성이 다르면 회전
		// rotationInfo에 회전 정보를 저장
		if (magnets[magnetNo - 1][2] != magnets[magnetNo][6]) {
			rotationInfo[magnetNo - 1] = direction * -1; // -1은 반대 방향 회전

			// 재귀 호출하여 다음 오른쪽 자석으로 이동
			effectLeft(magnetNo - 1, direction * -1, rotationInfo); // -1은 반대 방향 회전
		}

	}

}
