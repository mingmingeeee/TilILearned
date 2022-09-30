
import java.io.*;
import java.util.*;

public class DP4_KnapsackTest3 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int W = sc.nextInt();

		int[] dp1 = new int[W + 1];
		int[] dp2 = new int[W + 1];

		int[] weights = new int[N + 1];
		int[] profits = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			weights[i] = sc.nextInt();
			profits[i] = sc.nextInt();
		}

		for (int n = 1; n <= N; n++) {
			dp1 = dp2.clone();
			for (int i = 1; i <= W; i++) {
				if (weights[n] <= i) {
					dp2[i] = Math.max(dp1[i - weights[n]] + profits[n], dp1[i]);
				}
			}
		}

		System.out.println(dp2[W]);

	}

}
