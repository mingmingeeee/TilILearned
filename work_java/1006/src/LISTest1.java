import java.util.Scanner;

public class LISTest1 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] arr = new int[N]; // 수 저장 테이블
		int[] LIS = new int[N]; // 동적 테이블 : 각 원소를 끝으로 하는 LIS값

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int max = 0;
		for (int i = 0; i < N; i++) { // 앞쪽부터 모든 원소 기준으로 자신을 끝으로 하는 LIS 계산
			LIS[i] = 1;
			for (int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && LIS[i] < LIS[j] + 1) {
					LIS[i] = LIS[j] + 1;
				}
			}
			max = Math.max(max, LIS[i]);
		}
		
		System.out.println(max);

	}

}
