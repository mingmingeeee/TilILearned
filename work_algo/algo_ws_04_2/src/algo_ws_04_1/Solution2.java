package algo_ws_04_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution2 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for(int i = 0; i < 10; i++) {
			String test_case = in.readLine();
			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");

			Queue<Integer> queue = new ArrayDeque<>();
			for (int j = 0; j < 8; j++) {
				queue.offer(Integer.parseInt(split[j]));
			}

			int cnt = 1;
			
			while (i < 10) {
				// 큐에서 맨 앞의 요소 하나를 꺼낸다.
				int poll = queue.poll();
				int result = poll - cnt;

				if (result > 0) {
					queue.offer(result);
					cnt = cnt % 5 + 1;
				} else {
					result = 0;
					queue.offer(result);
					break;
				}
			}

			while (!queue.isEmpty()) {
				int poll = queue.poll();
				sb.append(poll);
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
