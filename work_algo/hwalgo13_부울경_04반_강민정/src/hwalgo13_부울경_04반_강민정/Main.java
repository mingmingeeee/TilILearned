package hwalgo13_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 숫자들이 한 곳에 많이 몰려 있으면 쿼드 트리에서는 압축하여 간단히 표현할 수 있음
		// 주어진 영상이 모두 0으로만 -> 압축 결과: 0
		// 주어진 영상이 모두 1로만 -> 압축 결과: 1
		// 0과 1이 섞여 있으면
		// 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 -> 4개로 나누어 압축

		int N = Integer.parseInt(in.readLine());

		int[][] data = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split("");
			for (int j = 0; j < N; j++) {
				data[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		cut(0, 0, N, data);
		
		System.out.println(sb);

	}

	static void cut(int x, int y, int size, int[][] data) {

		
		int white = 0;
		int black = 0;
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if(data[i][j] == 1)
					black++;
				else
					white++;
			}
		}
		
		if(black==size*size)
			sb.append(1);
		else if(white==size*size)
			sb.append(0);
		
		else {			
			int half = size / 2;
			sb.append("(");
			cut(x, y, half, data);
			cut(x, y + half, half, data);
			cut(x + half, y, half, data);
			cut(x + half, y + half, half, data);
			sb.append(")");
		}

	}

}
