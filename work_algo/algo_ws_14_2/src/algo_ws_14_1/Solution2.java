package algo_ws_14_1;

import java.io.*;
import java.util.*;

public class Solution2 {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			// 고객의 수 N + 회사 + 집
			int N = Integer.parseInt(in.readLine()) + 2;

			// 좌표 저장
			String[] split = in.readLine().split(" ");
			Position[] P = new Position[N];
			for (int i = 0; i < N; i++) {
				P[i] = new Position(Integer.parseInt(split[i * 2]), Integer.parseInt(split[i * 2 + 1]));
			}

			/**
			 * 2. 알고리즘 풀기
			 */
			// 고객의 인덱스 번호(P 배열의 인덱스 번호)를 저장하는 배열 생성
			int[] input = new int[N - 2];
			for (int i = 2; i < N; i++) {
				input[i - 2] = i;
			}
			
			// 우리가 구해야 할 최솟값
			int min = Integer.MAX_VALUE;
			
			// nPn 일 때만 사용할 수 있다.
			// 전처리: 순열에 쓰일 수들을 오름차순 정렬
			Arrays.sort(input);

			do {
				//System.out.println(Arrays.toString(input));  // 순열 완성
				
				// 회사와 집에 대한 인덱스 번호를 붙인다.
				int[] indexes = new int[N];
				System.arraycopy(input, 0, indexes, 1, input.length);
				
				// 집 인덱스 번호 넣기
				indexes[indexes.length - 1] = 1;
				
				//System.out.println(Arrays.toString(indexes));
				
				// 순회하면서 거리 구하기
				int sum = 0;
				for (int i = 0; i < N - 1; i++) {
					Position p1 = P[indexes[i]];
					Position p2 = P[indexes[i + 1]];
					
					sum += Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
				}
				
				// 최솟값 갱신하기
				if (sum < min) {
					min = sum;
				}

			} while(np(input));

			/**
			 * 3. 정답 출력
			 */
			sb.append(min).append("\n");
		}

		System.out.println(sb);
	}

	// numbers 배열의 상태를 근거로 다음 순열 생성, 다음 순열 존재하면 true, 아니면 false
	public static boolean np(int[] numbers) {

		int N = numbers.length;

		// 1. 꼭대기 찾기
		int i = N - 1;
		while (i > 0 && numbers[i - 1] >= numbers[i]) {
			--i;
		}

		if (i == 0) {
			// 다음 순열을 만들 수 없는 이미 가장 큰 순열의 상태!
			return false;
		}

		// 2. 꼭대기의 바로 앞자리(i - 1)의 값을 크게 만들 교환값을 뒤쪽에서 찾기
		int j = N - 1;
		while (numbers[i - 1] >= numbers[j]) {
			--j;
		}

		// 3. i - 1 위치값과 j 위치값 교환
		swap(numbers, i - 1, j);

		// 4. i 위치부터 맨 뒤까지의 수열을 가장 작은 형태의 오름차순으로 변경
		int k = N - 1;
		while (i < k) {
			swap(numbers, i++, k--);
		}

		return true;
	}

	public static void swap(int[] numbers, int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
}

class Position {
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
