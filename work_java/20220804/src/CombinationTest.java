import java.util.Arrays;
import java.util.Scanner;

public class CombinationTest {

	static int N, R, totalCnt;
	static int[] numbers, input; // 뽑은 수 저장할 배열

	// nCr
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		R = sc.nextInt();

		input = new int[N];
		numbers = new int[R];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		comb(0, 0);

		System.out.println("총 경우의 수 : " + totalCnt);
	}

	// cnt+1번째 해당하는 조합에 포함될 수를 뽑기
	private static void comb(int cnt, int start) {
		// cnt: 직전까지 뽑은 순열에 포함된 수의 개수, start: 시도할 수의 시작 위치

		if (cnt == R) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// 가능한 모든 수에 대해 시도 (input 배열의 모든 수 시도)
		// start: 시도할 수의 시작 위치! -> 반복을 돌 때 시작 위치만 결정하는 것 
		// start 부터 처리시 중복 수 추출 방지 및 순서가 다른 조합 추출 방지 
		for (int i = start; i < N; i++) { // 선택지
			// start 위치부터 처리했으므로 중복 체크 필요 없음 !!
			numbers[cnt] = input[i]; // 내가 i를 선택했으니 
			comb(cnt + 1, i + 1); // 넌 내 다음을 선택해
		}

	}
}
