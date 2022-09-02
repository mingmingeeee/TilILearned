package baekjoon;

import java.io.*;
import java.util.*;

/*
 * 1. N -> 게임 진행 횟수
 * 2. 한 이닝에 3아웃 발생 -> 이닝 종료, 공격 & 수비 바꿈
 * 3. 타자가 타석에 앉는 순서 정해야 함 
 * 4. 9번 타자까지 공을 쳤는데 3아웃 발생하지 않은 상태면 이닝 안 끝나고 1번 타자가 다시 타석에 섬
 * -> 2이닝: 6번이 마지막 -> 3이닝: 7번부터
 * 5. 공격: 투수가 던진 공을 타석에 있는 타자가 치는 것...
 * 공격 팀의 선수가 1루, 2루, 3루를 거쳐서 홈에 도착하면 1점 득점
 * 타자가 홈에 도착하지 못하고 1루, 2루, 3루 중 머물러 있을 수 있음.
 * 루에 있는 선수를 주자라고 함
 * 이닝이 시작될 때는 주자 없음
 * 타자가 공을 쳐서 얻을 수 있는 결과: 안타, 2루타, 3루타, 홈런, 아웃
 * 안타: 타자와 모든 주자가 한 루씩 진루 -> 1
 * 2루타: 타자와 모든 주자가 두 루씩 진루 -> 2
 * 3루타: 타자와 모든 주자가 세 루씩 진루 -> 3
 * 홈런: 타자와 모든 주자가 홈까지 진루 -> 4
 * 아웃: 모든 주자는 진루하지 못하고, 공격 팀에 아웃이 하나 증가 -> 0
 * 
 * -> 1번 선수 4번 타자로 미리 결정,
 * 다른 선수의 타순 결정해야 함
 * -> 입력: 각 선수가 각 이닝에서 어떤 결과를 얻는지
 * 가장 많이 득점하는 타순 찾고 그 때의 득점 구하기
 * */

public class B_17281 {
	
	private static int N;
	private static int[][] benefit;
	private static int max;
	private static int[] players;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(in.readLine());
		benefit = new int[N][9];
		max = Integer.MIN_VALUE;
		players = new int[9];
		
		for(int i=0; i<N; i++) {
			String[] split = in.readLine().split(" ");
			for(int j=0; j<9; j++) {
				benefit[i][j] = Integer.parseInt(split[j]);
			}
		}
		
		perm(0, 0, 0, 0);
		
		System.out.println(max);
		
	}
	
	
	
	// 타순 정하기
	static private void perm(int cnt, int flag, int sum, int index) {
		
		if(index == N) {
			max = Math.max(max, sum);
			return;
		}
		
		if(cnt == 9) {
			perm(0, 0, sum, index + 1);
		}
		
		for(int i=0; i<9; i++) {
			if((flag & 1 << i) != 0) continue;
			perm(cnt + 1, flag | 1 << i, sum + benefit[index][i], index);
		}
		
	}

}
