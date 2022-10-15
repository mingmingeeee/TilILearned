
import java.io.*;
import java.util.*;

// 참가자 이동 도중 블록 높이가 현재보다 높다면 올라가야 하고
// 낮다면 내려가야 함
// 준홍이 기준 => 난이도가 어떻게 매겨지는지 구하기 

// 올라갈 때 가장 심한 높이 변화
// 내려갈 때 가장 심한 높이 변화

public class Solution_6730 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");
			
			int N = Integer.parseInt(in.readLine());
			StringTokenizer st = new StringTokenizer(in.readLine(), " ");
			
			int[] blocks = new int[N];
			for(int i=0; i<N; i++) {
				blocks[i] = Integer.parseInt(st.nextToken());
			}
			
			int max_up = 0;
			int max_down = 0;
			
			for(int i=1; i<N; i++) {
				if(blocks[i - 1] < blocks[i]) {
					max_up = Math.max(max_up, blocks[i] - blocks[i - 1]);
				} else {
					max_down = Math.max(max_down, blocks[i - 1] - blocks[i]);
				}
			}

			sb.append(max_up + " " + max_down).append("\n");
		}
		
		System.out.println(sb);

	}

}
