package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class B_1158 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] strings = in.readLine().split(" ");
		int N = Integer.parseInt(strings[0]);
		int K = Integer.parseInt(strings[1]);
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		for(int i=1; i<=N; i++)
			queue.offer(i);

		int i = 0;
		sb.append("<");
		while(!queue.isEmpty()) {
			int n = queue.poll();
			if(i==K-1) {
				if(queue.isEmpty())
					sb.append(n + ">");
				else
					sb.append(n + ", ");
				i = 0;
				continue;
			}
			queue.offer(n);
			i++;
		}
		
		System.out.println(sb);
	}

}
