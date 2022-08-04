package algo_ws_04_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {


	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] open = { '{', '(', '[', '<' };
		char[] close = { '}', ')', ']', '>' };
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			Stack<Character> stack = new Stack<>();
			sb.append("#" + test_case + " ");
			stack.clear();
			int num = Integer.parseInt(in.readLine());

			String strings = in.readLine();

			int answer = 0;
			external:
			for (int i = 0; i < strings.length(); i++) {
				for (int j = 0; j < open.length; j++) { // 열린 괄호라면
					if (strings.charAt(i) == open[j]) {
						stack.push(strings.charAt(i));
					}
				}
				for (int j = 0; j < close.length; j++) {
					if (strings.charAt(i) == close[j]) { // 닫힌 괄호라면
						if (!stack.isEmpty() && stack.peek() == open[j]) {
							stack.pop(); // 짝인 괄호라면 빼내기
							answer = 1;
						}else {
							break external;
						}
					}
				}

			}

			if (!stack.isEmpty())
				answer = 0;

			sb.append(answer + "\n");

		}
		System.out.println(sb);
	}

	static void Stack() {

	}

}