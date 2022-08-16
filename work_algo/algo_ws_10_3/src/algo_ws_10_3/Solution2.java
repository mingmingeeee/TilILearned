package algo_ws_10_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {

	private static int N; // 식재료의 수
	private static int R; // A 음식에 사용할 식재료 수
	private static int[][] S; // 시너지
	private static int[] numbers; // N개의 식재료 중 A 음식이 사용할 식재료 체크
	private static int min; // 두 음식 간의 맛의 차이 최소 값

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {

			sb.append("#" + test_case + " ");

			// 식재료의 수
			N = Integer.parseInt(in.readLine());

			// A 음식에 사용할 식재료 수
			R = N / 2;

			// 시너지
			S = new int[N][N];

			// N개의 식재료 중 A 음식이 사용할 식재료 체크
			numbers = new int[N];

			// 최솟값 초기화
			min = Integer.MAX_VALUE;

			// 시너지 값 2차원 배열에 저장
			for (int x = 0; x < N; x++) {
				String[] split = in.readLine().split(" ");
				for (int y = 0; y < N; y++) {
					S[x][y] = Integer.parseInt(split[y]);
				}
			}

			// 1. N/2
			// 조합: nCr (A 음식을 위한 식재료 선택 후, 남은 재료는 B 음식)
			comb(0, 0);

			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}

	private static void comb(int cnt, int start) {

		// 기저 부분 (종료 조건)
		if(cnt == R) {
			// 각 음식별 식재료 
			List<Integer> listA = new ArrayList<>();
			List<Integer> listB = new ArrayList<>();
			
			// A 음식 식재료 목록에 저장
			boolean[] isSelected = new boolean[N]; // A 음식에서 사용한 식재료 체크
			for(int i=0; i<R; i++) {
				listA.add(numbers[i]);
				isSelected[numbers[i]] = true;
			}
			// 남은 식재료 들을 B 음식 식재료 목록에 저장
			for(int i=0; i<N; i++) {
				if(!isSelected[i]) {
					listB.add(i); // B 음식 식재료로 추가
				}
			}
			
			int A = 0; // A 음식 시너지의 합
			int B = 0; // B 음식 시너지의 합
			
			// 각 음식의 시너지 합 구하기 (중복 계산 안되게 잘 하기)
			for(int i=0; i<R-1; i++) {
				for(int j=1; j<R; j++) {
					A += S[listA.get(i)][listA.get(j)] + S[listA.get(j)][listA.get(i)];
					B += S[listB.get(i)][listB.get(j)] + S[listB.get(j)][listB.get(i)];
				}
			}
			
			// 맛의 차이를 구하고 최소값을 저장
			int abs = Math.abs(A - B);
			if(abs < min) {
				min = abs;
			}
			return;
			
		}
		
		// 유도 부분
		for (int i = start; i < N; i++) { // 선택지 (i는 A음식 식재료의 인덱스 번호) 

			numbers[cnt] = i;

			// 다음 수 뽑으러 가기
			comb(cnt + 1, i + 1);

		}

	}

}
