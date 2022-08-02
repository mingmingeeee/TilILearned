package algo_ws_02_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		/**
		 * 1. 입력 파일 객체화
		 */
		int T;
		T = Integer.parseInt(in.readLine());
		
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			
			int N = Integer.parseInt(in.readLine());
			
			int[][] data  = new int[N][N];
			for (int i = 0; i < N; i++) {
				String[] split = in.readLine().split("");
				for (int j = 0; j < N; j++) {
					data[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			/**
    		 * 2. 알고리즘 풀기
    		 */
			
			boolean isIncrease = true;
			int target = N/2;
			
			int sum = 0;
			int i = 0;
			int k = 0;
			while(k < N) {
				
				for(int l=N/2-i; l<2*i+1; l++) {
					sum += data[k][l];
				}
				
				
				if(isIncrease)
					i++;
				else
					i--;
					
				if(target == i)
					isIncrease = false;

				k++;
			}
	
			
			sb.append(sum).append("\n");
			
		}
		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);
	}
}
