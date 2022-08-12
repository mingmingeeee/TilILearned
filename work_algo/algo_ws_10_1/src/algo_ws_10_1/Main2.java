package algo_ws_10_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main2 {

	static final int HOME = 1;
	static final int CHICKEN = 2;

	static int N;
	static int M;
	static ArrayList<Home> homes;
	static ArrayList<Chicken> chickens;
	static Chicken[] nums;

	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");
		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

		homes = new ArrayList<>();
		chickens = new ArrayList<>();

		for (int x = 0; x < N; x++) {
			split = in.readLine().split(" ");
			for (int y = 0; y < N; y++) {
				int n = Integer.parseInt(split[y]);
				if (n == HOME) {
					homes.add(new Home(x, y));
				} else if (n == CHICKEN) {
					chickens.add(new Chicken(x, y));
				}
			}
		}

		nums = new Chicken[M];
		comb(0, M, 0);

		sb.append(min);
		System.out.println(sb);

	}

	public static void comb(int cnt, int R, int start) {

		if (cnt == R) {
			int sum = 0;
			for (int i = 0; i < homes.size(); i++) {
				int min_ch = Integer.MAX_VALUE;
				for (int j = 0; j < R; j++) {
					int distance = Math.abs(homes.get(i).x - nums[j].x) + Math.abs(homes.get(i).y - nums[j].y);
					if (min_ch > distance)
						min_ch = distance;
				}
				sum += min_ch;
			}
			if (min > sum)
				min = sum;
			return;
		}

		for (int i = start; i < chickens.size(); i++) {
			nums[cnt] = chickens.get(i);
			comb(cnt + 1, R, i + 1);
		}

	}

}

class Home {
	int x;
	int y;

	public Home(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Chicken {
	int x;
	int y;

	public Chicken(int x, int y) {
		this.x = x;
		this.y = y;
	}
}