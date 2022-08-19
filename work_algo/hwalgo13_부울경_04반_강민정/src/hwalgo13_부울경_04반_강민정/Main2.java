package hwalgo13_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main2 {
	
	static int[][] data;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		data = new int[N][N];
		for(int i=0; i<N; i++) {
			String line = in.readLine();
			for(int j=0; j<N; j++) {
				data[i][j] = line.charAt(j) - '0'; 
			}
		}
		
		cut(0, 0, N);
		
		System.out.println(sb);
		
	}
	
	// r: 시작 행 번호
	// c: 시작 열 번호
	// size: 한 변의 길이
	private static void cut(int r, int c, int size) {
		
		// 해당 영역의 색상 확인
		int sum = 0;
		for(int i=r; i<r+size; i++) {
			for(int j=c; j<c+size; j++) {
				sum += data[i][j];
			}
		}
		
		if(sum == size * size) { // 기저 조건
			sb.append(1);
		}
		else if(sum==0) { // 기저 조건
			sb.append(0);
		}
		else { // 유도 조건: 색상이 섞여 있는 경우 
			sb.append("(");
			int half = size / 2;
			cut(r, c, half);
			cut(r, c + half, half);
			cut(r + half, c, half);
			cut(r + half, c + half, half);
			sb.append(")");
		}
	}

}
