package com.ssfay.hw.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(in.readLine());
		
		for(int i=1; i<=N; i++) {
			queue.offer(i);
		}
		
		while(queue.size()>1) {
			queue.poll();
			int n = queue.poll();
			
			queue.offer(n);
		}
		sb.append(queue.poll());
		System.out.println(sb);
		
	}
	
}