import java.io.*;
import java.util.*;

// 1. 같은 행인지 비교 
// 2. 같은 열 비교: 열 위치 값 비교하면 끝남
// 3. 대각선 비교: |행차이| == |열차이|

public class NQueenTest {

	static int N, cols[], answer;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// 2차원 배열로 하지 않고
		// 1차원 배열로 할 거임 -> 0행: 1열, 1행: 2열... 이런 식으로 -> 1. 어차피 행이 겹치는 부분이 없음
		cols = new int[N + 1]; // 1행부터 사용할 거라 N + 1
		answer = 0;

		setQueen(1);
		System.out.println(answer);

	}

	//	111. 갔다가 돌아오는 코드
//	public static void setQueen(int rowNo) {// 하나의 퀸만 가능한 모든 곳에 놓아보기
//		// rowNo: 몇번 째 퀸에 놓을 것인지?
//
//		// 유망성 체크 -> 직전까지의 상황이 유망하지 않으면 현재 퀸 놓을 필요 없으니 백트랙!!!
//		if (!(isAvailable(rowNo - 1)))
//			return;
//
//		if (rowNo > N) { // 퀸을 다 놓았다면... 유망한 길을 따라왔으니 이것이 정답이다!!!
//			answer++; // 정답 수 증가
//			return;
//		}
//		
//		for (int i = 1; i <= N; i++) {
//			cols[rowNo] = i; // 행에 열 번호 넣고
//			setQueen(rowNo + 1); // 다음 퀸으로
//		}

//	}

	// 222. 미리 놓아보고 가는 코드 
	public static void setQueen(int rowNo) {

		if (rowNo > N) {
			answer++;
			return;
		}

		for (int i = 1; i <= N; i++) {
			cols[rowNo] = i;
			if (isAvailable(rowNo - 1))
				setQueen(rowNo + 1);
		}

	}

	public static boolean isAvailable(int rowNo) {

		for (int i = 1; i < rowNo; i++) {
			// 같은 열이거나 ||
			// 열 차이가 같거나~행 차이가 같거나
			// rowNo가 제일 큰 거고 앞에 애들이랑 비교할 거니까 절대값 체크 X
			if (cols[i] == cols[rowNo] || rowNo - i == Math.abs(cols[rowNo] - cols[i]))
				return false;
		}
		return true;
	}

}
