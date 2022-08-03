package algo_ws_03_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {

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

			for(int i=0; i<s.length(); i++) {
				if(s.charAt(i)=='(' || s.charAt(i)=='[') {
					stack.push(s.charAt(i));
				} else if(s.charAt(i)==')' && !stack.empty()) {
					if('(' == stack.peek()) {
						stack.pop();
					}else {
						stack.push(s.charAt(i));
					}
				} else if(s.charAt(i)==']' && !stack.empty()) {
					if('[' == stack.peek()) {
						stack.pop();
					}else {
						stack.push(s.charAt(i));
					}
				}
			}
			
			if(stack.empty())
				sb.append("yes\n");
			else
				sb.append("no\n");
		
		}
		System.out.println(sb);
	}
	
	
	
}