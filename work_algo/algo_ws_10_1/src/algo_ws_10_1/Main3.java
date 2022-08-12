package algo_ws_10_1;

import java.io.*;
import java.util.*;

public class Main3 {
	
	private static int M;			// 치킨집 중 M개의 치킨집을 뽑기
	private static List<Pair> house; // 모든 집의 좌표를 저장
	private static List<Pair> chicken; // 모든 치킨집의 좌표를 저장
	private static Pair[] pick; // 조합을 이용해서 M개의 치킨집을 저장
	private static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] split = in.readLine().split(" ");
		int N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);
		
		
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		pick = new Pair[M];
		answer = Integer.MAX_VALUE;
		
		for(int i=1; i<N+1; i++) {
			split = in.readLine().split(" ");
			for(int j=1; j<N+1; j++) {
				switch(split[j-1]) {
				case "1": // 집일 경우
					house.add(new Pair(i,  j));
					break;
					
				case "2": // 치킨집일 경우
					chicken.add(new Pair(i, j));
					break;
				}
			}
			
		}
		
		comb(0, 0);
		
		sb.append(answer);
		System.out.println(sb);
	}
	
	private static void comb(int cnt, int start) {
		
		if(cnt == M) {
			
			// 치킨 거리의 합
			int sum = 0;
			
			for(int i=0; i<house.size(); i++) {
				
				int min = Integer.MAX_VALUE;
				
				for(int j=0; j<M; j++) {
					Pair pickedHouse = house.get(i);
					Pair pickedChicken = pick[j];
					
					int dist = Math.abs(pickedHouse.r - pickedChicken.r) 
							+ Math.abs(pickedHouse.c + pickedChicken.c);
					
					if(dist < min) {
						min = dist;
					}
				}
				
				
				// 가장 작은 치킨 거리 값들의 합 구함
				sum += min;
			}
			
			// 답
			if(sum < answer) {
				answer = sum;
			}
			return;
			
		}
		
		for(int i=start; i<chicken.size(); i++) {
			pick[cnt] = chicken.get(i);
			
			comb(cnt + 1, i + 1);
		}
	}
	
}

class Pair{
	public int r;
	public int c;
	
	public Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + r + "," + c + ")";
	}
}
