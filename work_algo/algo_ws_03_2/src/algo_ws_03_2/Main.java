package algo_ws_03_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/**
		 * 0. 입력파일 읽어들이기
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		/**
		 * 1. 입력 파일 객체화
		 */
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		/**
		 * 2. 알고리즘 풀기
		 */
		int[] sum = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(st.nextToken()); 
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			
			sb.append(sum[m] - sum[l-1]).append("\n");
		}
		
		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);
	}

}
