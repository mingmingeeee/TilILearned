package algo_ws_03_3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		st = new StringTokenizer(in.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		/**
		 * 2. 알고리즘 풀기
		 */

		int[][] sum = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j=1; j<=N; j++) {
				sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + Integer.parseInt(st.nextToken()); 
			}
		}
		
		int result = 0;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			result = sum[x2][y2] - sum[x2][y1-1] - sum[x1-1][y2] + sum[x1-1][y1-1];
			sb.append(result).append("\n");
		}

		
		/**
		 * 3. 정답 출력
		 */

		System.out.println(sb);
	}

}
