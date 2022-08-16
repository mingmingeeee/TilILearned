import java.util.Scanner;

public class SpaceDivideTest {

	private static int white;
	private static int green;
	private static int[][] spaces; // 입력 -> 배열에 저장

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt(); // 한 변의 길이

		// 주어진 2차원 데이터 -> 배열에 저장
		spaces = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				spaces[i][j] = scanner.nextInt();
			}
		}

		// 주어진 영역에 대해 초록색이나 하얀색으로 이루어져 있는지 확인 (재귀 함수로 구현)
		cut(0, 0, N); // cut(시작점 행 번호, 시작점 열 번호, 한 변의 길이)
		System.out.println(white);
		System.out.println(green);

	}

	// r: 탐색 시작 지점의 행, c: 탐색 시작 지점의 열, size: 탐색 영역 한 변의 길이
	// 초록색: 1, 흰색: 0
	private static void cut(int r, int c, int size) {

		// 해당 영역의 색상 확인
		int sum = 0; // 초록색 부분의 개수
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				sum += spaces[i][j];
			}
		}
		
		// 초록색 개수가 영역의 크기와 같으면 초록색이란 뜻
		if(sum == size * size) {
			green++;
		}
		
		// 아니면 흰색이란 뜻
		else if (sum == 0){
			white++;
		}
		
		// 유도 부분
		else { // 전체 초록색도 아니고 흰색도 아닌 경우  -> 색상 섞여있는 경우 
			int half = size/2;
			
			// 4개로 쪼개기: 분할된 각 영역의 공간도 온전한 하나의 공간으로 본다면 동일한 작업 수행해야 함 (재귀 이용)
			cut(r, c, half);
			cut(r, c + half, half);
			cut(r + half, c, half);
			cut(r+half, c+half, half);		
		}
	
	}

}

/**
 * 
8
1 1 0 0 0 0 1 1
1 1 0 0 0 0 1 1
0 0 0 0 1 1 0 0
0 0 0 0 1 1 0 0
1 0 0 0 1 1 1 1 
0 1 0 0 1 1 1 1
0 0 1 1 1 1 1 1
0 0 1 1 1 1 1 1
 */