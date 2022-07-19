package practice;

import java.io.*;

// 로봇이 갈 수 있는 거리 구하긔 -> 더해서 
// 1. S적힌 곳만 지나갈 수 이슴
// 2. 로봇 초기 위치 넘어갈 수 업슴 + 벽 X
// 4방 탐색
// A: 오른쪽 -> 0
// B: 양방향 -> 0, 1
// C: 상하좌우 -> 0, 1, 2, 3
// 0 1 1 0 -1 0 0 -1
// 0 1

public class pr1 {
	
	public static void main(String[] args) throws Exception {
		
        System.setIn(new FileInputStream("ArrayTest_30_input.txt"));  // 문제에서 주어진 Input 데이터 파일명 작성
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(in.readLine());  // 테스트 케이스
        
        for (int test_case = 1; test_case <= T; test_case++) {
            System.out.print("#" + test_case + " ");
         
            // 여기서 부터 알고리즘 작성
            int N = Integer.parseInt(in.readLine());
            char[][] array = new char[N][N];
            for(int i=0; i<N; i++) {
            	String[] split = in.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					array[i][j] = split[j].charAt(0);
				}
            }
            
            // 1. S 적힌 곳 지나갈 수 없음 + W 지나갈 수 없음 + A, B, C지나갈 수 없음
            // 로봇: A, B, C
            int count=0;
            for(int i=0; i<N; i++) {
            	for(int j=0; j<N; j++) {
   	
            		int []dx = {};
            		int []dy = {};
            		
            		if(array[i][j]=='A') { // A
            			dx = new int[] {0};
            			dy = new int[] {1};
            		}
            		else if(array[i][j]=='B') { // B
            			dx = new int[] {0, 0};
            			dy = new int[] {1, -1};
					}
            		else if(array[i][j]=='C') { // C
            			dx = new int[] {-1, 1, 0, 0};
            			dy = new int[] {0, 0, -1, 1};	
            		}
            	
        			for(int k=0; k<dx.length; k++) {
        				int x = i + dx[k];
        				int y = j + dy[k];
        				
	            		while(x >= 0 && x < N && y >=0 && y < N ) {
	            			
	            			if(array[x][y]=='S') {
	            				count++;
	            			}
	            			else {
	            				break;
	            			}
	            			x += dx[k];
            				y += dy[k];
            				
	            		}
	            		
        			}
        			
            	}
      
           }
            System.out.println(count);
        }
        
    }

}
