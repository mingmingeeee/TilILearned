package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class B_13549 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] strings = in.readLine().split(" ");
		int N = Integer.parseInt(strings[0]); // 수빈이가 있는 위치
		int K = Integer.parseInt(strings[1]); // 동생이 있는 위치

		bfs(N, K);

	}

	public static void bfs(int N, int K) {

		PriorityQueue<Cur> q = new PriorityQueue<>();
		q.offer(new Cur(N, 0));

		boolean[] visited = new boolean[100_001];
		visited[N] = true;
		
		while (!q.isEmpty()) {

			Cur x = q.poll();
			visited[x.x] = true;

			if (x.x == K) {
				System.out.println(x.time);
				return;
			}

			if (x.x >= 0 && 2 * x.x < 100_001 && !visited[2 * x.x]) {
				q.offer(new Cur(2 * x.x, x.time));
			}

			if (x.x + 1 >= 0 && x.x + 1 < 100_001 && !visited[x.x + 1]) {
				q.offer(new Cur(x.x + 1, x.time + 1));
			}

			if (x.x - 1 >= 0 && x.x - 1 < 100_001 && !visited[x.x - 1]) {
				q.offer(new Cur(x.x - 1, x.time + 1));
			}

		}

	}

}

class Cur implements Comparable<Cur> {
	int x;
	int time;

	Cur(int x, int time) {
		this.x = x;
		this.time = time;
	}
	
	@Override
	public int compareTo(Cur o) {
		// TODO Auto-generated method stub
		return this.time - o.time;
	}
}