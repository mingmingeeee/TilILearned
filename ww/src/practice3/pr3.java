package practice3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class pr3 {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("ArrayTest_31_input.txt")); // 문제에서 주어진 Input 데이터 파일명 작성

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine()); // 테스트 케이스

		for (int test_case = 1; test_case <= T; test_case++) {
			System.out.print("#" + test_case + " ");

			// 여기서 부터 알고리즘 작성

			// 연못의 크기 & 소금쟁이 수
			String[] split1 = in.readLine().split(" ");
			int N = Integer.parseInt(split1[0]);
			int num = Integer.parseInt(split1[1]);

			int[][] pond = new int[N][N]; // 연못

			// 배열 초기화
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					pond[i][j] = 0;
				}
			}
			int flag = 0;
			// 소금쟁이
			int[][] sogum = new int[num][3];
			for (int i = 0; i < sogum.length; i++) {
				split1 = in.readLine().split(" ");
				sogum[i][0] = Integer.parseInt(split1[0]);
				sogum[i][1] = Integer.parseInt(split1[1]);
				sogum[i][2] = Integer.parseInt(split1[2]);

				int x = sogum[i][0];
				int y = sogum[i][1];

				pond[sogum[i][0]][sogum[i][1]] = 1;
				for (int k = 3; k >= 1; k--) {
					if (sogum[i][2] == 1) {
						x += k;
					} else if (sogum[i][2] == 2) {
						y += k;
					}

					if (x >= 0 && x < N && y >= 0 && y < N) {

						if (pond[x][y] == 0) {
							pond[x][y] = 1; // 이미 뛰었던 자리 표시
						} else if (pond[x][y] == 1) {// 뛰었던 자리라면
							System.out.println(i + 1);
							flag = 1;
							break;
						}

					}
					else
						break;
				}

			}
			if (flag == 0)
				System.out.println(0);
		}
	}

}
