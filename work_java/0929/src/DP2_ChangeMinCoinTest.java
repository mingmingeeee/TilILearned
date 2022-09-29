import java.util.Arrays;
import java.util.Scanner;

public class DP2_ChangeMinCoinTest {
	
	// 쓸 수 있는 동전이 무한개 이면 ㅇㅇ
	// 근데 정해져있다면 ㄴㄴ 아닐 수도 
	
	// 4, 6원 화폐단위로 고정 , 동전 개수 무제한
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int money = sc.nextInt(); // 목표 금액 <= 최대 100000원이다
		
		// 최종적으로 구해야하는 값은? 동적테이블의 의미에 따라 다름 
		// 꼭 마지막 값이 답이 아님 
		int[] D = new int[money + 1]; // D[i] : i 금액을 만드는 최소 동전 수
		// 만들 수 없는 경우는 "INF"
		
		D[0] = 0; // 0원에 대한 최적해는 0
		// 그런데 만약 D[]가 경우의 수라면? -> 초기값 D[0] = 1 !! "경우의 수"이니까.
		
		int INF = 100000; // 가장 작은 화폐 단위를 가장 많이 써도 만들 수 없는 큰 값 , + 1 처리 시 오버플로우 발생하지 않는 값
		for (int i = 1; i <= money; i++) {
			int min = INF; // 최소 값을 큰 값으로 놓고 출발!

			// 1원, 4원, 6원 
			if(i >= 4) min = Math.min(min, D[i - 4] + 1); // INF + 1해도 오버플로우 안 남~~~
			if(i >= 6) min = Math.min(min, D[i - 6] + 1); 
			
			D[i] = min;
		}
		
		System.out.println(Arrays.toString(D));
		
		System.out.println(D[money]==INF?-1:D[money]);
		
	}

}
