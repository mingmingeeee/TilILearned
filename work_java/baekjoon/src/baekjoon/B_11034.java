package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_11034 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		String string;
		
		while ((string = in.readLine()) !=null) {
			st = new StringTokenizer(string);

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			int A_B = B - A - 1;
			int B_C = C - B - 1;
			
			if(A_B > B_C)
				sb.append(A_B);
			else
				sb.append(B_C);
			
			sb.append("\n");

		}
		
		System.out.println(sb);

	}

	
}
