package com.ssafy.ws.problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//      StringTokenizer st = new StringTokenizer(in.readLine());
 
        StringBuilder sb = new StringBuilder();
 
        int T;
        T = Integer.parseInt(in.readLine());
 
        for (int tc = 1; tc <= T; tc++) {
 
            sb.append("#" + tc + " ");
 
            String bit = in.readLine();
            char[] tmp = new char[bit.length()];
            Arrays.fill(tmp, '0');
             
            int count = 0;
            for (int i = 0; i < bit.length(); i++) {
                if(bit.charAt(i)!=tmp[i]) {
                    for(int j=i; j<bit.length(); j++) {
                        tmp[j] = bit.charAt(i);
                    }
                    count++;
                }
            }
            sb.append(count + "\n");
        }
        System.out.print(sb);
 
    }
 
}
