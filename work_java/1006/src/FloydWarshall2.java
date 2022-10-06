import java.util.Scanner;

import java.io.*;
import java.util.*;

public class FloydWarshall2 {

	private static final int INF = 999999;
	private static int N;

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();


		int[][] D = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				D[i][j] = sc.nextInt();
				if(i != j && D[i][j] == 0)
					D[i][j] = INF;
			}
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				if (k == i)
					continue;
				for (int j = 0; j < N; j++) {
					if (j == i || j == k)
						continue;
					
					D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(D[i][j] + " ");
			}
			System.out.println();
		}

	}

}
