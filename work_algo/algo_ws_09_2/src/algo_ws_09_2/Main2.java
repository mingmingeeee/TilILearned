package algo_ws_09_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Character.Subset;

public class Main2 {

	private static int N; // 재료의 가지 수
	private static int[] S; // 재료의 신맛들을 저장할 배열
	private static int[] B; // 재료의 쓴맛들을 저장할 배열
	private static boolean[] isSelected; // 부분집합에서 사용할 flag

	private static int min; // 신맛과 쓴맛의 차이의 최솟값

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 재료의 개수
		N = Integer.parseInt(in.readLine());

		// 신 맛 배열
		S = new int[N];

		// 쓴 맛 배열
		B = new int[N];

		// 신 맛과 쓴 맛
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			S[i] = Integer.parseInt(split[0]);
			B[i] = Integer.parseInt(split[1]);
		}

		// 초기화
		min = Integer.MAX_VALUE;
		isSelected = new boolean[N];

		// 부분 집합으로 풀기
		Subset(0);
		System.out.println(min);

	}

	public static void Subset(int index) {
		if (index == N) {

			int resultS = 1; // 곱할 거니까 초기값 1로 설정
			int resultB = 0; // 더할 거니까 초기값 0으로 설정

			int selectedCnt = 0; // 문제에서 적어도 하나 이상 재료를 선택해야 하므로 선택 수 카운트
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					selectedCnt++;
					resultS *= S[i];
					resultB += B[i];
				}
			}
			
			if(selectedCnt > 0) { // 재료가 하나 이상일 경우
				int result = Math.abs(resultS - resultB);
				if(result < min)
					min = result;
			}
			
			return;

		}

		isSelected[index] = true;
		Subset(index + 1);

		isSelected[index] = false;
		Subset(index + 1);

	}

}
