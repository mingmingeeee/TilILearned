
import java.io.*;
import java.util.*;

public class No3 {
	
	static int result;
	static int[] card;
	static int[] card1;
	static int[] card2;

	static Queue<Card> q = new ArrayDeque<>();
	
	private static class Card{
		int[] cards;
		int[] cards1;
		int[] cards2;
		int depth;
		
		public Card(int[] cards, int depth) {
			this.cards = cards.clone();
			this.depth = depth;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");

			int N = Integer.parseInt(in.readLine()); // 카드 개수

			String[] split = in.readLine().split(" ");

			card = new int[N];
			for (int i = 0; i < N; i++) {
				card[i] = Integer.parseInt(split[i]);
			}
			q.offer(new Card(card, 0));

//			card1 = new int[N / 2];
//			card2 = new int[N / 2];
//			for (int i = 0; i < N / 2; i++) {
//				card1[i] = Integer.parseInt(split[i]);
//			}
//			for (int i = N / 2; i < N; i++) {
//				card2[i - N / 2] = Integer.parseInt(split[i]);
//			}
			
			result = 0;

		}
		System.out.println(sb);
	}
	
	private static int bfs() {

		
		while(!q.isEmpty()) {
			
			Card c = q.poll();
			
			for (int x = 0; x < card.length; x++) {
				
				System.out.println(Arrays.toString(c.cards));
				
				if(Up(c.cards)) {
					return c.depth;
				}
				if(Down(c.cards)) {
					return c.depth;
				}
				
				for (int i = 0; i < N; i++) {
					if (i + 1 < N && search(card1, card[i]) && search(card2, card[i + 1])) {
						swap(card, i, i + 1);
						i = i + 1;
					}
				}
				

			}
			sb.append(answer).append("\n");
			
		}
			
		}
		
		
	}


	private static boolean Down(int[] card) {
		for (int i = 0; i < card.length - 1; i++) {
			if (card[i] < card[i + 1])
				return false;
		}
		return true;
	}

	private static boolean Up(int[] card) {
		for (int i = 0; i < card.length - 1; i++) {
			if (card[i] > card[i + 1])
				return false;
		}
		return true;
	}

	private static void swap(int[] card, int i, int j) {
		int tmp = card[i];
		card[i] = card[j];
		card[j] = tmp;
	}

	private static boolean search(int[] card, int target) {
		for (int i = 0; i < card.length; i++) {
			if (card[i] == target)
				return true;
		}
		return false;
	}
}
