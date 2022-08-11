package algo_ws_09_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	static int T;
	static int[][] flavour;
	static boolean[] isSelected; 
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(in.readLine());
		
		flavour = new int[T][2];
		String[] split;
		
		isSelected = new boolean[T];
		for(int i=0; i<T; i++) {
			split = in.readLine().split(" ");
			flavour[i][0] = Integer.parseInt(split[0]);
			flavour[i][1] = Integer.parseInt(split[1]);
		}
		
		// 신맛과 쓴맛의 차이가 가장 작은 요리의 차이 
		// 신맛: 재료의 신맛의 곱, 쓴맛: 합
		// 3 8
		// 5 8
		// 15 16 -> 서로 재료의 합 
		subset(0);
		System.out.println(min);
		
	}
	
	public static void subset(int index) {
		
		if(index == T) {
			int S = 1;
			int B = 0;
			int check = 0;
			for(int i=0; i<T; i++) {
				if(isSelected[i]) {
					check = 1;
					S *= flavour[i][0];
					B += flavour[i][1];
				}
			}
			if(check==0) return;
			if(min > Math.abs(S - B)) {
				min = Math.abs(S - B);
			}
			return;
		}
		
		isSelected[index] = true;
		subset(index + 1);
		
		isSelected[index] = false;
		subset(index + 1);
	
	}

}
