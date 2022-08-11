import java.util.Arrays;
import java.util.Scanner;

public class NextPermutationTest { // nPr 안 됨... nPn만 됨...

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();

		int[] input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		// 전처리: 순열에 쓰일 수들을 오름차순 정렬
		Arrays.sort(input);
		
		do {
			System.out.println(Arrays.toString(input));
		}while(np(input));

	}

	public static boolean np(int[] numbers) { // numbers 배열의 상태를 근거로 다음 순열 생성, 다음 순열 존재하면 true, 아니면 false

		int N = numbers.length;
		// 1. 꼭대기 찾기 -> 맨 뒤부터 올라가는 녀석인지 보는 거
		// 줄어드는 녀석이 있다면 교환 대상
		int i = N - 1;
		while (i > 0 && numbers[i - 1] >= numbers[i]) {
			// 꼭대기: 나보다 앞에 녀석이 같거나 커야 함 && i>0: 첫 번째 원소가 아닐 때
			--i;
		}

		if (i == 0) { // 다음 순열을 만들 수 없는 이미 가장 큰 순열의 상태!
			return false;
		}

		// 2. 꼭대기의 바로 앞자리(i-1)의 값을 크게 만들 교환 값 뒤쪽에서 찾기
		// 여기로 내려왔다는 것 i>0이라는 뜻 
		int j = N - 1;
		while (numbers[i - 1] >= numbers[j]) { // 찾다 찾다 못 찾으면 j가 i까지 오게 됨
			--j;
		}

		// 3. i-1 위치 값과 j 위치값 교환
		swap(numbers, i - 1, j);

		// 4. i 위치부터 맨 뒤까지의 수열을 가장 작은 형태의 오름차순으로 변경
		int k = N - 1;
		while (i < k) {
			swap(numbers, i++, k--);
		}

		return true; // 다음 순열을 만들 수 있었다는 의미 
	}

	public static void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}

}
