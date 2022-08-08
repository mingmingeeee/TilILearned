package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_2493 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(in.readLine());
		st = new StringTokenizer(in.readLine());

		Stack<Tower> stack = new Stack<>();

		for (int i = 0; i < N; i++) {
			Tower top = new Tower(i + 1, Integer.parseInt(st.nextToken())); // 들어올 값
			while(!stack.isEmpty() && stack.peek().height < top.height) {

				stack.pop();

			} 
			if (stack.isEmpty())
				sb.append(0 + " ");
			else {
				sb.append(stack.peek().index + " ");
			}
			stack.push(top);


		}

		System.out.println(sb);

	}

}

class Tower {

	int index;
	int height;

	public Tower(int index, int height) {
		this.index = index;
		this.height = height;
	}

}
