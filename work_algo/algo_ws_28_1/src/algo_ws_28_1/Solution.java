package algo_ws_28_1;

import java.io.*;
import java.util.*;

public class Solution {

	// N x N 절벽지대 활주로 건설
	// 셀의 숫자: 지형의 높이
	// 활주로: 높이가 동일한 구간에서 건설 가능
	// 경사로: 길이가 X, 높이 1
	// 높이 차이가 1이고 낮은 지형의 높이가 동일하게 경사로의 길이만큼 연속되는 곳에 설치 가능

	// 활주로를 건설할 수 있는 경우의 수를 계산

	static int N, X, map[][], map2[][];

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(in.readLine().trim());
		StringTokenizer st = null;

		for (int tc = 1; tc <= TC; ++tc) {
			st = new StringTokenizer(in.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			map2 = new int[N][N];

			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(in.readLine().trim());
				for (int j = 0; j < N; ++j) {
					map2[j][i] = map[i][j] = Integer.parseInt(st.nextToken());
					// map2: 행, 열 뒤집은 정보
				}
			}
			System.out.println("#" + tc + " " + process());
		}

	}

	private static int process() {

		int count = 0;
		for (int i = 0; i < N; i++) {
			if (makeRoad(map[i])) // 수평 활주로 건설 체크
				count++;
			if (makeRoad(map2[i])) // 수직 활주로 건설 체크 
				count++;
		}
		return count;

	}

	// 해당 지형 정보로 활주로 건설이 가능하면 true, 불가능하면 false 리턴
	private static boolean makeRoad(int[] road) {

		int beforeHeight = road[0], size = 0;
		int j = 0;

		while (j < N) {

			if (beforeHeight == road[j]) { // 기존의 높이와 탐색하려는 높이와 같으면
				size++;
				j++;
			} else if (beforeHeight + 1 == road[j]) { // 이전 높이보다 1 높음 : 오르막 경사로 설치 체크
				if (size < X)
					return false; // X길이 미만이면 활주로 건설 불가

				beforeHeight++; // 오르막 경사로로 높이 증가
				size = 1; // size 초기화
				j++; // 옆 칸으로
			} else if (beforeHeight - 1 == road[j]) { // 이전 높이보다 1 작음
				int count = 0;
				for (int k = j; k < N; k++) {
					if (road[k] != beforeHeight - 1) {
						return false;
					}
					if(++count == X) break; // 최소길이 만족했으므로 반복문 빠져나오기 
				}
				
				if(count < X) { // 최소 연속 길이 만족 X
					return false; 
				}
				
				beforeHeight--; // 내리막 경사로로 높이 줄어듬
				j += X; // 다음 탐색 위치로 가야 하니까 X만큼 건너뜀
				size = 0;
			} else { // 높이가 2 이상 차이
				return false;
			}

		}

		return true;

	}

}
