import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] split = in.readLine().split(" ");
		
		int N = Integer.parseInt(split[0]);
		int X = Integer.parseInt(split[1]);
		int K = Integer.parseInt(split[2]);
		
		for(int i=0; i<K; i++) {
			split = in.readLine().split(" ");
			int A = Integer.parseInt(split[0]);
			int B = Integer.parseInt(split[1]);
			if(A == X) {
				X = B;
			}else if(B == X) {
				X = A;
			}
		}
		
		sb.append(X);
		
		System.out.println(sb);
		
	}

}
