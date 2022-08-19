package hwalgo15_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]);
		int K = Integer.parseInt(split[1]);

		boolean[] visited = new boolean[100_000 + 1];

		sb.append(bfs(N, K, visited));
		
		System.out.println(sb);

	}

	private static int bfs(int N, int K, boolean[] visited) {

		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(N);
		visited[N] = true;

		int depth = 0;
		while (!queue.isEmpty()) {

			int size = queue.size();

			for (int i = 0; i < size; i++) {

				int tmp = queue.poll();
				
				if(tmp == K)
					return depth;

				if (range(tmp - 1) && !visited[tmp-1]){
					visited[tmp-1] = true;
					queue.offer(tmp - 1);
				}

				if (range(tmp + 1) && !visited[tmp+1]) {
					visited[tmp+1] = true;
					queue.offer(tmp + 1);
				}

				if (range(2 * tmp) && !visited[2*tmp]) {
					visited[2*tmp] = true;
					queue.offer(2 * tmp);
				}

			}

			depth++;

		}

		return 0;

	}

	private static boolean range(int n) {

		if (0 <= n && n < 100_001) {
			return true;
		}
		return false;

	}

}
