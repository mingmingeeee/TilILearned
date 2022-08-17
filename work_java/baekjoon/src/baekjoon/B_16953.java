package baekjoon;

import java.io.*;
import java.util.*;

public class B_16953 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		long A = Integer.parseInt(split[0]);
		long B = Integer.parseInt(split[1]);

		bfs(A, B);

	}

	public static void bfs(long A, long B) {

		Queue<Long> q = new ArrayDeque<>();
		q.offer(A);

		int depth = 1;
		while (!q.isEmpty()) {

			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				long tmp = q.poll();
				
				if(tmp == B) {
					System.out.println(depth);
					System.exit(0);
				}
				
				// 1. 2 곱하기
				if(tmp*2<=B)
					q.offer(tmp * 2);
				
				// 2. 1을 수 가장 오른쪽에 추가하기 
				if(tmp*10+1<=B)
					q.offer(tmp*10 + 1);
				
			}

			depth++;
		}
		
		System.out.println(-1);

	}

}
