package algo_ws_10_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class B_2075 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());


		Queue<Integer> q = new PriorityQueue<>(new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
			
		});
		
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for(int j=0; j<N; j++) {
				q.offer(Integer.parseInt(split[j]));
			}
		}
		
		for(int i=0; i<N-1; i++) {
			q.poll();
		}
		System.out.println(q.poll());
	}

}
