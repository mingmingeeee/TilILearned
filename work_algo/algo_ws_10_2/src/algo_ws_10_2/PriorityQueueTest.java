package algo_ws_10_2;


import java.util.*;

public class PriorityQueueTest {
	
	public static void main(String[] args) {
		// 기본 정렬: 오름차순
//		PriorityQueue<Integer> queue = new PriorityQueue<>();
//		
//		queue.offer(10);
//		queue.offer(5);
//		queue.offer(11);
//		queue.offer(-1);
//		
//		while(!queue.isEmpty())
//			System.out.println(queue.poll());
		
		Queue<Student> queue = new PriorityQueue<>(new Comparator<Student>() {
			
			@Override
			public int compare(Student o1, Student o2) {
				
				return o1.getScore() - o2.getScore();
			}
		});

		
		queue.offer(new Student(5, 10));
		queue.offer(new Student(7, 60));
		queue.offer(new Student(3, 40));
		queue.offer(new Student(1, 30));
		
		while(!queue.isEmpty())
			System.out.println(queue.poll());
	}

}

class Student {
	int no;
	int score;
	
	Student(int no, int score){
		this.no = no;
		this.score = score;
	}
	
	
//
//	@Override
//	public int compareTo(Student o) {
//		
//		return this.no - o.no;
//	}

	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	@Override
	public String toString() {
		return "Student [no=" + no + ", score=" + score + "]";
	}
	
	
	
	
}