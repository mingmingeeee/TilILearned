package algo_ws_14_1;

import java.io.*;
import java.util.*;

public class Solution {
	
	static int N;
	static int[][] people;
	static int startx, starty, endx, endy;
	static int[] n;
	static int min;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			sb.append("#" + test_case + " ");
			
			N = Integer.parseInt(in.readLine());
			people = new int[N][2];
			n = new int[N];
			
			String[] split = in.readLine().split(" ");
			startx = Integer.parseInt(split[0]);
			starty = Integer.parseInt(split[1]);
			endx = Integer.parseInt(split[2]);
			endy = Integer.parseInt(split[3]);

			int j = 0;
			for(int i=4; i<split.length; i+=2) {
				people[j][0] = Integer.parseInt(split[i]);
				people[j++][1] = Integer.parseInt(split[i + 1]);
			}
			
			min = Integer.MAX_VALUE;
			perm(0, 0, 0);
			
			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}
	
	static void perm(int cnt, int flag, int sum) {
		
		if(sum >= min) return;
		
		if(cnt == N) {
			sum += Math.abs(endx - people[n[N-1]][0]) + Math.abs(endy - people[n[N-1]][1]);
			
			if(min > sum)
				min = sum;

			return;
		}
		
		for(int i=0; i<N; i++) {
			if((flag & 1 << i) != 0) continue;
			
			n[cnt] = i; 
			
			int distance = 0;
			if(cnt == 0) {
				distance = Math.abs(startx - people[n[0]][0]) + Math.abs(starty - people[n[0]][1]);
			} 
			else if(cnt > 0) {
				distance = Math.abs(people[n[cnt-1]][0] - people[n[cnt]][0]) + Math.abs(people[n[cnt-1]][1] - people[n[cnt]][1]);
			}
			
			perm(cnt + 1, flag | 1 << i, sum + distance);
		}
		
	}

}
