package algo_ws_13_1;

import java.io.*;
import java.util.*;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
 
        int[] dx = { 0, -1, 0, 1, 0 };
        int[] dy = { 0, 0, 1, 0, -1 };
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(in.readLine());
 
        for (int test_case = 1; test_case <= T; test_case++) {
 
            sb.append("#" + test_case + " ");
 
            int N = 10;
 
            List<Cur> A = new ArrayList<>();
            List<Cur> B = new ArrayList<>();
            A.add(new Cur(1, 1));
            B.add(new Cur(10, 10));
 
            String[] split = in.readLine().split(" ");
            int M = Integer.parseInt(split[0]); // 이동 시간
            int BC = Integer.parseInt(split[1]); // BC의 개수
 
            split = in.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                int d = Integer.parseInt(split[i]);
                int testX = A.get(A.size() - 1).x + dx[d];
                int testY = A.get(A.size() - 1).y + dy[d];
                A.add(new Cur(testX, testY));
            }
            split = in.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                int d = Integer.parseInt(split[i]);
                int testX = B.get(B.size() - 1).x + dx[d];
                int testY = B.get(B.size() - 1).y + dy[d];
                B.add(new Cur(testX, testY));
            }
            List<Battery> batteries = new ArrayList<>();
            for (int i = 0; i < BC; i++) {
                split = in.readLine().split(" ");
                batteries.add(new Battery(Integer.parseInt(split[1]), Integer.parseInt(split[0]),
                        Integer.parseInt(split[2]), Integer.parseInt(split[3])));
            }
 
            /**
             * 알고리즘
             */
            // 1. 겹칠 때
            // 2. 딴 것도 가능할 때
            int sum = 0;
 
            for (int i = 0; i < M + 1; i++) {
 
                int[] a_p = new int[BC];
                int[] b_p = new int[BC];
 
                for (int j = 0; j < BC; j++) {
                    int A_distance = Math.abs(A.get(i).x - batteries.get(j).x)
                            + Math.abs(A.get(i).y - batteries.get(j).y);
                    int B_distance = Math.abs(B.get(i).x - batteries.get(j).x)
                            + Math.abs(B.get(i).y - batteries.get(j).y);
                    int Cov = batteries.get(j).Coverage;
                    int Per = batteries.get(j).Performance;
 
                    if (A_distance <= Cov) {
                        a_p[j] = Per;
                    }
                    if (B_distance <= Cov) {
                        b_p[j] = Per;
                    }
 
                }
 
                int max = Integer.MIN_VALUE;
                for (int j = 0; j < BC; j++) {
                    for (int k = 0; k < BC; k++) {
                        int s;
                        if (j == k && a_p[j] == b_p[k]) {
                            s = a_p[j];
                        }
                        else
                            s = a_p[j] + b_p[k]; 
                        if(max < s) {
                            max = s;
                        }
                    }
                }
                 
                sum += max;
 
            }
 
            sb.append(sum).append("\n");
 
        }
 
        System.out.println(sb);
 
    }
 
}
 
class Cur {
 
    int x;
    int y;
 
    Cur(int x, int y) {
        this.x = x;
        this.y = y;
    }
 
}
 
class Battery {
    int x;
    int y;
    int Coverage;
    int Performance;
 
    Battery(int x, int y, int Coverage, int Performance) {
        this.x = x;
        this.y = y;
        this.Coverage = Coverage;
        this.Performance = Performance;
    }
}