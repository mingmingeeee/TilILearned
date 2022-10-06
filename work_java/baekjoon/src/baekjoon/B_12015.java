package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class B_12015 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] arr = new int[N]; // 수 저장 테이블
		int[] C = new int[N]; // 동적 테이블 : 해당(k) 길이를 만족하는 자리(k자리)에 오는 수의 최소 값

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int size = 0;

		for (int i = 0; i < N; i++) {
			// Arrays.binarySearch(a, fromIndex, toIndex, key)
			int pos = Arrays.binarySearch(C, 0, size, arr[i]);
			
			// C에서 값을 찾음
			if(pos >= 0) continue;
			
			// 못 찾음
			int insertPos = Math.abs(pos) - 1; // 맨 뒤 또는 기존 원소 대체 자리
			C[insertPos] = arr[i];
			
			if(insertPos == size) {
				size++;
			}
		}
		
		System.out.println(size);

	}
}
