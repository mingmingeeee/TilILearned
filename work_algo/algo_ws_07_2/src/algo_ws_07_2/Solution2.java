package algo_ws_07_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2 {

	public static int[] kyuCard; // 규영이가 뽑은 카드 (순서 O)
	public static int[] remainCard; // 규영이가 뽑고 남은 카드 (순서 X)
	public static int[] isCard; // 남은 카드로 인영이가 뽑은 카드 (순서 O)
	public static boolean[] isSelected; // 인영이의 카드 사용 유무
	private static int kyuWin; // 규영이가 이긴 횟수
	private static int kyuLose; // 규영이가 진 횟수

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

			// 초기화
			kyuWin = 0;
			kyuLose = 0;

			remainCard = new int[9]; // 규영이가 뽑고 남은 카드 (순서 X)
			isCard = new int[9]; // 남은 카드로 인영이가 뽑은 카드 (순서 O)
			isSelected = new boolean[9]; // 인영이의 카드 사용 유무

			// 카드를 뽑았는지 유무 (0번 인덱스 사용 안 함)
			boolean[] deck = new boolean[18 + 1];

			String[] split = in.readLine().split(" ");
			kyuCard = new int[9]; // 규영이가 가지고 있는 카드 (순서 0)

			for (int i = 0; i < 9; i++) {
				kyuCard[i] = Integer.parseInt(split[i]); // 규영이가 뽑은 카드 저장
				int pickNumber = kyuCard[i]; // 규영이가 뽑은 카드 번호
				deck[pickNumber] = true; // 뽑은 카드 표시
			}

			int deckIndex = 0;
			for (int i = 0; i < 9; i++) {
				while (deckIndex < 18) {
					deckIndex++;
					if (deck[deckIndex] == false) { // 뽑지 않은 카드면
						deck[deckIndex] = true; // 뽑고
						remainCard[i] = deckIndex; // 인영이가 가져간다.
						break;
					}
				}
			}

			/**
			 * 2. 알고리즘 풀기
			 */
			perm(0);

			sb.append(kyuWin).append(" ").append(kyuLose).append("\n");
		}

		/**
		 * 3. 정답 출력
		 */

		System.out.println(sb);
	}

	// cnt: 직전까지 뽑은 순열에 포함된 카드의 개수, cnt + 1 번째 해당하는 카드 뽑기
	public static void perm(int cnt) {

		// 기저 부분 (종료 조건)
		if (cnt == 9) {
			int kyuScore = 0; // 규영이 점수
			int inScore = 0; // 인영이 점수 
			
			// 규영이와 인영이가 각각 한 장씩 꺼내서 비교 
			for (int i = 0; i < 9; i++) {
				int kyu = kyuCard[i];
				int in = isCard[i];
				
				// 규영이가 이겼으면
				if (kyu > in)
					kyuScore += kyu + in; // 카드 합
				
				// 인영이가 이겼으면 
				else if (in > kyu)
					inScore += kyu + in; // 카드 합
			}
			
			// 최종 점수 비교하여 승패 결정
			if(kyuScore > inScore) {
				kyuWin++;
			}
			else if(kyuScore < inScore) {
				kyuLose++;
			}

			return;
		}

		for (int i = 0; i < 9; i++) {
			if (isSelected[i])
				continue;

			isCard[cnt] = remainCard[i];
			isSelected[i] = true;

			perm(cnt + 1);

			isSelected[i] = false;
		}

	}

}
