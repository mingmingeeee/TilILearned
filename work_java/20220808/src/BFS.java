import java.util.ArrayDeque;
import java.util.Queue;

public class BFS {
	
	public static void main(String[] args) {

		NOde n1 = new NOde(3, new NOde(6), new NOde(7));
		NOde n2 = new NOde(2, new NOde(4), new NOde(5));
		NOde root = new NOde(1, n2, n1);
		
		BFS(root);
		
	}
	
	public static void BFS(NOde root) {
		
		Queue<NOde> queue = new ArrayDeque<>(); // 큐 생성
		queue.offer(root); // 루트를 큐에 삽입
		
		while(!queue.isEmpty()) { // 큐가 비어 있지 않은 경우
			NOde node = queue.poll(); // 큐 첫번 째 원소 반환
			System.out.print(node.data + " "); // 방문
			
			if(node.left!=null) {
				queue.offer(node.left);
			}
			
			if(node.right!=null) {
				queue.offer(node.right);
			}
			
		}
		
		
		
	}

}

class NOde{
	
	public int data;
	public NOde left;
	public NOde right;
	
	public NOde(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	public NOde(int data, NOde left, NOde right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
}
