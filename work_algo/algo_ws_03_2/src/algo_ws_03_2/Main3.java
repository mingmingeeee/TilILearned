package algo_ws_03_2;
// 백준 4949

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static Stack<Character> stack = new Stack<Character>();
	
	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			stack.clear();
			String s = in.readLine();
			
			if(s.equals(".")) {
				break;
			}
			
			sb.append(S(s));
		
		}
		System.out.println(sb);
	}
	static String S(String s) {
		
		for(int i=0; i<s.length(); i++) {
			
			char c = s.charAt(i);
			if(c=='(' || s.charAt(i)=='[') {
				
				stack.push(c);
				
			} else if(c==')') {
				
				if(!stack.empty() && '(' == stack.peek()) {
					stack.pop();
				}else {
					return "no\n";
				}
				
			} else if(c==']') {
				if(!stack.empty() &&'[' == stack.peek()) {
					stack.pop();
				}else {
					return "no\n";
				}
			}
			
		}
		
		if(stack.empty())
			return "yes\n";
		else
			return "no\n";
		
		
	}
	// 반례 .] -> ]에서 넣지를 않으니까 계속 yes가 나옴
	
	
}
