package com.ssfay.hw.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// snippet: [본인이 등록한 이름] 타이핑 후  ctrl + space
// 코드 정렬: ctrl + a 누른 후 ctrl + shift + i
// 자동 import: ctrl + shift + o

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