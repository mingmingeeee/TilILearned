package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_12891 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] strings = in.readLine().split(" ");
		int S = Integer.parseInt(strings[0]);
		int P = Integer.parseInt(strings[1]);

		char[] password = new char[S];

		String s = in.readLine();
		for (int i = 0; i < S; i++) {
			password[i] = s.charAt(i);
		}

		int[] ACGT = new int[4];
		int count = 0; // 조건을 만족하는 지?
		strings = in.readLine().split(" ");
		for (int i = 0; i < 4; i++) {
			ACGT[i] = Integer.parseInt(strings[i]);
			if(ACGT[i]==0)
				count++;
		}

		// 첫 번째 turn 구하기
		for (int i = 0; i < P; i++) {
			if (password[i] == 'A') {
				ACGT[0]--;
				if (ACGT[0] == 0)
					count++;
			} else if (password[i] == 'C') {
				ACGT[1]--;
				if (ACGT[1] == 0)
					count++;
			} else if (password[i] == 'G') {
				ACGT[2]--;
				if (ACGT[2] == 0)
					count++;
			} else if (password[i] == 'T') {
				ACGT[3]--;
				if (ACGT[3] == 0)
					count++;
			}
		}
		int answer = 0;
		if (count == 4) {
			answer++;
		}
		// 0 초과면 실패5
		// 0 이하면 굿~
		int index_old = 0; // 이걸 증가시키고 나중에 그 만큼 빼줘야 함
		int index_new = P;
		while (index_new < S) {

			if (password[index_old] == 'A') {
				if (ACGT[0] == 0)
					count--;
				ACGT[0]++;
			} else if (password[index_old] == 'C') {
				if (ACGT[1] == 0)
					count--;
				ACGT[1]++;
			} else if (password[index_old] == 'G') {
				if (ACGT[2] == 0)
					count--;
				ACGT[2]++;
			} else if (password[index_old] == 'T') {
				if (ACGT[3] == 0)
					count--;
				ACGT[3]++;
			}

			if (password[index_new] == 'A') {
				ACGT[0]--;
				if (ACGT[0] == 0)
					count++;
			} else if (password[index_new] == 'C') {
				ACGT[1]--;
				if (ACGT[1] == 0)
					count++;
			} else if (password[index_new] == 'G') {
				ACGT[2]--;
				if (ACGT[2] == 0)
					count++;
			} else if (password[index_new] == 'T') {
				ACGT[3]--;
				if (ACGT[3] == 0)
					count++;
			}

			if (count == 4) {
				answer++;
			}
			index_old++;
			index_new++;

		}
		sb.append(answer);
		System.out.println(sb);

	}

}
