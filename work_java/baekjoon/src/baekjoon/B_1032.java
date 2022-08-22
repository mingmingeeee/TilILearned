package baekjoon;

import java.io.*;
import java.util.*;

public class B_1032 {

	// *.exe : 모든 exe 확장자 파일
	// a?b.exe : a로 시작해서 b로 끝나는 exe 확장자 파일
	// -> ?는 아무거나 와도 된다.

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		String[] command = new String[N];

		for (int i = 0; i < N; i++) {
			command[i] = in.readLine();
		}

		for (int i = 0; i < command[0].length(); i++) {
			boolean isTrue = false;
			for (int j = 1; j < N; j++) {
				if (command[j].charAt(i) != command[j - 1].charAt(i)) {
					isTrue = true;
					break;
				}
			}
			if (isTrue) {
				sb.append("?");
			} else {
				sb.append(command[0].charAt(i));
			}
		}

		System.out.println(sb);

	}

}