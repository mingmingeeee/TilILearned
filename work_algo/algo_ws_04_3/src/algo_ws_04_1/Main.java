package algo_ws_04_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		Stack<St> stack = new Stack<>();

		/**
		 * 1. 입력 파일 객체화
		 */

		int N = Integer.parseInt(in.readLine());

		st = new StringTokenizer(in.readLine());

		/**
		 * 2. 알고리즘 풀기
		 */

		for (int i = 1; i <= N; i++) {
			St top = new St(i,  Integer.parseInt(st.nextToken())); // 들어올 값

			// 6 9 5 7 4
			if (!stack.isEmpty() && stack.peek().height < top.height) {
				
				stack.pop();
				if(stack.isEmpty())
					sb.append(0 +" ");
				else {
					sb.append(stack.peek().i + " ");
				}
				
				stack.push(top);
			} else {
				
				if(!stack.isEmpty()) {
					sb.append(stack.peek().i + " ");}
				else {
					sb.append(0 +" ");
				}
				
				stack.push(top);
			}
		}
		
		
		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);
	}

}

class St{
	
	int i;
	int height;
	
	St(int i, int height){
		this.i = i;
		this.height = height;
	}
}
