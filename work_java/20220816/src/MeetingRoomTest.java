import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MeetingRoomTest {
	
	static class Meeting implements Comparable<Meeting>{
		int start, end; // 회의 시작, 종료 시간
		
		public Meeting(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting o) { // 종료 시간 기준 오름차순, 종료 시간이 같다면 시작 시간 기준 오름차순
			// 음수 리턴: 두 개 그대로 (정렬 X) A < B -> 뒤가 큼
			// 양수 리턴: 두 개 바뀜 (정렬 O) A > B -> 앞이 큼
			// 0 리턴: 가만히 (같음) A == B -> 같음
			return this.end != o.end ? this.end - o.end : this.start - o.start;
		}
	
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Meeting[] meetings = new Meeting[N];
		
		for(int i=0; i<meetings.length; i++) {
			meetings[i] = new Meeting(sc.nextInt(), sc.nextInt());
		}
		
		List<Meeting> result = getSchedule(meetings);
		
		System.out.println(result.size());
		
		for(Meeting meeting : result) {
			System.out.println(meeting.start + " " + meeting.end);
		}
	}
	private static List<Meeting> getSchedule(Meeting[] meetings) {
		// 모든 회의를 종료시간 기준 오름차순, 종료시간이 같다면 시작시간 기준 오름차순 정렬
		List<Meeting> result = new ArrayList<>();
		Arrays.sort(meetings);
		result.add(meetings[0]); // 첫 회의 스케줄에 추가 
		
		for(int i=1; i<meetings.length; i++) {
			if( result.get(result.size()-1).end <= meetings[i].start ) { // 양립 가능 회의 조건
				result.add(meetings[i]); // 만족하면 추가
			}
		}
		
		return result;
	}

}
