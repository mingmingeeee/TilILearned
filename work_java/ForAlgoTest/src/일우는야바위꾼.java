// https://www.acmicpc.net/problem/20361
// 백준 20361번

import java.io.*;
import java.util.*;

public class 일우는야바위꾼 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		int N = Integer.parseInt(split[0]); // 컵 개수
		int X = Integer.parseInt(split[1]); // 공을 숨겨놓을 컵
		int K = Integer.parseInt(split[2]); // 컵 위치 바꾸기 K번 수행


		for (int i = 0; i < K; i++) {
			split = in.readLine().split(" ");
			int a = Integer.parseInt(split[0]);
			int b = Integer.parseInt(split[1]);

			if(a == X) { // 간식 있는 컵이 a였으면 b로 바꿈
				X = b;
			} else if (b == X) { // 간식 있는 컵이 b였으면 a로 바꿈
				X = a;
			}
		}
		sb.append(X);
		System.out.println(sb);

	}

}
