import java.util.Arrays;
import java.util.Scanner;

public class Review {

	static int TotalCnt;
	static int N, R;
	static int[] input, nums;
	static boolean[] isSelected; // 중복 방지

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		R = sc.nextInt();

		TotalCnt = 0;

		input = new int[N];
		nums = new int[R];
		isSelected = new boolean[N];
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		int mode = sc.nextInt();

		switch (mode) {
		case 1:
			perm1(0);
			break;

		case 2:
			perm2(0);
			break;

		case 3:
			com1(0, 0);
			break;

		case 4:
			com2(0, 0);
			break;

		case 5:
			subset(0);
			break;

		}
		System.out.println(TotalCnt);

	}

	// 중복 순열
	public static void perm1(int cnt) {

		if (cnt == R) {
			TotalCnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}

		for (int i = 0; i < N; i++) {

			nums[cnt] = input[i];

			perm1(cnt + 1);

		}

	}

	// 순열
	public static void perm2(int cnt) {

		if (cnt == R) {
			TotalCnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				continue;

			nums[cnt] = input[i];
			isSelected[i] = true;
			perm2(cnt + 1);
			isSelected[i] = false;

		}

	}

	// 중복 조합
	public static void com1(int cnt, int start) {

		if (cnt == R) {
			TotalCnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}

		for (int i = start; i < N; i++) {
			nums[cnt] = input[i];
			com1(cnt + 1, i);
		}

	}

	// 조합
	public static void com2(int cnt, int start) {

		if (cnt == R) {
			TotalCnt++;
			System.out.println(Arrays.toString(nums));
			return;
		}

		for (int i = start; i < N; i++) {

			nums[cnt] = input[i];
			com2(cnt + 1, i + 1);

		}

	}

	// 부분 집합
	public static void subset(int index) {

		if (index == N) { // 더 이상 고려할 원소 없음
			TotalCnt++;

			for (int i = 0; i < N; i++) {
				System.out.print(isSelected[i] ? input[i] : "X");
				System.out.print("\t");
			}
			System.out.println();
			return;
		}

		// 현재 값 선택
		isSelected[index] = true;
		subset(index + 1);

		// 현재 값 선택 안 함
		isSelected[index] = false;
		subset(index + 1);

	}

}
