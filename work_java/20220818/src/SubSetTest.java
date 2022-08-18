import java.util.Scanner;

// n개의 자연수를 입력 받고 목표합이 주어지면 목표합에 해당하는 부분집합을 출력 
public class SubSetTest {

	static int N, totalCnt, S;
	static int[] input;
	static boolean[] isSelected;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		S = scanner.nextInt(); // 목표합 
		totalCnt = 0;

		input = new int[N];
		isSelected = new boolean[N]; // index로 맞출 것이기 때문에 N개

		for (int i = 0; i < N; i++) {
			input[i] = scanner.nextInt();
		}

		subset(0, 0);
		System.out.println("총 경우의 수 : " + totalCnt);
		System.out.println("총 호출 횟수: " + callCnt);
	}

	static int callCnt;
	// cnt: 직전까지 고려한 원소 수
	private static void subset(int index, int sum) { // index: 내가 고려할 원소 index

		callCnt++;

		if (sum == S) { // 기저조건: 더 이상 고려할 원소가 없다면 부분집합 구성 완성!
			totalCnt++;
			for (int i = 0; i < N; i++) {
				if(isSelected[i]) System.out.print(input[i] + "\t");
			}
			System.out.println();
			return;
		}
		// sum > S
		if(sum > S || index == N) return; // sum 초과 -> 갈 필요 X or 다 돌았는데 없음

		// 끝까지 다 가보지 않았고 모든 원소를 고려해보지 않았고 sum이 S를 넘지 않는다면 고려 다시!
		// sum < S
		// 원소 선택 
		isSelected[index] = true;
		subset(index + 1, sum + input[index]);
		// 원소 미선택 
		isSelected[index] = false;
		subset(index + 1, sum);
	}

}
