package algo_ws_04_1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main2 {

	public static void main(String[] args) throws Exception {

		/**
		 * 0. 입력파일 읽어들이기
		 */
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		// 결과를 한 번에 출력하기 위한 StringBuilder
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		/**
		 * 1. 입력 파일 객체화
		 */
		// 탑의 수
		int N = Integer.parseInt(in.readLine());
		int[] towers = new int[N];

		// 탑들의 높이 
		String[] split = in.readLine().split(" ");
		for(int i = 0; i < N; i++) {
			towers[i] = Integer.parseInt(split[i]);
		}

		/**
		 * 2. 알고리즘 풀기
		 */

		Stack<Tower> stack = new Stack<>();
		
		for(int i=1; i<=N; i++) {
			while(!stack.isEmpty()) {
				// 스택의 top이 현재 입력 값(높이)보다 크면, 신호 수신 가능
				if(stack.peek().height > towers[i - 1]) {
					sb.append(stack.peek().no);
					sb.append(" ");
					break;
				}
				// 스택의 top이 현재 입력 값보다 작으면, 신호 수신 불가능
				stack.pop();
			}
			// 스택이 비었다는 뜻은 신호 수신 가능한 탑이 없다는 뜻이므로 0 출력 
			if(stack.isEmpty()) {
				sb.append("0 ");
			}
			
			// 현재 입력 값을 가지고 탑을 생성하여 스택에 push
			stack.push(new Tower(i, towers[i - 1]));
		}
		
		
		
		/**
		 * 3. 정답 출력
		 */
		System.out.println(sb);
	}

}

class Tower{
	
	public int no; // 탑의 번호: 숫자가 적을 수록 왼쪽에 위치
	public int height; // 탑의 높이
	
	public Tower(int no, int height){
		this.no = no;
		this.height = height;
	}
}