
// http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1153
// 정올 1880

import java.io.*;
import java.util.*;

// 문자열 소문자 -> str.toLowerCase();
// 문자열 대문자 -> str.toUpperCase();

// 문자 소문자 판별 -> Character.isUpperCase() -> false: 소문자, true: 대문자

public class 정올_암호풀기 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		char[] key = in.readLine().toCharArray();
		char[] password = in.readLine().toCharArray();

		// (1)
		for (int j = 0; j < password.length; j++) {
			if (password[j] == ' ') {
				sb.append(password[j]);
				continue;
			}
			int index = password[j] - 'a';
			if (index < 0) {
				index = password[j] - 'A';
				StringBuilder sb2 = new StringBuilder();
				sb2.append(key[index]);
				sb.append(sb2.toString().toUpperCase());
			} else {
				password[j] = key[index];
				sb.append(password[j]);
			}
		}

		// (2)
//		for (int j = 0; j < password.length; j++) {
//			if (password[j] == ' ') {
//				sb.append(' ');
//			}else if (65 <= password[j] && password[j] <= 90 ) { // 대문자
//				password[j]+=32;
//				sb.append((char)(key[password[j]-'a']-32));
//			} else { // 소문자
//				sb.append(key[password[j]-'a']);
//			}
//		}



		System.out.println(sb);

	}

}
