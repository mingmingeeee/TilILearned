package algo_ws_10_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	private static ArrayList<Chicken> chickens = new ArrayList<>();
	private static ArrayList<Home> homes = new ArrayList<>();
	private static final int HOME = 1;
	private static final int CHICKEN = 2;
	static int totalCnt = 0;

	private static int N;
	private static int M;
	private static boolean[] isSelected;
	private static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String[] split = in.readLine().split(" ");

		N = Integer.parseInt(split[0]);
		M = Integer.parseInt(split[1]);

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

		isSelected = new boolean[chickens.size()];
		subset(0);
		System.out.println(min);
	}

	private static void subset(int index) {

		if (index == chickens.size()) { // 더 이상 원소 없을 때

			int count = 0;
			for (int j = 0; j < chickens.size(); j++) {
				if (isSelected[j]) {
					count++;
				}
			}
			if (!(count > M || count == 0)) {
				
				int distance_sum = 0;
				for (int i = 0; i < homes.size(); i++) {
					int min_ch = Integer.MAX_VALUE;
					for (int j = 0; j < chickens.size(); j++) {
						if (isSelected[j]) {
							int distance = Math.abs(homes.get(i).x - chickens.get(j).x)
									+ Math.abs(homes.get(i).y - chickens.get(j).y);
							if (min_ch > distance)
								min_ch = distance;
						}
					}
					distance_sum += min_ch;
				}

				if (min > distance_sum)
					min = distance_sum;
			}
			return;
		}

		isSelected[index] = true;
		subset(index + 1);

		isSelected[index] = false;
		subset(index + 1);

	}

}

//class Chicken {
//	int x;
//	int y;
//
//	Chicken(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//}
//
//class Home {
//	int x;
//	int y;
//
//	Home(int x, int y) {
//		this.x = x;
//		this.y = y;
//	}
//}