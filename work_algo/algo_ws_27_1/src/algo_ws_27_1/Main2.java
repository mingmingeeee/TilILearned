package algo_ws_27_1;

import java.io.*;
import java.util.*;

public class Main2 {

	private static int N;  // 구역의 개수
	private static int[] population;  // 각 구역의 인구 수
	private static int[][] adjMatrix;  // 각 구역의 연결 여부 확인을 위한 인접행렬
	private static int[] parents;  // 각 구역의 어느 선거구에 포함되었는지 확인하기 위한 배열 (같은 부모면 같은 선거구)
	private static int min;  // 두 선거구의 인구 차이의 최솟값


	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		////////////// 입력
		N = Integer.parseInt(in.readLine());
		population = new int[N + 1]; // 각 구역의 인구 수 (구역 번호는 1번부터 시작)
		String[] split = in.readLine().split(" ");
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(split[i - 1]);
		}
		adjMatrix = new int[N + 1][N + 1];
		for (int from = 1; from <= N; from++) {
			split = in.readLine().split(" ");

			int cnt = Integer.parseInt(split[0]);
			for (int j = 1; j <= cnt; j++) {
				int to = Integer.parseInt(split[j]);
				// 구역 A가 구역 B와 인접하면 구역 B도 구역 A와 인접한다.
				adjMatrix[from][to] = adjMatrix[to][from] = 1; // 1은 서로 연결되어 있다는 뜻

			}
		}

		////////////// 알고리즘
		// 두 선거구의 인구 차이의 최솟값 (두 선거구로 나눌 수 없는 경우는 -1)
		min = Integer.MAX_VALUE;

		// 1. 구역들을 두 선거구로 나눌 수 있는 방법을 구한다. (부분 집합)
		generateSubSet();
		
		min = min == Integer.MAX_VALUE ? -1 : min;
		
		sb.append(min);

		System.out.println(sb);
	}

	private static void generateSubSet() {
		// 공집합과 모든 원소를 포함한 집합을 제외한 모둔 부분 집합 구하기
		// caseCont가 (2^N / 2)부터 집합 구성이 대칭을 이루기 때문에 반을 나눠줬다.
		for (int flag = 1, caseCount = 1 << N; flag < (caseCount / 2); flag++) {

			// parents 배열 초기화
			make();

			// 구역들을 선거구 2개로 나눈다.
			List<Integer> groupA = new ArrayList<>();
			List<Integer> groupB = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				if ((flag & 1 << i) != 0) {
					groupA.add(parents[i + 1]);
				} else {
					groupB.add(parents[i + 1]);
				}
			}

//			System.out.println(groupA);
//			System.out.println(groupB);
//			System.out.println("=========" + flag + "/" + caseCount);

			// 2. 서로소 집합을 이용하여 나눈 구역들을 union 한다.
			// 선거구 A에 포함된 구역들을 union 한다.
			for (int i = 0; i < groupA.size() - 1; i++) {
				for (int j = i + 1; j < groupA.size(); j++) {
					int from = groupA.get(i);
					int to = groupA.get(j);
					if (adjMatrix[from][to] == 1) { // 두 구역이 서로 연결되어 있다면
						union(from, to); // 두 구역을 합치기
					}
				}
			}

			// 선거구 B에 포함된 구역들을 union 한다.
			for (int i = 0; i < groupB.size() - 1; i++) {
				for (int j = i + 1; j < groupB.size(); j++) {
					int from = groupB.get(i);
					int to = groupB.get(j);
					if (adjMatrix[from][to] == 1) { // 두 구역이 서로 연결되어 있다면
						union(from, to); // 두 구역을 합치기
					}
				}
			}

			// 모든 구역에 대해 find 수행하여 패스 압축 한다. (최상단 부모 확인을 위해)
			for (int i = 0; i <= N; i++) {
				find(i);
			}
			
			// 3. 모든 구역에 대해 부모가 총 2개 존재한다면 두 선거구로 나눌 수 있는 경우이다.
			Arrays.sort(parents); // 부모의 개수 파악을 쉽게 하기 위해 정렬
//			System.out.println(Arrays.toString(parents));
			
			int cntOfParents = 0;
			for(int i=0; i<parents.length - 1; i++) {
				if(parents[i] != parents[i + 1]) {
					cntOfParents++;
				}
			}
			
			// 두 선거구로 나눌 수 있는 경우에는 두 선거구의 인구의 차이를 구한다. 
			if(cntOfParents == 2) {
				int cntA = 0;
				int cntB = 0;
				
				for(int i=0; i<groupA.size(); i++) {
					int zone = groupA.get(i);
					cntA += population[zone];
				}
				
				for(int i=0; i<groupB.size(); i++) {
					int zone = groupB.get(i);
					cntB += population[zone];
				}
				
				min = Math.min(min, Math.abs(cntA - cntB));
				
			}

		}
	}

	private static void make() {
		parents = new int[N + 1]; // 선거구 (1번부터 사용)
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) { // a의 대표자 찾기
		if (parents[a] == a) {
			return a;
		}

		return parents[a] = find(parents[a]); // 우리의 대표자를 나의 부모로 => 패스 압축
	}

	// 리턴 값 : true ==> union 성공
	// 리턴 값 : false => union 실패 (사이클 발생!)
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return false;
		}

		parents[bRoot] = aRoot;
		return true;
	}

}
