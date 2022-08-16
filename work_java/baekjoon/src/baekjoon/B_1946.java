package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class B_1946 {

	public static void main(String[] args) throws Exception {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {

			int N = Integer.parseInt(in.readLine());
			
			// 적어도 하나가 떨어지면 선발 X
			
			List<Candidate> list = new ArrayList<>();
			for(int i=0; i<N; i++) {
				String[] split = in.readLine().split(" ");
				list.add(new Candidate(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
			}
			
			Collections.sort(list, new Comparator<Candidate>() {
				@Override
				public int compare(Candidate o1, Candidate o2) {
					// TODO Auto-generated method stub
					return o1.interview - o2.interview;
				}
			});
			
			int count = 1;
			int tmp = list.get(0).paper;
			for(int i=1; i<list.size(); i++) {
				if(tmp > list.get(i).paper) {
					count++;	
					tmp = list.get(i).paper;
				}
			}

			sb.append(count).append("\n");
		}
		System.out.println(sb);
	}

}

class Candidate{
	
	int paper;
	int interview;
	
	public Candidate(int paper, int interview) {
		this.paper = paper;
		this.interview = interview;
	}
	
}