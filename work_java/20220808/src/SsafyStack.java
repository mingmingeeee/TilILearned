
public class SsafyStack<E> implements IStack<E> {

	private Node<E> top; // 첫 번째 타입 저장할 것 -> 더미 노드 아님 !!!
	
	@Override
	public void push(E data) { // 첫 노드 삽입 알고리즘 
		top = new Node<E>(data, top);
	}

	@Override
	public E pop() {
		if(isEmpty()) {
			System.out.println("공백 스택이어서 작업이 불가능합니다.");
			return null;
		}
		
		Node<E> popNode = top;
		top = popNode.link;
		popNode.link = null;
		
		return popNode.data;
	}

	@Override
	public E peek() {
		if(isEmpty()) {
			System.out.println("공백 스택이어서 작업이 불가능합니다.");
			return null;
		}
		return top.data; // 그렇지 않다면 
	}

	@Override
	public boolean isEmpty() {
		return top == null; // null 이면 true return 
	}

	@Override
	public int size() {
		int cnt = 0;
		
		for(Node<E> temp = top; temp != null; temp = temp.link) {
			++cnt;
		}
		
		return cnt;
	}

}
