package algo_ws_02_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = 10;
        int N = 100;
 
        for (int test_case = 1; test_case <= 10; test_case++) {
            sb.append("#" + test_case + " ");
 
            String string = in.readLine();
 
            // 배열 입력
            int[][] data = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    data[i][j] = Integer.parseInt(st.nextToken());
                }
            }
 
            // 알고리즘
            int[] dx = { 0, 0, -1 };
            int[] dy = { -1, 1, 0 };
 
            int x = N-1;
            int y = 0;
            for (int i = 0; i < N; i++) {
                if (data[x][i] == 2) {
                    y = i;
                    break;
                }
            }
             
            while (true) {
                 
                for (int i = 0; i < 3; i++) {
                    int testX = x + dx[i];
                    int testY = y + dy[i];
                    if (testX >= 0 && testX < N && testY >= 0 && testY < N 
                            && data[testX][testY] == 1) {
                        x += dx[i];
                        y += dy[i];
                        data[testX][testY] = 3;
                    }else {
                        continue;
                    }
 
                }
                 
                if(x==0) {
                    sb.append(y + "\n");
                    break;
                }
 
            }
 
        }
        // 출력
        System.out.println(sb);
 
    }
 
}