package algo_ws_18_1;

import java.io.*;
import java.util.*;

import javax.xml.soap.Node;

public class Main3 {

	private static final char BLANK = '.'; // 빈 칸
	private static final char WATER = '*'; // 물
	private static final char S = 'S'; // 고슴도치 시작점
	private static final char D = 'D'; // 도착점

	// 우, 하, 좌, 상
	private static final int[] dr = { 0, 1, 0, -1 };
	private static final int[] dc = { 1, 0, -1, 0 };

	private static char[][] map; // 지도
	private static Queue<Node> sQueue; // 고슴도치 BFS를 위한 큐
	private static Queue<Node> wQueue; // 물 BFS를 위한 큐

	private static int R;
	private static int C;

	private static int answer;

	private static class Node {

		public int r;
		public int c;
		public int depth;

		public Node(int r, int c, int depth) {
			this.r = r;
			this.c = c;
			this.depth = depth;
		}

		@Override
		public String toString() {
			return "Main3 [r=" + r + ", c=" + c + ", depth=" + depth + "]";
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 변수 초기화
		sQueue = new ArrayDeque<>();
		wQueue = new ArrayDeque<>();

		String[] split = in.readLine().split(" ");
		R = Integer.parseInt(split[0]); // 행
		C = Integer.parseInt(split[1]); // 열
		answer = 0; // 고슴도치-도착점 최단거리

		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			String line = in.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);

				switch (map[i][j]) {

				case S: // 고슴도치 시작 위치
					sQueue.offer(new Node(i, j, 0));
					break;

				case WATER:
					wQueue.offer(new Node(i, j, 0));
					break;

				}
			}
		}

		// 1. 물 먼저 뿌린 후
		// 2. 고슴도치들 이동시켜 봄

		do {
			bfsWater(); // 1. 물 뿌림
			bfs(); // 2. 고슴도치 이동
		} while (!sQueue.isEmpty() && answer == 0);
		
		sb.append(answer == 0 ? "KAKTUS" : answer);
		
		System.out.println(sb);
 
	}

	private static void bfsWater() {

		// 큐 크기 확인 (동일 너비 대상 개수)
		int size = wQueue.size();

		while (--size >= 0) {
			// 현재 물 꺼내가ㅣ
			Node curNode = wQueue.poll();

			// 4방향 탐색
			for (int i = 0; i < 4; i++) {
				int testR = curNode.r + dr[i];
				int testC = curNode.c + dc[i];

				// 경계 체크
				if ((0 <= testR && testR < R) && (0 <= testC && testC < C)) {

					// 빈 칸이거나 고슴도치면
					// 고슴도치일 때도 물 채우는 이유: 원래 빈칸이었기 때문 -> 물이 계속 퍼져가는 거랑 고슴도치 이동 경로랑은 별개임 
					// 고슴도치가 서있다가 물이 오면 그냥 침수되는 거 
					if (map[testR][testC] == BLANK || map[testR][testC] == S) {
						map[testR][testC] = WATER; // 물 채우고
						wQueue.offer(new Node(testR, testC, curNode.depth + 1)); // 다음 칸 이동
					}

				}
			}
		}

	}
	
	private static void bfs() {
		// 큐 크기 확인 (동일 너비 대상 개수)
				int size = sQueue.size();

				while (--size >= 0) {
					// 현재 물 꺼내기
					Node curNode = sQueue.poll();

					// 4방향 탐색
					for (int i = 0; i < 4; i++) {
						int testR = curNode.r + dr[i];
						int testC = curNode.c + dc[i];

						// 경계 체크
						if ((0 <= testR && testR < R) && (0 <= testC && testC < C)) {
							
							// 도착하면 (기저 부분)
							if(map[testR][testC] == D) {
								answer = curNode.depth + 1;
								return; 
							}

							// 빈 칸이면
							if (map[testR][testC] == BLANK) {
								map[testR][testC] = S; // 고슴도치 이동 경로 표시 
								sQueue.offer(new Node(testR, testC, curNode.depth + 1)); // 다음 칸 이동
							}

						}
					}
				}
	}

}
