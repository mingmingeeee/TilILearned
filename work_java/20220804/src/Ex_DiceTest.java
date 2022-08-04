import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Ex_DiceTest {

	static int N, totalCnt;
	static int[] numbers;
	static boolean[] isSelected;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt(); // 던지는 주사위 수
		int mode = scanner.nextInt(); // 던지기 모드

		numbers = new int[N];

		switch (mode) {
		case 1: // 중복 순열
			dice1(0);
			break; 
		case 2: // 순열
			isSelected = new boolean[7]; // 1~6 주사위눈 선택 여부
			dice2(0);
			break;
		case 3: // 중복 조합
			dice3(0, 1);
			break;
		case 4: // 조합
			dice4(0, 1);
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			return;
		}

		System.out.println("총 경우의 수 : " + totalCnt);
	}

	// 주사위 던지기 1: 중복 순열
	private static void dice1(int cnt) {
		if (cnt == N) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// 가능한 모든 수 시도
		for (int i = 1; i <= 6; i++) { // 주사위 눈은 1~6

			// 수의 중복 선택이 가능하므로 중복 체크 필요 없음
			// 해당 수 선택
			numbers[cnt] = i;
			// 다음 주사위 던지러 가기
			dice1(cnt + 1);
		}

	}

	// 주사위 던지기 2: 순열
	private static void dice2(int cnt) {
		if (cnt == N) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// 가능한 모든 수 시도
		for (int i = 1; i <= 6; i++) { // 주사위 눈은 1~6
			// 중복 체크 필요
			if (isSelected[i])
				continue;
			// 해당 수 선택
			numbers[cnt] = i;
			isSelected[i] = true;
			// 다음 주사위 던지러 가기
			dice2(cnt + 1);
			isSelected[i] = false;
		}

	}

	// 주사위 던지기 3: 중복 조합
	private static void dice3(int cnt, int start) {

		if (cnt == N) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// 가능한 모든 수 시도
		for (int i = start; i <= 6; i++) { // 주사위 눈은 1~6
			// 해당 수 선택
			numbers[cnt] = i;
			// 다음 주사위 던지러 가기
			dice3(cnt + 1, i);
		}

	}

	// 주사위 던지기 4: 조합
	private static void dice4(int cnt, int start) {
		if (cnt == N) {
			totalCnt++;
			System.out.println(Arrays.toString(numbers));
			return;
		}

		// 가능한 모든 수 시도
		for (int i = start; i <= 6; i++) { // 주사위 눈은 1~6
			// 해당 수 선택
			numbers[cnt] = i;
			// 다음 주사위 던지러 가기
			dice4(cnt + 1, i + 1);
		}

	}

}
