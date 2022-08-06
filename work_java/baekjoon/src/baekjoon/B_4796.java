package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_4796 {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int test_case = 1;

		while (true) {
			st = new StringTokenizer(in.readLine());

			int L = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());

			if (L == 0 && P == 0 && V == 0) {
				break;
			}
			
			int m = V;
			int result = 0;
			while(m > P) {
				result += m/P;
				m%=P;
			}
			
			if(m <= L)
				result = result * L + m;
			else
				result = result * L + L;
			
			sb.append("Case " + test_case++ + ": ");
			sb.append(result);
			sb.append("\n");

		}
		
		System.out.println(sb);

	}

}
