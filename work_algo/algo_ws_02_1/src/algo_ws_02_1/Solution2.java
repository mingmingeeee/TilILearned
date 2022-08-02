package algo_ws_02_1;

import java.util.*;
import java.io.*;

public class Solution2 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 가장 높은 곳에 있는 상자 -> 가장 낮은 곳으로
		// 그 후 최고점 - 최저점
		
		/**
		 * 1. Test Case
		 */
		for (int tc = 1; tc <= 10; tc++) {
			
			sb.append("#" + tc + " ");
			
			// 덤프 횟수
			int N = Integer.parseInt(in.readLine()); // 덤프 횟수
			int maxDumpCount = N;
			// 각 상자의 높이 값
			String[] split = in.readLine().split(" ");
			int[] heights = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
			
			/**
			 * 2. 알고리즘 풀기 
			 */
			int dumpCount = 0; // 덤프 횟수
			
			while(true) { // 이렇게 하면 한 번에 다 가능
				Arrays.sort(heights); // 오름차순 정렬 
				
				// 가장 높은 곳과 가장 낮은 곳의 차이가 최대 1 이내 (0 또는 1) 인가?
				boolean isDiffeLessThanOne = heights[99] - heights[0] <= 1;
				
				// 최대 덤프 횟수 이상 수행 하였는가?
				boolean isAboveMax = dumpCount >= maxDumpCount;
				
				// max일 때 안 바꿈
				if(isDiffeLessThanOne || isAboveMax){
					sb.append(heights[99] - heights[0]);
					break;
				}
				
				// 덤프 수행
				heights[99] -= 1;
				heights[0] += 1;
				dumpCount++;
			}
			sb.append("\n");
			
		}
		/**
		 * 3. 정답 출력 
		 */
		System.out.println(sb);
	}

}
