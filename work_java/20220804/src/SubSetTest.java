import java.util.Scanner;

// n개의 수를 입력 받고 가능한 모든 부분 집합 생성
public class SubSetTest {

	static int N, totalCnt;
	static int[] input;
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		totalCnt = 0;

		input = new int[N];
		isSelected = new boolean[N]; // index로 맞출 것이기 때문에 N개

		for (int i = 0; i < N; i++) {
			input[i] = scanner.nextInt();
		}

		subset(0);
		System.out.println("총 경우의 수 : " + totalCnt);

	}

	// cnt: 직전까지 고려한 원소 수
	private static void subset(int index) { // index: 내가 고려할 원소 index

		if (index == N) { // 기저조건: 더 이상 고려할 원소가 없다면 부분집합 구성 완성!
			totalCnt++;
			for (int i = 0; i < N; i++) {
				System.out.print(isSelected[i] ? input[i] : "X");
				// 선택된 녀석이라면 가져오고 아니라면 X
				System.out.print("\t");
			}
			System.out.println("\n");
			return;
		}

		// 원소 선택 -> 부분 집합에 넣는
		isSelected[index] = true;
		subset(index + 1);
		// 원소 미선택 -> 부분 집합에 넣지 않는
		isSelected[index] = false;
		subset(index + 1);
	}

}
