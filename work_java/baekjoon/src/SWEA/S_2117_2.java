package SWEA;

import java.io.*;
import java.util.*;

// 마름모 표현 -> 중앙에서 모든 곳의 거리가 같음 

public class S_2117_2 {
	
	private static int N;
	private static int M;
	private static int[][] map;
	private static int home;
	private static int result;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int test_case=1; test_case <=T; test_case++) {
			
			sb.append("#" + test_case + " ");
			
			
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken()); // k의 최댓값 = N + 1
            M = Integer.parseInt(st.nextToken()); // 하나의 집이 지불할 수 있는 비용
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            
            result = Integer.MAX_VALUE;
            for(int k = 1; k <= N + 1; k++) {
            	// i, j 한 개가 마름모의 중심 
            	for(int i=0; i<N; i++) {
            		for(int j=0; j<N; j++) {
            			home = 0;
            			solve(k, i, j);
            		}
            	}
            }
            
            sb.append(home).append("\n");
            
		}
		System.out.println(sb);
		
	}
	
	private static void solve(int k, int x, int y) {
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				
				int distance = Math.abs(i - x) + Math.abs(j - y);
				if(distance <= k - 1 && map[i][j] == 1) {
					home++;
				}
				
			}
		}
		
		int benefit = (M * home) - (k*k + (k-1)*(k-1));
		
		if(benefit >= 0) {
			if(result < home)
				result = home;
		}
		
	}

}
