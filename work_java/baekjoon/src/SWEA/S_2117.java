package SWEA;

import java.io.*;
import java.util.*;

public class S_2117 {
	
	// N: 5 이상 20 이하
	// 지불할 수 있는 비용 M: 1 이상 10 이하
	
	// 보안 회사 이익 = 집 개수 * M - 운영 비용
	// 운영 비용: K * K + (K - 1) * (K - 1) <- K: 서비스 영역 
	
	private static final int HOME = 1;
	private static final int BLANK = 0;
	private static int[][] map;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case<=T; test_case++) {
			
			sb.append("#" + test_case + " ");
			
			String[] split = in.readLine().split(" ");
			int M = Integer.parseInt(split[0]);
			int N = Integer.parseInt(split[1]);
			
			map = new int[M][M];
			
			for(int i=0; i<M; i++) {
				split = in.readLine().split(" ");
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(split[j]);
				}
			}
			
			/**
			 * 알고리즘 풀기 
			 */
			
			
			
		}
		
	}

}
