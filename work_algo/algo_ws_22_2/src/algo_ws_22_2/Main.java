package algo_ws_22_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		int[] D = new int[N + 1]; // i를 1로 만들 때 연산하는 횟수의 최소 값

		D[0] = 0; 
		D[1] = 0;

		for (int i = 2; i <= N; i++) {
			D[i] = D[i - 1] + 1;
			if(i % 2 == 0) {
				D[i] = Math.min(D[i], D[i / 2] + 1);
			}
			if (i % 3 == 0) {
				D[i] = Math.min(D[i], D[i / 3] + 1);
			}
		}
		
		System.out.println(D[N]);
		
	}

}