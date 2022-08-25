
import java.io.*;
import java.util.*;

public class No1 {
	
	private static int N;
	private static int[] exit;
	private static int[] people;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			
			N = Integer.parseInt(in.readLine()); // 낚시터 자리 개수
			
			people = new int[N + 1]; // 출입구 번호에 따른 사람 수 저장 배열 
			exit = new int[3]; // 출입구 번호 저장 배열 
			
			for(int i=0; i<3; i++) {
				String[] split = in.readLine().split(" ");
				
				int exit_num = Integer.parseInt(split[0]);
				int people_num = Integer.parseInt(split[1]);
				people[exit_num] = people_num;
				exit[i] = exit_num;
			}
			
			Arrays.sort(exit);
			
			do {
//				System.out.println(Arrays.toString(exit));
				
				// 사람 낚시터 배치 -> 최단 거리 
				visited = new boolean[N + 1];
				
				
				
				dfs(exit[0], 1);
				
			}while(np());
			
		}
	}
	
	static boolean[] visited;
	private static void dfs(int x, int depth) {
		
		if(x >= N + 1 || x < 0)
			return;
		
	
		
		visited[x] = true;
		
		dfs(x + 1, depth + 1);
		dfs(x - 1, depth + 1);
		
	}
	
	private static boolean np() {
		int num = 3;
		
		int i = num - 1;
		while(i > 0 && exit[i-1] >= exit[i]) {
			--i;
		}
		
		if(i==0)
			return false;
		
		int j = num - 1;
		while(exit[i-1] >= exit[j]) {
			--j;
		}
		swap(i-1, j);
		
		int k = num - 1;
		while(i<k) {
			swap(i++, k--);
		}
		
		return true;
	}
	
	private static void swap(int i, int j) {
		int tmp = exit[i];
		exit[i] = exit[j];
		exit[j] = tmp;
	}

}
