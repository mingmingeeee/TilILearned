import java.util.Arrays;
import java.util.Scanner;

public class DP2_ChangeMinCoinTest2 {
	
	
	// 1, 4, 6원 화폐단위로 고정 , 동전 개수 무제한
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int money = sc.nextInt(); // 목표 금액
		
		// 최종적으로 구해야하는 값은? 동적테이블의 의미에 따라 다름 
		// 꼭 마지막 값이 답이 아님 
		int[] D = new int[money + 1]; // D[i] : i 금액을 만드는 최소 동전 수
		
		D[0] = 0; // 0원에 대한 최적해는 0
		// 그런데 만약 D[]가 경우의 수라면? -> 초기값 D[0] = 1 !! "경우의 수"이니까.
		
		// 1, 4, 6 사용 가능
		int INF = Integer.MAX_VALUE;
		for (int i = 1; i <= money; i++) {
			int min = INF; // 최소 값을 큰 값으로 놓고 출발!

			// IF, 1원에 대한 화폐단위가 없다면 ???
			// 1원 최적 해 
			min = Math.min(min, D[i - 1] + 1); // 1원을 사용했을 때의 최적 해 
			
			// 4원 최적 해 -> 4원보다 크거나 같을 때 가능
			if(i >= 4) min = Math.min(min, D[i - 4] + 1); // min: 직전단계까지 처리된 min
			// min, 현재 금액 i에서 4원 뺀 금액에다가 4원을 하나 더 썼을 때의 최적  
			
			// 6원 최적 해 -> 6원보다 크거나 같을 때 가능
			if(i >= 6) min = Math.min(min, D[i - 6] + 1); 
			
			D[i] = min;
		}
		
		System.out.println(Arrays.toString(D));
		
		System.out.println(D[money]);
		
	}

}
