package algo_ws_04_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution2 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = 10;
		
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int num = Integer.parseInt(in.readLine());
			
			String strings = in.readLine();
			
			Stack<Character> stack = new Stack<>();
			int answer = 0;
			for(int i=0; i<num; i++) {
				// 입력 받은 과로들 중에서 index 번호에 해당하는 문자를 하나 꺼낸다. 
				char c = strings.charAt(i);
				
				// 만약 열린 괄호면 스택에 담기
				if(c=='(' || c=='{' || c=='[' || c=='<') {
					stack.push(c);
				}
				// 만약 닫힌 괄호면 스택에서 꺼내서 괄호 종류 비교 
				else if(c==')' || c=='}' || c==']' || c=='>') {
					if(!stack.isEmpty()) { // 스택이 비어 있지 않은 경우 
						char pop = stack.pop();
						if(pop == '(' && c ==')') {
							answer = 1;
						} else if(pop == '{' && c =='}') {
							answer = 1;
						}else if(pop == '[' && c ==']') {
							answer = 1;
						}else if(pop == '<' && c =='>') {
							answer = 1;
						}else {
							answer = 0;
							break;
						}
					}
					else { // 닫힌 괄호를 만났는데 짝이 없다면 ㅠㅠㅠ
						answer = 0;
						break;
					}
				}
			}
			
			// 스택에 괄호가 있다면, 유효하지 않은 상태이므로 답은 0
			if(!stack.isEmpty()) {
				answer = 0;
			}
			
			sb.append(answer);
			sb.append("\n");
			
		}
		System.out.println(sb);
	}
	static void Stack() {
		
	} 
	
}
