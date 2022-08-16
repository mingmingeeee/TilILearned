package baekjoon;

import java.io.*;
import java.util.*;

public class B_14226 {

	static int S;
	// clipboard, monitor
	static boolean visited[][] = new boolean[1001][1001];

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		S = Integer.parseInt(in.readLine());

		bfs();

	}

	static void bfs() {
		// 클립 보드에 몇 개 있는지 알아야 함 
		// 화면에 몇 개 있는지 알아야 함 
		// 1. 화면에 있는 이모티콘 복사
		// 2. 클립보드에 있는 모든 이모티콘 붙여넣기 
		// 3. 화면에 있는 이모티콘 중 하나 삭제 -> -1
		Queue<emoticon> q = new ArrayDeque<>(); // 화면에 있는 이모티콘 수 
		
		q.offer(new emoticon(0, 1, 0));
		visited[0][1] = true;
		
		while(!q.isEmpty()) {
			
			emoticon tmp = q.poll();
			
			if(tmp.monitor==S) {
				System.out.println(tmp.depth);
				return;
			}
			
			// 1. 복사
			q.offer(new emoticon(tmp.monitor, tmp.monitor, tmp.depth + 1));
			
			// 2. 클립보드 -> 화면 
			if(tmp.clipboard > 0 && tmp.clipboard + tmp.monitor < 1001
					&& !visited[tmp.clipboard][tmp.monitor + tmp.clipboard]) {
				q.offer(new emoticon(tmp.clipboard, tmp.clipboard + tmp.monitor, tmp.depth + 1));
				visited[tmp.clipboard][tmp.clipboard + tmp.monitor] = true;
			}
			
			// 3. 화면 이모티콘 하나 삭제
			if(tmp.monitor>=1 && !visited[tmp.clipboard][tmp.monitor-1]) {
				q.offer(new emoticon(tmp.clipboard, tmp.monitor-1, tmp.depth+1));
				visited[tmp.clipboard][tmp.monitor-1] = true;
			}
				
		}
		
	}

}

class emoticon {
	int clipboard;
	int monitor;
	int depth;

	emoticon(int clipboard, int monitor, int depth) {
		this.clipboard = clipboard;
		this.monitor = monitor;
		this.depth = depth;
	}
}
