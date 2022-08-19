package algo_ws_15_1;

import java.io.*;
import java.util.*;



public class Main {

	// 성: N + 1번 행
	// 적만 이동
	// 1. 거리 D 이하인 적 중 가장 가까운 적 공격
	// 2. 적이 여러 궁수에게 공격 가능
	// 공격 받은 적은 게임에서 제외

	private static List<Position> Enemy = new ArrayList<>(); // 1 ~ N-1까지만 가능 그 이상 가면 사라짐
	private static int[] Archer = new int[3];

	private static int N;
	private static int M;
	private static int D;

	private static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(in.readLine(), " ");

		N = Integer.parseInt(st.nextToken()); // 행의 수
		M = Integer.parseInt(st.nextToken()); // 열의 수
		D = Integer.parseInt(st.nextToken()); // 궁수의 공격 거리 제한

		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				if (Integer.parseInt(split[j]) == 1) {
					Enemy.add(new Position(i, j)); // 적 좌표 저장
				}

			}
		}

		comb(0, 0);

		if(max==Integer.MIN_VALUE)
			System.out.println(0);

		else
			System.out.println(max);

	}

	static void comb(int cnt, int start) {

		int count = 0;
		List<Position> tmpEnemy = new ArrayList<Position>();

		for (int i = 0; i < Enemy.size(); i++) {
			tmpEnemy.add(new Position(Enemy.get(i).x, Enemy.get(i).y));
		}

		if (cnt == 3) { // 궁수 자리 조합
			while (true) {
				int[] tmpx = new int[3];
				int[] tmpy = new int[3];
				Arrays.fill(tmpx, -1);
				Arrays.fill(tmpy, -1);

				for (int i = 0; i < 3; i++) {
					int min = Integer.MAX_VALUE;
					// 한 궁수와의 min값 구하기
					for (int j = 0; j < tmpEnemy.size(); j++) {

						int distance = Math.abs(N - tmpEnemy.get(j).x) + Math.abs(Archer[i] - tmpEnemy.get(j).y);
						if (D >= distance && min > distance) {
							min = distance;
							tmpx[i] = tmpEnemy.get(j).x;
							tmpy[i] = tmpEnemy.get(j).y;
						}

					}
					
					// min 여러개일 때 y가 가장 작은
					int min_cnt = 0;
					int idx_y = Integer.MAX_VALUE;
					for (int j = 0; j < tmpEnemy.size(); j++) {

						int distance = Math.abs(N - tmpEnemy.get(j).x) + Math.abs(Archer[i] - tmpEnemy.get(j).y);
						if (min == distance) {
							if(idx_y > tmpEnemy.get(j).y) {
								tmpx[i] = tmpEnemy.get(j).x;
								tmpy[i] = tmpEnemy.get(j).y;
								idx_y = tmpEnemy.get(j).y;
							}
						}

					}

				}

				// 적 ㅂㅂ
				for (int i = 0; i < 3; i++) {
					for (int j = tmpEnemy.size() - 1; j >= 0; j--) {
						if (tmpx[i] == tmpEnemy.get(j).x && tmpy[i] == tmpEnemy.get(j).y) {
							tmpEnemy.remove(j);
							count++;
							break;
						}
					}
				}
				

				// 적 ㅂㅂ
				for (int j = tmpEnemy.size() - 1; j >= 0; j--) {
					int x = tmpEnemy.get(j).x + 1;
					if (x >= N)
						tmpEnemy.remove(j);
					else {
						tmpEnemy.get(j).x = x;
					}
				}

				
				if(tmpEnemy.size()==0)
					break;
			}

			if(max<count)
				max = count;
			return;
		}

		for (int i = start; i < M; i++) {
			Archer[cnt] = i;
			comb(cnt + 1, i + 1);
		}

	}

}

class Position {

	int x;
	int y;

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Position [x=" + x + ", y=" + y + "]";
	}

}