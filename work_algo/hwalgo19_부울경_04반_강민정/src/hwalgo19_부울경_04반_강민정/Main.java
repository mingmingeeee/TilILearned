package hwalgo19_부울경_04반_강민정;

import java.io.*;
import java.util.*;

// 도시를 모두 거쳐 다시 돌아오는 순회 여행
// 비용 행렬 i, j로 주어짐
// 도시의 수: 2 ~ 10

public class Main {

	// 순열
	// a-b-c-d-e : ab + bc + cd + de
	// e-d-c-b-a : ed + dc + cb + ba

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		int[][] W = new int[N][N];

		int[] cities = new int[N];
		for (int i = 0; i < N; i++) {
			cities[i] = i;
		}

		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(split[j]);
			}
		}

		int answer = Integer.MAX_VALUE;
		do {
			boolean go = true;
			int cost = 0;
			for(int i=0; i<N-1; i++) {
				if(W[cities[i]][cities[i+1]]==0) go = false; // 길 없음 표시
				cost += W[cities[i]][cities[i+1]];
			}
			if(!go || W[cities[cities.length-1]][cities[0]] == 0) continue; // 길 없을 때 continue
			cost += W[cities[cities.length-1]][cities[0]];
			answer = Math.min(answer, cost);
		} while (np(cities));
		
		sb.append(answer);
		System.out.println(sb);

	}

	private static boolean np(int[] cities) {
		int N = cities.length;

		int i = N - 1;
		while (i > 0 && cities[i - 1] >= cities[i]) {
			--i;
		}
		if (i == 0)
			return false;

		int j = N - 1;
		while (cities[i - 1] >= cities[j]) {
			--j;
		}
		swap(cities, i - 1, j);

		int k = N - 1;
		while (i < k) {
			swap(cities, i++, k--);
		}

		return true;

	}

	private static void swap(int[] cities, int i, int j) {
		int tmp = cities[i];
		cities[i] = cities[j];
		cities[j] = tmp;
	}

}
