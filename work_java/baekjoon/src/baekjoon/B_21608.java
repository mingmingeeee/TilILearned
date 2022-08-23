package baekjoon;


import java.io.*;
import java.util.*;

public class B_21608 {
	// r행 c열
	// 1. 교실은 (1,1) ~ (N, N)
	// 2. 학생 번호 1 ~ N^2
	// 3. 학생이 좋아하는 학생 4명 조사함

	static List<Position> blanks = new ArrayList<>();
	static int[][] map;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static class Position {
		int x;
		int y;
		int likes = 0;
		int blank = 0;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Position [x=" + x + ", y=" + y + ", likes=" + likes + ", blank=" + blank + "]";
		}

	}

	static class Student {
		int number;
		int[] likes = new int[4];
		Position p = new Position(0, 0);

		Student(int number, int like1, int like2, int like3, int like4) {
			this.number = number;
			this.likes[0] = like1;
			this.likes[1] = like2;
			this.likes[2] = like3;
			this.likes[3] = like4;
		}

		@Override
		public String toString() {
			return "Student [number=" + number + ", like1=" + likes[0] + ", like2=" + likes[1] + ", like3=" + likes[2]
					+ ", like4=" + likes[3] + "]";
		}

	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine());

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				blanks.add(new Position(i, j));
			}
		}

		Student[] students = new Student[N * N + 1];

		for (int i = 0; i < N * N; i++) {
			String[] split = in.readLine().split(" ");
			students[i] = new Student(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
					Integer.parseInt(split[2]), Integer.parseInt(split[3]), Integer.parseInt(split[4]));
		}

		for (int i = 0; i < N * N; i++) {

			for (int s = 0; s < blanks.size(); s++) {

				for (int d = 0; d < dx.length; d++) {
					int testX = blanks.get(s).x + dx[d];
					int testY = blanks.get(s).y + dy[d];

					if (isRange(testX, testY)) {

						for (int k = 0; k < 4; k++) {
							if (map[testX][testY] == students[i].likes[k]) {
								blanks.get(s).likes++;
							}
						}

						if (map[testX][testY] == 0)
							blanks.get(s).blank++;

					}

				}

			}

			// 4-1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리 정함
			// 4-2. 1을 만족하는 칸이 여러개이면 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리 정함
			// 4-3. 2를 만족하는 칸도 여러개인 경우 행의 번호가 가장 작은 칸으로
			// 4-4. 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리 정함
			Collections.sort(blanks, new Comparator<Position>() {

				public int compare(Position o1, Position o2) {

					if (o2.likes == o1.likes) {

						if (o2.blank == o1.blank) {

							if (o1.x == o2.x)
								return o1.y - o2.y;

							return o1.x - o2.x;
						}

						return o2.blank - o1.blank;
					}

					return o2.likes - o1.likes;
				};

			});

			map[blanks.get(0).x][blanks.get(0).y] = students[i].number;
			students[i].p.x = blanks.get(0).x;
			students[i].p.y = blanks.get(0).y;
			blanks.remove(0);

			for (int j = 0; j < blanks.size(); j++) {
				blanks.get(j).blank = 0;
				blanks.get(j).likes = 0;
			}

		}

		int result = 0;

		for (int i = 0; i < N * N; i++) {

			int x = students[i].p.x;
			int y = students[i].p.y;
			int count = 0;
			for (int d = 0; d < dx.length; d++) {
				int testX = x + dx[d];
				int testY = y + dy[d];

				if (isRange(testX, testY)) {
					for (int k = 0; k < 4; k++) {
						if (map[testX][testY] == students[i].likes[k]) {
							count++;
						}
					}
				}
			}
			int n = 1;
			if (count == 0)
				n = 0;
			for (int k = 1; k < count; k++) {
				n *= 10;
			}
			result += n;

		}

		System.out.println(result);

	}

	private static boolean isRange(int x, int y) {
		if (0 <= x && x < map.length && 0 <= y && y < map[0].length)
			return true;
		return false;
	}

}
