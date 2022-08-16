package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;

public class B_13913 {
	
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String[] strings = in.readLine().split(" ");
		int N = Integer.parseInt(strings[0]); // 수빈이가 있는 위치
		int K = Integer.parseInt(strings[1]); // 동생이 있는 위치

		bfs(N, K, 0);
		
		System.out.println(sb);
	}

	static void bfs(int N, int K, int time) {

		Queue<Current> q = new ArrayDeque<>();
		ArrayList<Integer> arr = new ArrayList<>();
		q.offer(new Current(N, time, arr));

		boolean[] visited = new boolean[100_001];
		while (!q.isEmpty()) {

			Current cur = q.poll();
			
			visited[cur.x] = true;
			
			if (cur.x == K) {
				sb.append(cur.time + "\n");
				for(int n : cur.dot) {
					sb.append(n + " ");
				}
				sb.append("\n");
				return;
			}
			
			if (cur.x + 1 >= 0 && cur.x + 1 < 100_001 && !visited[cur.x+1]) {
				q.offer(new Current(cur.x + 1, cur.time + 1, cur.dot));
				visited[cur.x+1] = true;
			}

			if (cur.x - 1 >= 0 && cur.x - 1 < 100_001 && !visited[cur.x-1]) {
				q.offer(new Current(cur.x - 1, cur.time + 1, cur.dot));
				visited[cur.x-1] = true;
			}

			if (cur.x >= 0 && 2 * cur.x < 100_001 && !visited[cur.x*2]) {
				q.offer(new Current(2 * cur.x, cur.time + 1, cur.dot));
				visited[cur.x*2] = true;
			}

		}
	}

}

class Current {

	int x;
	int time;
	ArrayList<Integer> dot = new ArrayList<>();

	Current(int x, int time, ArrayList<Integer> dot) {
		this.x = x;
		this.time = time;
		this.dot.addAll(dot);
		this.dot.add(x);
	}

}
