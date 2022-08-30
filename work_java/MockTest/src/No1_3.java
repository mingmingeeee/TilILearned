
import java.io.*;
import java.util.*;

public class No1_3 {

	// 게이트? 낚시꾼?
	// 낚시꾼은 게이트 안에 있으므로... 게이트에 순서가 중요!
	// 3개의 게이트로 순열을 만드는 완전 탐새그올 진행해보자!

	// DFS(게이트 개수, 현재까지의 거리 합){
	// if 현재까지의 거리 >= 최소 거리 return
	// if 3개의 게이트를 다 배치했다면 최소 거리 갱신 후 return
	//
	// 1. 순열 뽑기
	// 2. 낚시꾼 배치
	// 3. DFS(게이트 + 1, 배치 후 늘어난 거리)
	// }

	// 어떻게 배치?
	// 1. 왼쪽 -> 오른쪽
	// 2. 오른쪽 -> 왼쪽

	static class Gate {
		int pos;
		int fisher;

		public Gate(int pos, int fisher) {
			this.pos = pos;
			this.fisher = fisher;
		}
	}

	static int N; // 낚시터 수 N
	static Gate[] gates; // 게이트 정보 저장
	static int min, fisherCnt; // min: 최소 이동 거리
	static boolean[] selected; // 순열 selected 배열
	static int[] map; // 낚시터 ->
	// 낚시꾼 배치했다가 DFS 탐색 후 원래 상태로 되돌리기 위해 -> 낚시 gate 번호로 저장했다가~되돌리는
	// 각 낚시꾼이 몇 번 게이트인지 알게 하기 위해 map[i]에는 게이트 번호를 저장했다가 재귀 끝나고 원상태로 되돌릴 때 사용할 것임

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#" + tc + " ");

			N = Integer.parseInt(in.readLine());

			gates = new Gate[4];

			for (int i = 1; i <= 3; i++) {
				String[] split = in.readLine().split(" ");

				gates[i] = new Gate(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

			}
			selected = new boolean[4];
			map = new int[N + 1];
			min = Integer.MAX_VALUE;

			DFS(0, 0);

			sb.append(min).append("\n");

		}

		System.out.println(sb);
	}

	// 왼쪽 배치
	private static int left(int idx, int distance) {

		int left = gates[idx].pos - distance; // 게이트에서 distance 뺌

		if (left > 0 && map[left] == 0) { // left가 0보다 크고 비어있다면 (1번부터 시작이니까)

			map[left] = idx; // 게이트 번호로 저장
			fisherCnt++;
			return distance + 1;

		}

		return 0;
	}

	// 오른쪽 배치
	private static int right(int idx, int distance) {

		int right = gates[idx].pos + distance; // 게이트에서 distance 더함

		if (right <= N && map[right] == 0) { // left가 0보다 크고 비어있다면 (1번부터 시작이니까)

			map[right] = idx; // 게이트 번호로 저장
			fisherCnt++;
			return distance + 1;

		}

		return 0;

	}

	// 낚시꾼 배치 함수
	// flag: true - 왼쪽 -> 오른쪽, false - 오른쪽 -> 왼쪽 순서
	private static int inFishers(int idx, int fishers, boolean flag) {
		int distance = 0;
		fisherCnt = 0;
		int sum = 0;

		while (fisherCnt < fishers) {
			// true: 왼쪽, false: 오른쪽
			sum += flag ? left(idx, distance) : right(idx, distance);

			if (fisherCnt == fishers)
				break; // 모든 낚시꾼을 다 배치할 때까지 왼쪽, 오른쪽 배치함

			// true: 오른쪽, false: 왼쪽
			sum += flag ? right(idx, distance) : left(idx, distance);
			
			// true: 왼쪽 -> 오른쪽
			// false: 오른쪽 -> 왼쪽
			distance++;

		}

		return sum;
	}

	// 배치했던 낚시꾼을 원래 상태로 되돌림
	private static void outFishers(int idx) {
		int cnt = 0;

		for (int i = 1; i <= N; i++) { // 모든 낚시터를 돌면서
			if (map[i] == idx) { // 되돌리고 싶은 gate 번호가 있는 map을
				map[i] = 0; // 0으로 바꿔줌
				cnt++;
			}

			if (cnt == gates[idx].fisher)
				return;
		}
	}

	// 순열 함수
	private static void DFS(int cnt, int sum) {

		if (sum >= min)
			return;
		if (cnt == 3) {
			min = Math.min(min, sum);
			return;
		}

		// 게이트 선택 순서가 중요하므로 -> 선택했을 때 바로 탐색함 
		for (int i = 1; i <= 3; i++) {

			if (selected[i])
				continue;

			// 1. 게이트 선택
			selected[i] = true;

			// 2. 낚시꾼 배치
			// cnt + 1 해서 계속해서 그에 맞는 게이트 번호들 선택 
			DFS(cnt + 1, sum + inFishers(i, gates[i].fisher, true)); // true이기 때문에 왼쪽 -> 오른쪽 배치 
			outFishers(i);

			if (gates[i].fisher % 2 == 0) { // 짝수일 경우에만 오른쪽으로 탐색 추가로 돌려줌
				DFS(cnt + 1, sum + inFishers(i, gates[i].fisher, false)); // false이기 때문에 오른쪽 -> 왼쪽 배치 
				outFishers(i);
			}

			// 3. 선택 해제
			selected[i] = false;

		}

	}

}
