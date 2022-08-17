package algo_ws_13_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution2 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T;
		T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			String[] split = in.readLine().split(" ");
			int M = Integer.parseInt(split[0]); // 총 이동 시간
			int A = Integer.parseInt(split[1]); // BC의 개수

			// 사용자 A와 B의 이동 정보
			String[] splitA = in.readLine().split(" ");
			String[] splitB = in.readLine().split(" ");

			// 초기 이동 방향까지 저장을 위해 인덱스 번호에 1 더함
			// 아래 배열은 각 사용자의 이동 방향을 저장하는 배열
			int[] routeA = new int[M + 1];
			int[] routeB = new int[M + 1];

			// 초기 이동 방향 (제자리)
			routeA[0] = 0;
			routeB[0] = 0;

			// 입력 파일에 작성된 이동 방향을 가져온다.
			for (int i = 1; i < M + 1; i++) {
				routeA[i] = Integer.parseInt(splitA[i - 1]);
				routeB[i] = Integer.parseInt(splitB[i - 1]);
			}

			// BC의 정보
			BC[] bcs = new BC[A];
			for (int i = 0; i < A; i++) {
				split = in.readLine().split(" ");
				bcs[i] = new BC();
				bcs[i].x = Integer.parseInt(split[0]);
				bcs[i].y = Integer.parseInt(split[1]);
				bcs[i].c = Integer.parseInt(split[2]);
				bcs[i].p = Integer.parseInt(split[3]);
			}

			// 사용자 A, B 생성하고 출발 위치 설정
			User userA = new User(1, 1);
			User userB = new User(10, 10);

			// X, 상, 우, 하 , 좌
			int[] dx = { 0, 0, 1, 0, -1 };
			int[] dy = { 0, -1, 0, 1, 0 };

			int answer = 0; // 최대 충전 값
			for (int turn = 0; turn < M + 1; turn++) {

				// 입력에서 받은 이동 방향에 따라 사용자를 이동시킨다.
				userA.x += dx[routeA[turn]];
				userA.y += dy[routeA[turn]];
				userB.x += dx[routeB[turn]];
				userB.y += dy[routeB[turn]];

				// 각 사용자가 방문한 BC를 기록
				// 아래 인덱스 번호는 하나의 BC를 가리키게 된다.
				int[] visitedA = new int[A];
				int[] visitedB = new int[A];
				for (int i = 0; i < A; i++) {
					// 사용자와 BC간의 거리 계산
					int distA = Math.abs(userA.x - bcs[i].x) + Math.abs(userA.y - bcs[i].y);
					int distB = Math.abs(userB.x - bcs[i].x) + Math.abs(userB.y - bcs[i].y);

					// 충전 범위 내에 들어오면, 해당 BC 범위에 들어왔다고 visited 배열에 표시
					if (distA <= bcs[i].c)
						visitedA[i] = 1;

					if (distB <= bcs[i].c)
						visitedB[i] = 1;
				}
				
				// 모든 경우의 수에 해당하는 합 구하기
				// 그 중 최댓값을 max 변수에 저장
				int max = 0;
				for (int i = 0; i < A; i++) {
					for (int j = 0; j < A; j++) {
						int sum = 0;
						
						// 사용자 A, B가 같은 BC에 들어온 경우
						if(i == j && visitedA[i] == 1 && visitedB[j]==1 ) {
							// 사용자 A, B가 반반 나눠 가지지만 총합은 결국 그 BC의 성능 값
							sum += bcs[i].p;
						}
						else { // 그렇지 않으면 각각의 BC의 성능 값 만큼 누적 
							if(visitedA[i] == 1) {
								sum += bcs[i].p;
							} 
							
							if(visitedB[j] ==1) {
								sum+= bcs[j].p;
							}
						}
						
						// 구한 합이 최댓값인지 확인
						if(max < sum) {
							max = sum;
						}
					}
				}
				
				// max는 한 칸 이동했을 때 최대로 얻을 수 있는 성능 값
				// answer에 누적해서 답을 구한다.
				answer += max;
			}
			
			sb.append(answer).append("\n");
		}

		System.out.print(sb);
	}

}

class BC {
	public int x; // 위치
	public int y; // 위치
	public int c; // 충전 범위
	public int p; // 성능

	// 디버그용
	@Override
	public String toString() {
		return "BC [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
	}
}

class User {
	public int x; // 위치
	public int y; // 위치

	public User(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "User [x=" + x + ", y=" + y + "]";
	}
}
