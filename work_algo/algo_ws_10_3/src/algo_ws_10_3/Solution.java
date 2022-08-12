package algo_ws_10_3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution{
	
	static int N;
	static int[] ingredients;
	static boolean[] isSelected;
	static int[][] map;
	static int min;
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		for(int test_case = 1; test_case <= T; test_case++) {
			
			sb.append("#" + test_case + " ");
			min = Integer.MAX_VALUE;
			N = Integer.parseInt(in.readLine());
			
			map = new int[N][N];
			for(int x=0; x<N; x++) {
				String[] split = in.readLine().split(" ");
				for(int y=0; y<N; y++) {
					map[x][y] = Integer.parseInt(split[y]); 
				}
			}
			
			// 1. N/2
			ingredients = new int[N];
			for(int i=0; i<N; i++)
				ingredients[i] = i; 
			int[] group1 = new int[N/2];
			int[] group2 = new int[N/2];
			isSelected = new boolean[N];
			comb(0, 0, group1, group2);
			
			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void comb(int cnt, int start, int[] group1, int[] group2) {
		
		if(cnt==N/2){
			
			int j = 0;
			for(int i=0; i<N; i++) {
				if(!isSelected[i])
					group2[j++] = ingredients[i]; 
			}
			
			int A = 0;
			int B = 0;
			for(int i=0; i<N/2; i++) {
				for(int k=i+1; k<N/2; k++) {
					A += map[group1[i]][group1[k]] + map[group1[k]][group1[i]];
				}
			}
			
			for(int i=0; i<N/2; i++) {
				for(int k=i+1; k<N/2; k++) {
					B += map[group2[i]][group2[k]] + map[group2[k]][group2[i]];
				}
			}
			
			if(min > Math.abs(A - B)){
				min = Math.abs(A - B);
			}
			
			
			return;
			
		}
		
		for(int i=start; i<N; i++) {
			group1[cnt] = ingredients[i]; 
			isSelected[i] = true;
			comb(cnt + 1, i + 1, group1, group2);
			isSelected[i] = false; 
		}
		
	}
	
}
