package algo_ws_17_2;

import java.io.*;
import java.util.*;

public class DisjointSetTest {

	// 서로소 집합

	static void make() {

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
	private static int V;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		V = 5;
		make();

		System.out.println(Arrays.toString(parents));
		union(0, 1);
		System.out.println(Arrays.toString(parents));
		union(2, 1);
		System.out.println(Arrays.toString(parents));
		union(3, 2);
		System.out.println(Arrays.toString(parents));
		union(4, 3);
		System.out.println(Arrays.toString(parents));
		
		System.out.println("==== find ====");
		System.out.println(find(4));
		System.out.println(Arrays.toString(parents));
		System.out.println(find(3));
		System.out.println(Arrays.toString(parents));
		System.out.println(find(2));
		System.out.println(Arrays.toString(parents));
		System.out.println(find(1));
		System.out.println(Arrays.toString(parents));
		System.out.println(find(0));
		System.out.println(Arrays.toString(parents));

	}

}
