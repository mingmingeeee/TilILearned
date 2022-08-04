package week00;

// 밑에서 가는 -> 한가지 빠르게 찾는
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T;
		T = Integer.parseInt(in.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			// N과 M
			String[] split = in.readLine().split(" ");
			int N = Integer.parseInt(split[0]);
			int M = Integer.parseInt(split[1]);

			// 2차원 배열 생성
			int[][] data = new int[N][N];

			// 2차원 배열에 데이터 입력
			for (int i = 0; i < N; i++) {
				split = in.readLine().split(" ");
				// data[i] = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
				for (int j = 0; j < N; j++)
					data[i][j] = Integer.parseInt(split[j]);
			}

			int answer = 0; // 최대 값
			
			// N에 대해서 2차원 배열 완전 탐색하는 이중 for문
			for (int i = 0; i <= N - M; i++) { 
				for (int j = 0; j <= N - M; j++) { // 좌표
					int sum = 0;
					// M에 대해서 2차원 배열 완전 탐색하는 이중 for문 
					for(int x = i; x < i + M; x++) { 
						for(int y = j; y < j + M; y++) {
							sum += data[x][y] ;
						}
					}
					if(answer<sum)
						answer = sum;
				}
			}
			
			sb.append(answer + "\n");

		}

		System.out.println(sb);

	}

}
