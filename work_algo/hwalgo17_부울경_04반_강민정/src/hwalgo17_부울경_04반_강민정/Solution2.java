package hwalgo17_부울경_04반_강민정;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeSet;

public class Solution2 {

	static void make(int V) {

		parents = new int[V + 1];

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

		if (aRoot == bRoot)
			return false;

		parents[bRoot] = aRoot;

		return true;
	}

	static int[] parents;

	public static void main(String[] args) throws Exception {
		

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			
			TreeSet<Integer> set = new TreeSet<>();
			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");

			int N = Integer.parseInt(split[0]); // 사람의 수 (정점의 수)
			int M = Integer.parseInt(split[1]); // 서로를 알고 있는 사람의 관계 수 (간선 수)

			make(N);

			int answer = 1;

			for (int i = 0; i < M; i++) {
				split = in.readLine().split(" ");

				int a = Integer.parseInt(split[0]) - 1;
				int b = Integer.parseInt(split[1]) - 1;

				union(a, b);

			}

			// find를 호출하여 무리의 대표자 갱신
			for (int i = 1; i <= N; i++) {
				find(i);
			}

			// 무리의 대표자 오름차순 정렬
			Arrays.sort(parents);

			// 무리의 개수 구하기
			for (int i = 1; i < N; i++) {
//				// 다른 무리면 카운팅 (대표가 다르면)
//				if (parents[i] != parents[i + 1]) {
//					answer++;
//				}

				set.add(parents[i]);
			}

//			sb.append(answer).append("\n");
			sb.append(set.size()).append("\n");

		}

		System.out.print(sb);

	}

}
