package algo_ws_09_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Character.Subset;

public class Solution {

	private static int N; // 재료의 수
	private static int L; // 제한 칼로리
	private static int[][] TK;// 재료의 점수, 재료의 칼로리
	private static int max; // 조건에 맞는 최대 점수
	private static boolean[] isSelected; // 부분 집합 구성 시 사용할 flag
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		int T;
		T = Integer.parseInt(in.readLine());
		
		for(int test_case = 1; test_case <=T; test_case++) {
			sb.append("#" + test_case + " ");
			
			String[] split = in.readLine().split(" ");
			N = Integer.parseInt(split[0]);
			L = Integer.parseInt(split[1]);
			
			TK = new int[N][2];
			for(int i=0; i<N; i++) {
				split = in.readLine().split(" ");
				TK[i][0] = Integer.parseInt(split[0]);
				TK[i][1] = Integer.parseInt(split[1]);
			}
			
			
			// 전역 변수 초기화
			max = Integer.MIN_VALUE;
			isSelected = new boolean[N];
			
			// 부분 집합으로 풀기
			Subset(0);
			
			sb.append(max).append("\n");
			System.out.println(sb);
	
		}
		
	}
	
	public static void Subset(int index) {
		
		// 기저 조건 (종료 조건)
		if(index == N) { // 더 이상 고려할 원소가 없다면 부분 집합의 구성이 완성
			
			int sumT = 0; // 정수의 합
			int sumK = 0; // 칼로리의 합
			
			for(int i=0; i<N; i++) {
				if(isSelected[i]) {
					sumT += TK[i][0];
					sumK += TK[i][1];
				}
			}
			
			// 만약 제한 칼로리 이ㅏ이면
			if(sumK <= L) {
				
				// 맛에 대한 정수 최대값 갱신
				if(sumT > max) {
					max = sumT;
				}
			}
			
			return;
		}
		
		
		
		// 유도 부분
		// 원소 선택
		isSelected[index] = true;
		Subset(index + 1);
		
		// 원소 미선택
		isSelected[index]= false;
		Subset(index + 1);
	}
}
