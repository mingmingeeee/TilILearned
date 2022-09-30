
import java.io.*;
import java.util.*;

public class DP4_KnapsackTest2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int W = sc.nextInt();

		int[] dp = new int[W + 1];

		int[] weights = new int[N + 1];
		int[] profits = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			weights[i] = sc.nextInt();
			profits[i] = sc.nextInt();
		}

		for (int n = 1; n <= N; n++) {
			for (int i = W; i >= 1; i--) {
				if (weights[n] <= i) {
					dp[i] = Math.max(dp[i - weights[n]] + profits[n], dp[i]);
				}
			}
		}

		System.out.println(dp[W]);

	}

}
