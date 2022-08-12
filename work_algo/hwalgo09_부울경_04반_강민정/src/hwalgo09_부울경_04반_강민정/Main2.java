package hwalgo09_부울경_04반_강민정;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {

	private static int[] data; // 아홉 난쟁이들의 모자 번호
	private static int[] dwarfs; // 아홉 난쟁이들 중 뽑힌 일곱 난쟁이들
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();

		data = new int[9];
		for (int i = 0; i < 9; i++) {
			data[i] = Integer.parseInt(in.readLine());
		}

		// 조합으로 뽑은 일곱 난쟁이들의 모자 번호
		dwarfs = new int[7];

		comb(0, 0);

		System.out.println(sb);

	}

	private static void comb(int cnt, int start) {
		
		// 기저 부분 (종료 조건)
		if(cnt==7) { // 아홉 난쟁이 중 일곱 난쟁이를 뽑았다면
			int sum = 0;
			for(int i=0; i<7; i++) {
				sum += dwarfs[i];
			}
			
			// 합이 100이라면 우리가 찾던 일곱 난쟁이일 것이다.
			if(sum == 100) {
				Arrays.sort(dwarfs);
				for(int i=0; i<7; i++) {
					sb.append(dwarfs[i]).append("\n");
				}
			}
			return;
		}

		// 유도 부분
		for (int i = start; i < 9; i++) {
			dwarfs[cnt] = data[i];
			comb(cnt + 1, i + 1);
		}

	}

}
