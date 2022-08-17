import java.util.Scanner;

public class Review {

	static int[] numbers;
	static int N;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		numbers = new int[N];

		for (int i = 0; i < N; i++) {
			numbers[i] = sc.nextInt();
		}

		generateSubSet();

	}
	
	private static void generateSubSet() {
		
		for(int flag = 0; flag < 1 << N; flag++) {
			
			for(int i=0; i<N; i++) {
				if((flag & (1 << i)) != 0) {
					System.out.print(numbers[i] + " ");
				}
			}
			System.out.println();
			
		}
		
	}

}
