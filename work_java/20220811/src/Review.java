import java.util.Arrays;
import java.util.Scanner;

public class Review {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int[] input = new int[N];
		
		// 조합 위한 mask
		int R = sc.nextInt();
		int[] mask = new int[N];
		for(int i = N-1; i>=N-R; i--) {
			mask[i] = 1; 
		}

		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		}

		// 전처리: 순열에 쓰일 수들을 오름차순 정렬 -> 가장 작은 순열 만들기 위함
		Arrays.sort(input);

		do {
			for(int i = 0; i < N; i++) {
				if(mask[i]==1) System.out.print(input[i]+ " " );
			}
			System.out.println();
		} while (np(mask));

	}

	public static boolean np(int[] numbers) {
		int N = numbers.length;
		
		int i = N - 1;
		while(i > 0 && numbers[i-1] >= numbers[i]) {
			--i;
		}
		if(i==0)
			return false;
		
		int j = N - 1;
		while(numbers[i-1] >= numbers[j]) {
			--j;
		}

		swap(numbers, i-1, j);

		int k = N - 1;
		while(i<k) {
			swap(numbers, i++, k--);
		}
		
		return true;
	}
	
	public static void swap(int[] numbers, int i, int j) {
		int tmp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = tmp; 
	}

}
