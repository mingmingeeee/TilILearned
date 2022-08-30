package baekjoon;

import java.io.*;
import java.util.*;

public class B_16637 {

	private static int answer = Integer.MIN_VALUE; //..답이 음수가 나올 경우 고려 

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());
		char[] exp = in.readLine().toCharArray();

		List<Integer> num = new ArrayList<>();
		List<Character> op = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0)
				num.add(exp[i] - '0');
			else
				op.add(exp[i]);
		}

		dfs(0, num.get(0), num, op);

		sb.append(answer);

		System.out.println(sb);

	}

	private static int cal(int a, int b, char op) {
		switch (op) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		default:
			return Integer.MAX_VALUE;
		}

	}

	private static void dfs(int i, int sum, List<Integer> num, List<Character> op) {

		if (i >= op.size()) { // 연산자 고갈 = 연산 끝
			answer = Math.max(answer, sum);
			return;
		}

		// 1. 괄호 x -> 그냥 계산 
		int sum1 = cal(sum, num.get(i + 1), op.get(i));
		dfs(i + 1, sum1, num, op);

		// 2. 괄호 o
		if(i + 1 < op.size()) {
			// 바로 옆으로 넘겨서 또 괄호치는 일 없게 (중첩이 없게) 미리 계산하고 index + 2 해서 넘김
			int cal1 = cal(num.get(i + 1), num.get(i + 2), op.get(i + 1));
			int sum2 = cal(sum, cal1, op.get(i));
			dfs(i + 2, sum2, num, op); 
		}
	}

}
