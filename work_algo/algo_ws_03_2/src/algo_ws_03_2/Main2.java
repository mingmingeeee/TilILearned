package algo_ws_03_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

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
		// N과 M
		String[] split = br.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		
		/**
		 * 2. 알고리즘 풀기
		 */
		// N개의 수
		split = br.readLine().split(" ");
		int[] sums = new int[N+1];
		for(int i=0; i<N; i++) {
			sums[i + 1] = sums[i]+ Integer.parseInt(split[i]); 
		}
		
		// M개의 구간
		for(int i=0; i<M; i++) {
			split = br.readLine().split(" ");
			int start = Integer.parseInt(split[0]) - 1;
			int end = Integer.parseInt(split[1]);
			
			int answer = sums[end] - sums[start];
			sb.append(answer);
			sb.append("\n");
		}
		
		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);
	}

}
