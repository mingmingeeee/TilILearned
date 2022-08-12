package algo_ws_10_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main2 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
	
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		
		Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// 1. 절댓값 기준 오름차순 정렬
				if(Math.abs(o1) > Math.abs(o2)) {
					return Math.abs(o1) - Math.abs(o2);
				}
				// 2. 절댓값이 같다면 값 기준 오름차순 정렬
				else if(Math.abs(o1)==Math.abs(o2)) {
					return o1 - o2;
				}
				
				// 그 외에는 기본 오름차순 정렬 
				return -1;
			}
		});
		
		for(int i=0; i<N; i++) {
			
			
			// 숫자 하나를 읽는다.
			int num = Integer.parseInt(in.readLine());
			
			switch(num) {
			case 0: // 큐에서 절댓값이 가장 작은 값 출력 
				if(!queue.isEmpty()) {
					sb.append(queue.poll()).append("\n");
				}
				else {
					sb.append(0).append("\n");
				}
				break;
				
				default:
					queue.offer(num);
			}
			
		}
		
		System.out.println(sb);
	}

}
