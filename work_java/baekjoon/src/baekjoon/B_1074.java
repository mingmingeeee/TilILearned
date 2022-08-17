package baekjoon;

import java.io.*;
import java.util.*;

public class B_1074 {

	static int[] dx = { 0, 0, 1, 1 };
	static int[] dy = { 0, 1, 0, 1 };

	static int r;
	static int c;
	static int count;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		r = Integer.parseInt(split[1]);
		c = Integer.parseInt(split[2]);
		
		
		int size = (int) Math.pow(2, N);

		Divide_and_conquer(0, 0, size);

	}

	static void Divide_and_conquer(int x, int y, int size) {

		if(size == 2) {
			
			for(int i=0; i<dx.length; i++) {
				
				int testX = x + dx[i];
				int testY = y + dy[i];

				if(testX == r && testY == c) {
					System.out.println(count);
					System.exit(0);
				}
				
				count++;
			}
			return;
		}
		
		if(x > r || x + size < r || y > c || y + size < c) {
			count += size*size;
			return;
		}
		
		int half = size/2;
		
		Divide_and_conquer(x, y, half);
		Divide_and_conquer(x, y + half, half);
		Divide_and_conquer(x + half, y, half);
		Divide_and_conquer(x + half, y + half, half);

	}

}
