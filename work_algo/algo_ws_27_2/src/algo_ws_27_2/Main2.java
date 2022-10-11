package algo_ws_27_2;

import java.io.*;
import java.util.*;

public class Main2 {

	private static final int WATER = 0;

	private static int N;
	private static int M;

	// DFS 변수들
	private static int[][] map;
	private static boolean[][] isVisited;
	private static int current; // 섬에 대한 넘버링
	private static final int[] dx = { 0, 1, 0, -1 }; // 우, 하, 좌, 상
	private static final int[] dy = { 1, 0, -1, 0 };

	// MST 변수들과 구조체
	private static class Edge implements Comparable<Edge> {
		public int from;
		public int to;
		public int weight;

		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.weight - o.weight;
		}
	}

	private static int[] parents;

	private static void make() {
		parents = new int[current + 1];
		for (int i = 0; i <= current; i++) {
			parents[i] = i;
		}
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;

		return parents[a] = find(parents[a]); // 패스 압축
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return false;
		}

		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		current = 1; // 섬에 대한 넘버링은 2부터 시작 (이미 map에 1로 섬이 표시되어 있으므로)
		isVisited = new boolean[N][M];
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(split[j]);
			}
		}

		// 1. 섬 식별 : DFS 시작점 결정
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if (!isVisited[x][y] && map[x][y] == 1) {
					current++;
					DFS(x, y); // DFS 시작
				}
			}
		}
		
		// 2. 간선 정보 저장: Kruskal 알고리즘 사용을 위해
		List<Edge> list = new ArrayList<>();
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < M; y++) {
				if(map[x][y] != WATER) { // 섬이라면
					for(int i = 0; i< 4; i++) {
						int testX = x + dx[i];
						int testY = y + dy[i];
						
						if((0 <= testX && testX < N) && (0 <= testY && testY < M)) {
							// 섬 내부일 경우 다른 방향으로 탐색
							if(map[testX][testY] != WATER)
								continue;
							else { // 바다일 경우
								Edge temp = new Edge();
								temp.from = map[x][y]; // 다리 건설 시작 섬 번호
								
								// 다리 시작 좌표
								int tX = testX;
								int tY = testY;
								
								// 한 방향으로 다리 건설
								for(int cnt = 1; (0 <= tX && tX < N) && (0 <= tY && tY < M); cnt++) {
									tX = tX + dx[i];
									tY = tY + dy[i];
									
									if((0 <= tX && tX < N) && (0 <= tY && tY < M)) {
										if(map[tX][tY] == WATER) { // 바다일 경우
											continue; // 다리 계속 걸설
										} else { // 섬일 경우
											if (cnt > 1) { // 다리 길이가 2 이상인지 확인
												temp.to = map[tX][tY]; // 다리 건설 끝 섬 번호
												temp.weight = cnt; // 다리 길이 (가중치)
												list.add(temp);
											}
											break;
										}
									} 
 								}
							}
						}
					}
				}
			}
		}
		
		// 간선들을 다리 길이 순(가중치 순)으로 정렬 (길이가 짧은 다리를 우선 사용하기 위해)
		Collections.sort(list);
		
		// 3. MST - Kruskal 알고리즘 적용
		make();
		
		int k = 0, result = 0;
		for(Edge e : list) {
			if(union(e.from, e.to)) { // 간선으로 두 정점의 집합을 합치는 시도 : 성공 => 사이클 발생하지 않음 
				result += e.weight;
				// 간선의 개수는 (섬의 마지막 번호 - 2 = 섬의 개수 - 1)
				// 모든 섬이 이어졌다면 종료
				// 기저 조건
				if(++k == current - 2) {
					break;
				}
			}
		}
		
		if(k == current - 2) { // 성공
			sb.append(result);
		} else { // 모든 섬 연결 실패 시
			sb.append(-1);
		}

	}
	
	private static void DFS(int curX, int curY) {
		isVisited[curX][curY] = true;
		
		if(map[curX][curY] == 1) {
			map[curX][curY] = current; // 각 섬에 대해 번호 붙이기
		}
		
		// 4방향 탐색하면서 섬 영여겨 DFS 탐색
		for(int i=0; i<4; i++) {
			int testX = curX + dx[i];
			int testY = curY + dy[i];
			
			// 경계 안쪽이면
			if ((0 <= testX && testX < N) && (0 <= testY && testY < M)) {
				// 방문하지 않았고 여전히 섬 영역이면 다음 위치로 이동하여 DFS 수행
				if(!isVisited[testX][testY] && map[testX][testY] == 1) {
					DFS(testX, testY);
				}
			}
		}
	}

}