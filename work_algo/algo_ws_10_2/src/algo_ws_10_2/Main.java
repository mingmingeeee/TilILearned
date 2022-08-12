package algo_ws_10_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

// 절대값 힙
// 배열에서 절댓값이 가장 작은 값 출력, 그 값 배열에서 제거
// 절대값 가장 작은 값 여러개: 가장 작은 수 출력, 그 값 배열 제거 

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());
		
		PriorityQueue<Heap> q = new PriorityQueue<>(new Comparator<Heap>() {
			
			@Override
			public int compare(Heap o1, Heap o2) {
				
				if(o1.abs==o2.abs)
					return o1.data - o2.data;
				
				return o1.abs - o2.abs;
			}
		});
			
		for (int i = 0; i < T; i++) {
			int n = Integer.parseInt(in.readLine());
			if (n != 0)
				q.add(new Heap(Math.abs(n), n));
			if (n == 0) {
				if(!q.isEmpty())
					sb.append(q.poll().data).append("\n");
				else
					sb.append(0).append("\n");
			}
		}

		System.out.println(sb);
	}

}

class Heap {

	int abs;
	int data;

	public Heap(int abs, int data) {
		this.abs = abs;
		this.data = data;
	}

//	@Override
//	public int compareTo(Heap o) {
//		// TODO Auto-generated method stub
//
//		if (this.abs == o.abs)
//			return this.data - o.data;
//
//		return this.abs - o.abs;
//	}

}