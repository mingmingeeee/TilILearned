package algo_ws_07_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
// 연산 => stack에 숫자 넣어놓고 연산자 만나면 나와서 계산함 = 단말 => 숫자, 중간 => 연산자
// 후위 => 좌, 우, 부 

public class Solution3 {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();

		int T = 10;
		for (int test_case = 1; test_case <= T; test_case++) {
			/**
			 * 1. 입력 파일 객체화
			 */
			sb.append("#" + test_case + " ");
			
			// 정점의 총 수
			int N = Integer.parseInt(in.readLine());

			
			/**
			 * 2. 알고리즘 풀기
			 */
			
			// 정답을 저장할 변수
			// 0: 유효하지 않음
			// 1: 유효함
			// (유효하지 않는 조건일 경우 0으로 변경할 예정이므로 1로 초기화)
			int answer = 1; 
			
			// 문제에서 완전 이진 트리 형식으로 주어진다는 조건이 있기 때문에
			// 두번째(노드의 데이터) 값만 가지고 연산자 노드인지 아닌지를 판단하면 된다. 
			for(int i=1; i<=N; i++) {
				String[] split = in.readLine().split(" ");
				char data = split[1].charAt(0);
				
				// 각 행의 두 번째 데이터가 연산자일 경우는 자식 노드 2개를 반드시 가져야 한다. 
				if(data == '+' || data == '-' || data == '*' || data == '/') {
					if(split.length != 4) {	// 연산자 노드의 자식 노드개 2개라면 길이가 4여야 한다.
						answer = 0; // 유효하지 않음
						
						// 나머지 입력 데이터 건너뛰기
						while(1 < N) {
							i++;
							in.readLine();
						}
					}
				} else { // 피연산자 노드는 자식 노드가 없어야 하고, 숫자이어야 한다.
					if(split.length != 2) {
						answer = 0; // 유효하지 않음
						
						// 나머지 입력 데이터 건너 뛰기
						while(1 < N) {
							i++;
							in.readLine();
						}
					}	
				}
			}
			
			sb.append(answer);
			sb.append("\n");

		}

		/**
		 * 3. 정답 출력
		 */

		System.out.println(sb);
	}
	

}
