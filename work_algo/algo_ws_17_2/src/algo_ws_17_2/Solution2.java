package algo_ws_17_2;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution2 {

	private static int[] parents;
	private static int N;
	
	private static void make() {  // 크기가 1인 서로소 집합 생성

		parents = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {  // 모든 노드가 자신을 부모로 하는(대표자) 집합으로 만듦
			parents[i] = i;
		}
	}

	private static int find(int a) {  // a의 대표자 찾기

		if (parents[a] == a) {
			return a;
		}
		
		return parents[a] = find(parents[a]);  // 우리의 대표자를 나의 부모로 .. : path compression
	}

	private static boolean union(int a, int b) {  // 리턴 값 : true ==> union 성공
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) {
			return false;
		}

		parents[bRoot] = aRoot;
		return true;
	}
	
	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		System.setIn(new FileInputStream("sample_input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			
			String[] split = in.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			int M = Integer.parseInt(split[1]);

			/**
			 * 2. 알고리즘 풀기
			 */
			make();
			for (int i = 0; i < M; i++) {
				split = in.readLine().split(" ");
				int op = Integer.parseInt(split[0]);
				int a = Integer.parseInt(split[1]);
				int b = Integer.parseInt(split[2]);
				
				switch (op) {
				case 0:  // 합집합
					union(a, b);
					break;
					
				case 1:  // 같은 집합인지 확인
					int result = find(a) == find(b) ? 1 : 0;
					sb.append(result);
					break;
				}
			}

			/**
			 * 3. 정답 출력
			 */
			sb.append("\n");
		}

		System.out.println(sb);
	}
}

