import java.util.Arrays;
import java.util.Scanner;

public class Homeworks {
	
	static int N;
	static int[] K;
	static int[] nums;
	static boolean[] isSelected;
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		int[] input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}
		
		K = new int[N];
		nums = new int[N];
		isSelected = new boolean[N];
		for(int i=0; i<N; i++) {
			K[i] = input[i]; 
		}

		// 전처리: 순열에 쓰일 수들을 오름차순 정렬 -> 가장 작은 순열 만들기 위함
		Arrays.sort(input);
		
		// np
		long startTime = System.nanoTime();
		do {
			System.out.println(Arrays.toString(input));
		}while(np(input));
		long endTime = System.nanoTime();
		
		System.out.println("np: " + (endTime - startTime));
		
		// 재귀
		startTime = System.nanoTime();
		perm(0);
		endTime = System.nanoTime();
		
		System.out.println("재귀: " + (endTime - startTime));

	}
	
	private static void perm(int cnt) {
		if(cnt==N) {
			return;
		}
		
		
		for(int i=0; i<N; i++) {
			if(isSelected[i]) continue;
			
			isSelected[i] = true; 
			nums[cnt] = K[i];
			
			perm(cnt+1);
			
			isSelected[i] = false; 
		}
	}
	
	private static boolean np(int[] numbers) {
		
		int N = numbers.length;
		
		int i = N - 1;
		while(i>=0 && numbers[i-1] >= numbers[i]) {
			--i;
		}
		if(i==0)
			return false;
		
		int j = N - 1;
		while(numbers[i-1]>=numbers[j]) {
			--j;
		}
		
		swap(numbers, i-1, j);
		
		int k = N - 1;
		while(i<k) {
			swap(numbers, i++, k--);
		}
		
		return false;
	}
	
	private static void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp;
	}

}
