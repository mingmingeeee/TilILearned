package algo_ws_22_2;

import java.util.*;
import java.io.*;

public class Main2 {

	// d 값에 의해 아래 델타 값들을 활용
	// 0: X, 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
	private static final int[] dr = { 0, -1, 1, 0, 0 };
	private static final int[] dc = { 0, 0, 0, 1, -1 };

	private static class Shark {
		public int r, c, s, d, z;  // 행, 열, 속력, 이동방향, 크기

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
		System.setIn(new FileInputStream("input1.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		/**
		 * 1. 입력 파일 객체화
		 */
		String[] split = in.readLine().split(" ");
		int R = Integer.parseInt(split[0]);
		int C = Integer.parseInt(split[1]);
		int M = Integer.parseInt(split[2]);
		
		// 땅과 가까운 순으로 정렬 (행 기준 오름차순 정렬)
		PriorityQueue<Shark> queue = new PriorityQueue<>(new Comparator<Shark>() {

			@Override
			public int compare(Shark o1, Shark o2) {
				return o1.r - o2.r;  // 행 기준 오름차순 정렬
			}
			
		});
		
		// 상어들을 PQ에 담기 (땅에 가까운 순으로 정렬)
		for (int i = 0; i < M; i++) {
			split = in.readLine().split(" ");
			int r = Integer.parseInt(split[0]);
			int c = Integer.parseInt(split[1]);
			int s = Integer.parseInt(split[2]);
			int d = Integer.parseInt(split[3]);
			int z = Integer.parseInt(split[4]);
			
			queue.offer(new Shark(r, c, s, d, z));
		}
		
		int kingC = 0;  // 낚시왕의 위치 (열)

		/**
		 * 2. 알고리즘 풀기
		 */
		// 0. 낚시왕이 잡은 상어 크기의 합
		int sum = 0;
		
		// 낚시왕이 격자판을 벗어날 때까지 반복한다.
		while (kingC < C) {
			
			// 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
			kingC++;
			
			// 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
			boolean isCatch = false;
			List<Shark> tempSharks = new ArrayList<>();
			
			while (!queue.isEmpty()) {
				
				// 상어를 꺼낸다.
				Shark curShark = queue.poll();
				
				// 낚시왕이 있는 열에 있는 상어를 최초 발견하면 잡는다. (이미 땅과 가까운 순으로 정렬되어 있으므로)
				if (curShark.c == kingC && isCatch == false) {
					
					// 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
					isCatch = true;
					sum += curShark.z;
				}
				else {  // 그렇지 않은 상어는 임시 리스트에 담는다.
					tempSharks.add(curShark);
				}
			}
			
			// 3. 상어가 이동한다.
			// 상어가 이동을 마친 후에 한 칸에 상어가 두 마리 이상 있을 수 있다.
			// 이때는 크기가 가장 큰 상어가 나머지 상어를 모두 잡아먹는다.
			// 한 칸에 두마리 이상인지 체크하기 위해 이차원 배열을 사용한다.
			Shark[][] isVisited = new Shark[R + 1][C + 1];  // 0번 인덱스 사용 안함
			
			for (Shark curShark : tempSharks) {
				
				// 시간 초과 해결 부분 (이동 횟수를 나머지 연산을 통해 줄이자!)
				int speed = 0;
				// 위 혹은 아래 방향이면
				if (curShark.d == 1 || curShark.d == 2) {
					speed = curShark.s % ((R - 1) * 2);
				}
				else if (curShark.d == 3 || curShark.d == 4) {  // 오른쪽 혹은 왼쪽 방향이면
					speed = curShark.s % ((C - 1) * 2);
				}
				
				// 1초에 이동한 횟수: curShark.s
				for (int i = 0; i < speed; i++) {
					int testR = curShark.r + dr[curShark.d];
					int testC = curShark.c + dc[curShark.d];
					
					// 경계 안쪽이면 이동
					if ((0 < testR && testR <= R) && (0 < testC && testC <= C)) {
						curShark.r = testR;
						curShark.c = testC;
					}
					else {  // 경계 밖이면 방향 전환
						// 위 혹은 아래 방향이면
						if (curShark.d == 1 || curShark.d == 2) {
							curShark.d = curShark.d == 1 ? 2 : 1;  // 위 아래 방향 전환
						}
						else if (curShark.d == 3 || curShark.d == 4) {  // 오른쪽 혹은 왼쪽 방향이면
							curShark.d = curShark.d == 3 ? 4 : 3;  // 오른쪽 왼쪽 방향 전환
						}
						
						// 전환한 방향으로 이동
						curShark.r += dr[curShark.d];
						curShark.c += dc[curShark.d];
					}
				}
				
				// 이동을 다 했다면 방문 표시
				if (isVisited[curShark.r][curShark.c] == null) {  // 상어가 없다면
					isVisited[curShark.r][curShark.c] = curShark;  // 해당 위치에 상어를 넣기
				}
				else if (isVisited[curShark.r][curShark.c] != null) {  // 상어가 있다면
					if (isVisited[curShark.r][curShark.c].z < curShark.z) {  // 새로 들어온 상어가 크다면 변경하기
						isVisited[curShark.r][curShark.c] = curShark;
					}
				}
			}
			
			// isVisited 2차원 배열에 존재하는 상어들은 생존한 상어들
			// 이 상어들을 다시 PQ에 넣고 반복
			for (int i = 1; i <= R; i++) {
				for (int j = 1; j <= C; j++) {
					if (isVisited[i][j] != null) {
						queue.offer(isVisited[i][j]);
					}
				}
			}
		}

		/**
		 * 3. 정답 출력
		 */
		sb.append(sum);
		System.out.println(sb);
	}
}

