package hwalgo24_부울경_04반_강민정;

import java.io.*;
import java.util.*;

public class Main2 {

	private static final int BLANK = 0; // 빈 칸

	private static StringBuilder sb;
	private static int[][] board; // 게임판

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		board = new int[9][9];
		for (int i = 0; i < 9; i++) {
			String readLine = in.readLine();
			for (int j = 0; j < 9; j++) {
				board[i][j] = readLine.charAt(j) - '0';
			}
		}

		dfs(0);
		
		System.out.println(sb);
	}

	// cnt : 호출 횟수
	private static void dfs(int cnt) {
		
		//기저 조건 (81번째 칸이면 종료)
		if(cnt == 81) {
			// 기저 조건을 만났다면, 빈 칸을 모두 채운 경우이므로
			// 빈 칸을 채운 2차원 배열을 출력한다.
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			return;
		}

		// 유도 부분
		int x = cnt / 9; // 행
		int y = cnt % 9; // 열 

		// 숫자가 채워진 경우는 다음 칸 이동
		if (board[x][y] != BLANK) {
			dfs(cnt + 1);
		}

		// 빈 칸이면
		else {
			for (int num = 1; num <= 9; num++) {
				// 숫자 작성 가능 여부 체크
				if (isValid(x, y, num)) {
					board[x][y] = num; // 방문 체크 (숫자 작성)
					dfs(cnt + 1); // 다음 칸으로 이동
					
					// 가지치기 조건
					if(sb.length() != 0) { // 이미 답을 구한 상태이면 (기저 조건을 만나 답을 채운 상태)
						return;
					}
					
					board[x][y] = BLANK; // 미방문 처리 (빈 칸으로 만들기)
				}
			}
		}

	}

	// (x, y) 위치에 num을 넣었을 때 유효한지 검사
	private static boolean isValid(int x, int y, int num) {
		// (x, y) 위치에서 가로, 세로에 num 숫자 사용 유무 확인
		for (int i = 0; i < 9; i++) {
			if (board[x][i] == num || board[i][y] == num) {
				return false;
			}
		}

		// 3 X 3 사각형에 num 숫자 사용 유무 확인
		int curX = x / 3 * 3; // 사각형의 좌측 상단 X 좌표
		int curY = y / 3 * 3; // 사각형의 좌측 상단 Y 좌표
		for (int i = curX; i < curX + 3; i++) {
			for (int j = curY; j < curY + 3; j++) {
				if (board[i][j] == num) {
					return false;
				}
			}
		}

		// 위 조건 모두 통과 했다면, 사용하지 않은 숫자 num이다.
		return true;
	}

}
