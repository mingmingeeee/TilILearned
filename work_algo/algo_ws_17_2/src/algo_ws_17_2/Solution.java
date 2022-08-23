package algo_ws_17_2;

import java.io.*;
import java.util.*;

public class Solution {

	// 서로소 집합

	static void make(int V) {

		parents = new int[V];
		for (int i = 0; i < V; i++) {
			parents[i] = i;
		}

	}

	static int find(int a) {

		if (parents[a] == a)
			return a;

		return parents[a] = find(parents[a]);

	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return false;
		}

		parents[bRoot] = aRoot;

		return true;
	}

	private static int[] parents;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");

			int n = Integer.parseInt(split[0]); // 정점 개수
			int m = Integer.parseInt(split[1]); // 연산 수

			make(n);

			// 합집합: 0
			// 같은 집합인지: 1
			for (int i = 0; i < m; i++) {
				split = in.readLine().split(" ");
				int cal = Integer.parseInt(split[0]);
				int a = Integer.parseInt(split[1]) - 1;
				int b = Integer.parseInt(split[2]) - 1;

				if (cal == 0) {
					union(a, b);
				} else if (cal == 1) {
					if(find(a) == find(b))
						sb.append(1);
					else
						sb.append(0);
				}
			}
			
			sb.append("\n");

		}
		
		System.out.println(sb);

	}

}
