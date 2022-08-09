package algo_ws_06_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
// 연산 => stack에 숫자 넣어놓고 연산자 만나면 나와서 계산함 = 단말 => 숫자, 중간 => 연산자
// 후위 => 좌, 우, 부 
import java.util.Arrays;

public class Solution {

	static int TotalWin;
	static int Totaloose;

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			/**
			 * 1. 입력 파일 객체화
			 */
			sb.append("#" + test_case + " ");

			int[] cards = new int[19];
			int[] gyu = new int[9];
			int[] in_y = new int[9];
			/**
			 * 2. 알고리즘 풀기
			 */

			String[] strings = in.readLine().split(" ");
			for (int i = 0; i < 9; i++) {
				int N = Integer.parseInt(strings[i]);
				gyu[i] = N;
				cards[N]++;
			}

			int idx = 0;
			for (int i = 1; i <= 18; i++) {
				if (cards[i] == 0) {
					in_y[idx++] = i;
				}
			}

			boolean[] isSelected = new boolean[9];
			int[] nums = new int[9];
			TotalWin = 0;
			Totaloose = 0;

			perm(0, in_y, gyu, nums, isSelected);

			sb.append(TotalWin + " " + Totaloose);
			sb.append("\n");

		}

		/**
		 * 3. 정답 출력
		 */

		System.out.println(sb);
	}


	public static void perm(int cnt, int[] in_y, int[] gyu, int[] nums, boolean[] isSelected) {

		if (cnt == 9) {
			int sum_g = 0, sum_i = 0;
			for (int i = 0; i < 9; i++) {
				if (gyu[i] > nums[i])
					sum_g += gyu[i] + nums[i];
				else if (gyu[i] < nums[i])
					sum_i += gyu[i] + nums[i]; 
			}
			if (sum_g < sum_i)
				Totaloose++;
			else if (sum_g > sum_i)
				TotalWin++;

			return;
		}

		for (int i = 0; i < 9; i++) {
			if (isSelected[i])
				continue;
			nums[cnt] = in_y[i];
			isSelected[i] = true;
			perm(cnt + 1, in_y, gyu, nums, isSelected);
			isSelected[i] = false;
		}
	}

}
