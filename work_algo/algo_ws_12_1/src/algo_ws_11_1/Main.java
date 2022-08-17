package algo_ws_11_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	static int min = 1667;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder(1000);
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i <= 1667; i++) {
			for (int j = 0; j <= 1667; j++) {
				if (3 * i + 5 * j == N) {
					if (i + j < min) {
						min = i + j;
					}
				}
				
			}
		}
		if (min == 1667) sb.append(-1);
		else sb.append(min);
		
		System.out.println(sb);
	}


}