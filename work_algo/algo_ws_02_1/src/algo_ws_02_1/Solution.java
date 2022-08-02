package algo_ws_02_1;

import java.util.*;
import java.io.*;

/**
 * 원래: max, min, idx 다 했음 
 */

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 가장 높은 곳에 있는 상자 -> 가장 낮은 곳으로
		// 그 후 최고점 - 최저점

		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(in.readLine()); // 덤프 횟수

			StringTokenizer st = new StringTokenizer(in.readLine());
			int[] dumps = new int[100];
			for(int i=0; i<100; i++) {
				dumps[i] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < N; j++) { // 이렇게 하면
				Arrays.sort(dumps);
				dumps[dumps.length-1]--;
				dumps[0]++;
			}
			Arrays.sort(dumps); // 마지막에도 정렬시켜줘야 함
			
			System.out.println("#" + tc + " " + (dumps[dumps.length-1]-dumps[0]));
		}
		
	}

}
