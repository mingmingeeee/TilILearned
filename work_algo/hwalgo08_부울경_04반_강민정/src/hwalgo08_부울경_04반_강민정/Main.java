package hwalgo08_부울경_04반_강민정;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int[][] data;
	static int[][] tmp_data;
	static int min = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]);
		int M = Integer.parseInt(split[1]);
		int R = Integer.parseInt(split[2]);

		data = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			split = in.readLine().split(" ");
			for (int j = 1; j <= M; j++) {
				data[i][j] = Integer.parseInt(split[j - 1]);
			}
		}
		int[][] command = new int[R][3];
		// endX - startX : 세로
		// endY - startY : 가로

		for (int k = 0; k < R; k++) {
			split = in.readLine().split(" ");
			command[k][0] = Integer.parseInt(split[0]);
			command[k][1] = Integer.parseInt(split[1]);
			command[k][2] = Integer.parseInt(split[2]);
		}
	
		
		int[] nums = new int[R];
		boolean[] isSelected = new boolean[R];
		comb(0, R, command, nums, isSelected);
		sb.append(min);
		System.out.println(sb);
	}
	
	static void comb(int cnt, int V, int[][] command, int[] nums, boolean[] isSelected) {
		if(cnt==V) {
			tmp_data = new int[data.length][data[0].length];
			for(int i=1; i<tmp_data.length; i++) {
				for(int j=1; j<tmp_data[0].length; j++) {
					tmp_data[i][j] = data[i][j]; 
				}
			}
			for(int i=0; i<V; i++) {
				move(command[nums[i]][0], command[nums[i]][1], command[nums[i]][2]);
			}
			for(int i=1; i<data.length; i++) {
				int sum = 0;
				for(int j=1; j<data[0].length; j++) {
					sum += tmp_data[i][j];
				}
				if(min >= sum)
					min = sum;
			}
			return;
		}
		
		for(int i=0; i<command.length; i++) {
			if(isSelected[i]) continue;
			nums[cnt] = i;
			isSelected[i] = true;
			comb(cnt + 1, V, command, nums, isSelected);
			isSelected[i] = false; 		
		}
	}
	
	static void move(int r, int c, int s) {

		for (int j = 0; j < s; j++) {
			int startX = r - s + j;
			int startY = c - s + j;

			int endX = r + s - j;
			int endY = c + s - j;
			int tmp = tmp_data[startX][endY];
			// 윗변: 왼쪽에서 오른쪽 이동
			for (int i = endY; i > startY; i--) {
				tmp_data[startX][i] = tmp_data[startX][i - 1];
			}

			// 우변: 아래에서 위로 이동
			for (int i = startX; i < endX; i++) {
				tmp_data[i][startY] = tmp_data[i + 1][startY];
			}

			// 아랫변: 오른쪽에서 왼쪽 이동
			for (int i = startY; i < endY; i++) {
				tmp_data[endX][i] = tmp_data[endX][i + 1];
			}

			// 좌변: 위에서 아래 이동
			for (int i = endX; i > startX; i--) {
				tmp_data[i][endY] = tmp_data[i - 1][endY];
			}

			// 처음 빼낸 값 다시 넣기
			tmp_data[startX + 1][endY] = tmp;
		}
	}
}