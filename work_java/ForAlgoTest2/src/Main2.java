import java.io.*;
import java.util.*;

public class Main2 {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] key = in.readLine().toCharArray();
		
		char[] password = in.readLine().toCharArray();
		
		for(int i=0; i<password.length; i++) {
			
			int index = password[i] - 'a';
			
			if(password[i] == ' ')
				sb.append(' ');
			
			else if(index < 0) { // 대문자일 때
				index = password[i] - 'A';
				sb.append((char)(key[index] - 32));
			} else { // 소문자일 때 
				sb.append(key[index]);
			}
			
		}
		
		System.out.println(sb);
	}

}
