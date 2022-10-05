package hwalgo24_부울경_04반_강민정;

// 백준 2239. 스도쿠
import java.io.*;
import java.util.*;

// 1. 0을 만난다.
// 2. 0 자리에 1~9 숫자를 대입해본다.
// 3. 스도쿠 조건 (3가지 조건) 만족하는지 확인 => (1) 행, (2) 열, (3) 3x3 사각형 
// ==> 사전식으로 앞서는 것을 출력 => 81자리의 수가 제일 작은 경우 출력 

public class Main {

	private static boolean isAvailable;
	private static boolean[] visited;
	private static int[][] sdoku = new int[9][9];
	private static List<Position> list = new ArrayList<>();
	
	private static StringBuilder sb = new StringBuilder();

	private static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		char[] tmp = new char[9];
		for (int i = 0; i < 9; i++) {
			tmp = in.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				sdoku[i][j] = tmp[j] - '0';
				if (sdoku[i][j] == 0) {
					list.add(new Position(i, j));
				}
			}
		}

		visited = new boolean[list.size()];

		start(0);
		
		
		
		System.out.println(sb);

	}

	private static void start(int cnt) {
		
		if(isAvailable)
			return;

		if (cnt == list.size()) { // 다 채웠으면
			isAvailable = true; // true
			for(int i=0; i<9; i++) {
				for(int j=0; j<9; j++) {
					sb.append(sdoku[i][j]);
				}
				sb.append("\n");
			}
			return; // return
		}

		if (visited[cnt])
			return; // 이미 채운 부분이면 return

		visited[cnt] = true; // 방문 표시

		int x = list.get(cnt).x;
		int y = list.get(cnt).y;

		for (int i = 1; i <= 9; i++) {
			if (isPossible(x, y, i)) { // 수 넣을 수 있으면
				sdoku[x][y] = i;

				start(cnt + 1); // 수로 끝까찌 감

				sdoku[x][y] = 0; // 반환
			}
		}

		visited[cnt] = false;

	}

	private static boolean isPossible(int x, int y, int number) {
		// 행, 열
		for (int i = 0; i < 9; i++) {
			if (sdoku[x][i] == number || sdoku[i][y] == number) { // 원래 이미 있던 수이면
				return false;
			}
		}

		// 3x3 스도쿠
		int nx = x / 3;
		int ny = y / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sdoku[3 * nx + i][3 * ny + j] == number)
					return false;
			}
		}
		
		return true;
	}

}
