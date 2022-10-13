package algo_ws_29_2;

import java.io.*;
import java.util.*;

// Graham Scan
// 1. y값이 제일 작은 정점 (x값이 제일 작은...)
// 2. 유클리드 기하학 외적 (CCW, O, CW)

public class Main {

	private static class Point {
		public long x;
		public long y;

		public Point(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	// 외적 구하기
	// 반환 값 > 0: 점 A, B 기준으로 C가 반시계 방향에 위치한다.
	// 반환 값 == 0: 점 A, B 기준으로 C가 평행하게 위치한다.
	// 반환 값 < 0: 점 A, B 기준으로 C가 시계 방향에 위치한다.
	private static long ccw(Point A, Point B, Point C) {
		return (B.x - A.x) * (C.y - A.y) - (C.x - A.x) * (B.y - A.y);
	}

	// 맨해튼 거리 구하기
	private static long distance(Point A, Point B) {
		return Math.abs(A.x - B.x) + Math.abs(A.y - B.y);
	}

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(in.readLine()); // 점의 개수

		List<Point> points = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] split = in.readLine().split(" ");
			int x = Integer.parseInt(split[0]);
			int y = Integer.parseInt(split[1]);

			points.add(new Point(x, y));
		}
		
		// 1. y가 가장 작은 점을 구한다. (y가 같다면 x가 작은 점)
		final Point start = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
		for(Point p : points) {
			if(p.y < start.y) {
				start.x = p.x;
				start.y = p.y;
			} else if (p.y == start.y) {
				if(p.x < start.x) {
					start.x = p.x;
					start.y = p.y;
				}
			}
		}
		
		// 2. 1번에서 구한 점에서 나머지 점들을 직선으로 이어 구한 각을 기준으로 정렬한다.
		// return 값이 음수이면 자리 바꿈이 일어나지 않는다.
		points.sort(new Comparator<Point>() {

			@Override
			public int compare(Point B, Point C) {
				// ccw 메서드의 리턴 값이 양수: 반시계 방향이므로 자리 바꿈이 일어나지 않게 -1 곱한다.
				long ccw = ccw(start, B, C) * -1;
				
				// 점 A(start), B 기준으로 C가 평행하게 위치하고 있다면
				if(ccw == 0) {
					// 점 A(start)와 가까운 점을 우선으로 정렬
					return distance(start, B) - distance(start, C) < 0 ?-1 : 1;
				}
				return ccw < 0 ?- 1 : 1;
			}
			
		});
		
		// 3. 2번에서 정렬한 순서대로 조사하면서 블록 껍질인지 아닌지 확인하고 추가한다.
		Stack<Point> stack = new Stack<>();
		stack.add(start);
		
		for(int i=1; i <points.size(); i++) {
			
			// 현재 점이 블록 껍질을 이루는 점들에 포함 여부를 판단한다.
			// 점이 2개 이상 존재하는 상태에서 포함하려는 점이 반시계 방향에 위치해 있는지 확인
			// 반시계 방향이 아닐 경우는 블록 껍질을 이루는 점들에서 second 점을 제외
			// ccw(first, second, next)
			while(stack.size() >= 2 && 
					ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0) {
				stack.pop(); // second 점 제거
			}
			
			// 블록 껍질을 이루는 점들에 포함
			stack.add(points.get(i)); // next형 포함 
			
		}
		
		sb.append(stack.size());
		System.out.println(sb);

	}

}
