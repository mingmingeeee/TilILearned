package daily;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

// 0: 바다, 1: 섬
// 다리: 섬 2개 연결 가능
// 다리 중간에 섬: 다리 건설 X
// 가로 또는 세로에 있는 섬만 연결 가능 -> 상-하, 좌-우
// 섬끼리 붙어있어도 다리 건설 가능 -> 1

public class Lv {

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("input.txt")); // 문제에서 주어진 Input 데이터 파일명 작성

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine()); // 테스트 케이스

		int[][]map = new int[N][N];
		
		for(int i=0; i<N; i++) {
        	String[] split = in.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = split[j].charAt(0) - '0';
			}
        }
		
		// 검사 
		int bridge_max = 0;
		int bridge = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				int b1 = 0; int b2 = 0;
				int x=i+1, y=j+1;
				while(x>=0 && x<N && y>=0 && y<N && map[x][y]==0) {
					y++;
					b1++;
				}
				x=i+1; y=j+1;
				while(x>=0 && x<N && y>=0 && y<N && map[x][y]==0) {
					x++;
					b2++;
				}
				bridge = (b2>b1)?b2:b1;
				
			}
			if(bridge_max<bridge)
				bridge_max=bridge;
		}
		
		System.out.println(bridge_max);
		
	}
}