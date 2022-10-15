import java.io.*;
import java.util.*;
 
public class Solution_5515 {
 
    private static int[] date = { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
    private static int first = 4;
 
    public static void main(String[] args) throws Exception {
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(in.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
            sb.append("#" + tc + " ");
 
            StringTokenizer st = new StringTokenizer(in.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
 
            int month = 0;
            for (int i = 1; i < m; i++) {
                month += date[i];
            }
            int answer = (first + month + d - 1) % 7;
 
            sb.append(answer + "\n");
        }
         
        System.out.println(sb);
 
    }
 
}