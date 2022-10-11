package hwalgo28_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main {

	// 손님이 먹을 수 있는 초밥 가짓수의 최댓값을 구하는 프로그램
	private static int[] sushi;
	private static int answer;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		int d = Integer.parseInt(split[1]);
		int k = Integer.parseInt(split[2]);
		int c = Integer.parseInt(split[3]);

		sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(in.readLine());
		}

		// 7 9 7 30 2 7 9 25
		start(new int[d + 1], k, N, c);
		
		System.out.println(answer);

	}

	private static void start(int[] visited, int k, int N, int c) {

		// k접시 초기화
		int cnt = 0;
		for (int i = 0; i < k; i++) {
			int ate = sushi[i];
			if (visited[ate] == 0) {
				answer++;
			}
			visited[ate]++;
		}

		cnt = answer;
		answer = visited[c] == 0 ? cnt + 1 : cnt;

		int start = k;
		while (true) {
			// 이 전 초밥 하나 -1 
			int index = sushi[(start - k) % N];
			visited[index]--;
			if(visited[index] == 0) cnt--;

			// 현재 먹을 초밥 +1
			index = sushi[start % N];
			if (visited[index] == 0)
				cnt++;
			visited[index]++;
			
			// 쿠폰 => 안 먹은 거였으면 +1, 아니면 원래대로
			answer = Math.max(answer, visited[c] == 0 ? cnt + 1 : cnt);
			
			// 다음 초밥으로 
			start++;
			
			// 기저 조건 
			if(start == (N - 1) + k) break;

		}

	}

}
