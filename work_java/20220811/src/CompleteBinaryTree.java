import java.util.*;

// 완전 이진 트리
public class CompleteBinaryTree {

	private char[] nodes;
	private int lastIndex; // 마지막 노드 인덱스
	private final int SIZE;

	public CompleteBinaryTree(int size) {
		SIZE = size;
		nodes = new char[size + 1]; // 1인덱스부터 사용
	}

	public boolean add(char e) { // 완전 이진트리에 맞게 추가
		if (lastIndex == SIZE) {
			return false; // 못했다면 (size랑 lastIndex랑 같아서 -> 포화상태여서) false
		}
		nodes[++lastIndex] = e;
		return true; // 잘 추가했다면 true
	}

	public void bfs() {

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1); // 루트 노드의 인덱스 부터

		while (!queue.isEmpty()) {
			int current = queue.poll(); // 방문 대상이 있을 때까지 반복
			// 방문 차례인 대상 정보 꺼내기
			System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리

			// 현재 방문 노드의 자식 노드들을 대기열에 넣기
			if (current * 2 <= lastIndex) { // 왼쪽 자식
				queue.offer(current * 2);
			}
			if (current * 2 + 1 <= lastIndex) { // 오른쪽 자식
				queue.offer(current * 2 + 1);
			}
		}
		System.out.println();

	}

	// "너비 별로 처리"
	public void bfs2() {

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(1); // 루트 노드의 인덱스 부터

		while (!queue.isEmpty()) {
			int size = queue.size(); // 큐 크기 확인 -> 동일 너비 대상 개수

			while (--size >= 0) { // 반복 진입 전 큐 크기 만큼만 반복
				int current = queue.poll();
				System.out.print(nodes[current] + " ");

				// 현재 방문 노드의 자식 노드들을 대기열에 넣기
				if (current * 2 <= lastIndex) { // 왼쪽 자식
					queue.offer(current * 2);
				}
				if (current * 2 + 1 <= lastIndex) { // 오른쪽 자식
					queue.offer(current * 2 + 1);
				}
			}
			System.out.println();
		}

	}

	public void dfs() {

		Stack<Integer> queue = new Stack<Integer>();
		queue.push(1); // 루트 노드의 인덱스 부터

		while (!queue.isEmpty()) {
			int current = queue.pop(); // 방문 대상이 있을 때까지 반복
			// 방문 차례인 대상 정보 꺼내기
			System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리

			// 현재 방문 노드의 자식 노드들을 대기열에 넣기
			if (current * 2 <= lastIndex) { // 왼쪽 자식
				queue.push(current * 2);
			}
			if (current * 2 + 1 <= lastIndex) { // 오른쪽 자식
				queue.push(current * 2 + 1);
			}
		}
		System.out.println();

	}

	// 자식들 부르는 순서: 방문해서 처리하고 다음 자식들집 갈건지
	public void dfsByPreOrder(int current) { // flat -> current: 현재 탐색할 대상

		System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리

		if (current * 2 <= lastIndex) {
			dfsByPreOrder(current * 2);
		}

		if (current * 2 + 1 <= lastIndex) {
			dfsByPreOrder(current * 2 + 1);
		}

	}
	
	// 자식을 부르는 순서: 자식들 집을 가고 방문해서 처리할 건지
	// Inorder: 이진트리라 가능! 가운데가 정해져있기 때문에~~
	public void dfsByInOrder(int current) { // flat -> current: 현재 탐색할 대상

		// 1. 먼저 체크해서 조건 만족안 되면 안 보낼 것인가
		if (current * 2 <= lastIndex) {
			dfsByInOrder(current * 2);
		}
		
		System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리

		if (current * 2 + 1 <= lastIndex) {
			dfsByInOrder(current * 2 + 1);
		}
		
		// 2. 일단 재귀 타게 한 다음 나중에 검사할 것인가 
//		if(current>lastIndex) return;
//		dfsByInOrder(current*2);
//		System.out.println(nodes[current] + " ");
//		dfsByInOrder(current*2+1);

	}
	
	// 자식을 부르는 순서: 나~중에 처리할 건지
		public void dfsByPostOrder(int current) { // flat -> current: 현재 탐색할 대상

			// 1. 먼저 체크해서 조건 만족안 되면 안 보낼 것인가
			if (current * 2 <= lastIndex) {
				dfsByPostOrder(current * 2);
			}

			if (current * 2 + 1 <= lastIndex) {
				dfsByPostOrder(current * 2 + 1);
			}

			System.out.print(nodes[current] + " "); // 방문해서 해야할 일 처리

		}

}
