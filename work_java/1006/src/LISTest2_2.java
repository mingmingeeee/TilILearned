import java.util.Scanner;

import java.io.*;
import java.util.*;

public class LISTest2_2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] arr = new int[N]; // 수 저장 테이블
		int[] C = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int size = 0;
		for (int i = 0; i < N; i++) {
			int pos = Arrays.binarySearch(C, 0, size, arr[i]);
			
			if(pos >= 0) continue; // 들어가는 애가 이미 C에 있다면 continue
			
			pos = Math.abs(pos) - 1;
			C[pos] = arr[i];
			
			if(pos == size)
				size++;
		}
		
		System.out.println(size);

	}

}
