package hwalgo09_부울경_04반_강민정;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[] dwarfs;
	static int[] tmp;
	static int flag = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		dwarfs = new int[9];
		tmp = new int[7];
		for(int i=0; i<9; i++) {
			dwarfs[i] = Integer.parseInt(in.readLine()); 
		}
		
		Com(0, 0);
	
		System.out.println(sb);
	}
	
	// 10C7 조합 
	static void Com(int cnt, int start) {
		
		if(cnt == 7) {
			int sum = 0;
			for(int i=0; i<7; i++) {
				sum += tmp[i];
			}
			if(sum==100 && flag == 0) {
				Arrays.sort(tmp);
				for(int i=0; i<7; i++) {
					sb.append(tmp[i]).append("\n");
				}
				flag = 1;
			}
			return;
		}
		
		for(int i=start; i<9; i++) {
			tmp[cnt] = dwarfs[i];
			Com(cnt + 1, i + 1);
		}
		
	}

}
