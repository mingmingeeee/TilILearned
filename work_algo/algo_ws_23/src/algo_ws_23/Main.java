package algo_ws_23;

import java.io.*;
import java.util.*;

public class Main {

	// 가로 0, 대각선 1, 세로 2
	private static final int HORIZONTAL = 0;
	private static final int DIAGONAL = 1;
	private static final int VERTICAL = 2;
	
	private static final int BLOCK = 1;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		int[][] space = new int[N + 1][N + 1]; // 인덱스 0번은 사용 안 함
		
		for(int i = 1; i <= N; i++) {
			String[] split = in.readLine().split(" ");
			for(int j = 1; j <= N; j++) {
				space[i][j] = Integer.parseInt(split[j - 1]);
			}
		}

		// 1. 동적 테이블 생성 -> 현 위치에 파이프가 놓이는 경우의 수
		int[][][] D = new int[N + 1][N + 1][3]; // 행, 열, [가로 0, 대각선 1, 세로 2]
		
		// 2. 베이스 값 채우기
		D[1][2][HORIZONTAL] = 1;  // (1, 2)칸에 가로 파이프의 끝이 있다는 의미 
		
		// 3. 점화식을 이용하여 상향식으로 동적 테이블 채우기 
		for(int i = 1; i <= N; i++) {
			for(int j = 3; j <= N; j++) { // 다음 파이프 끝이 위치하는 좌표는 (1, 3) 이므로 j는 3부터 시작 
				
				// 벽이면 다음 반복문 수행
				if(space[i][j] == BLOCK) {
					continue;
				}
				
				// 현재 위치에 가로 파이프를 놓을 수 있는 경우의 수는
				// 이전 파이프를 가로로 놓은 경우와 대각선으로 놓은 경우의 수 합이다.
				D[i][j][HORIZONTAL] = D[i][j - 1][HORIZONTAL] + D[i][j - 1][DIAGONAL];
				
				// 맨 윗줄의 경우는 가로 파이프만 놓을 수 있으므로
				// 현재 위치에 세로 파이프를 놓은 경우의 수와 대각선 파이프를 놓은 경우의 수는 없다.
				if(i == 1) {
					continue;
				}
				
				// 현재 위치에 세로 파이프를 놓을 수 있는 경우의 수는
				// 이전 파이프를 세로로 놓은 경우와 대각선으로 놓은 경우의 수의 합이다.
				D[i][j][VERTICAL] = D[i - 1][j][VERTICAL] + D[i - 1][j][DIAGONAL];
				
				// 대각선 파이프를 놓으려면 윗 칸과 좌측 칸, 현재 칸이 벽이면 안 된다.
				if(space[i - 1][j] == BLOCK || space[i][j - 1] == BLOCK) {
					continue;
				}
				
				// 현재 위치에 대각선 파이프를 놓을 수 있는 경우의 수는
				// 이전 파이프를 가로, 대각선, 세로로 놓은 경우의 수의 합이다.
				D[i][j][DIAGONAL] = D[i - 1][j - 1][HORIZONTAL] + D[i - 1][j - 1][DIAGONAL] + D[i - 1][j - 1][VERTICAL];
				
			}
		}
		
		sb.append(D[N][N][HORIZONTAL] + D[N][N][DIAGONAL] + D[N][N][VERTICAL]);
		System.out.println(sb);

	}

}
