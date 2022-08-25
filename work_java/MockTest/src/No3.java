// 문제 잘 읽고...가지 치기...끊어주기 잘 하기...
import java.io.*;
import java.util.*;

public class No3 {

	static int result;
	static int[] card;

	static Queue<Card> q = new ArrayDeque<>();

	private static class Card {
		int[] cards = new int[card.length];
		int[] cards1 = new int[card.length / 2];
		int[] cards2 = new int[card.length / 2];
		int depth;

		public Card(int[] cards, int depth, int[] cards1, int[] cards2) {
			this.cards = cards.clone();
			this.depth = depth;
			this.cards1 = cards1.clone();
			this.cards2 = cards2.clone();
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(in.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#" + test_case + " ");
			
			q.clear();

			int N = Integer.parseInt(in.readLine()); // 카드 개수

			String[] split = in.readLine().split(" ");

			card = new int[N];
			for (int i = 0; i < N; i++) {
				card[i] = Integer.parseInt(split[i]);
			}
			int[] card1 = new int[N / 2];
			int[] card2 = new int[N / 2];
			divide(card1, card2, card);

			q.offer(new Card(card, 0, card1, card2));
			int result = 0;
			if (Up(card)) {
				result = 0;
			}
			else if (Down(card)) {
				result = 0;
			}
			else {
				q.offer(new Card(card, 0, card1, card2));
				result = bfs();
			}
			sb.append(result).append("\n");

		}
		System.out.println(sb);
	}

	private static void divide(int[] card1, int[] card2, int[] card) {
		int N = card.length;
		for (int i = 0; i < N / 2; i++) {
			card1[i] = card[i];
		}
		for (int i = N / 2; i < N; i++) {
			card2[i - N / 2] = card[i];
		}
	}

	private static int bfs() {

		int depth = 0;
		
		while (!q.isEmpty()) {
			int size = q.size();
			
			if(depth > 4)
				return -1;
			
			for (int s = 0; s < size; s++) {
				Card c = q.poll();
				
				int N = c.cards.length;

				for (int x = 0; x < card.length; x++) {
					
					for (int i = 0; i < N; i++) {
						if (i + 1 < N && search(c.cards1, c.cards[i]) && search(c.cards2, c.cards[i + 1])) {
							swap(c.cards, i, i + 1);
							i = i + 1;
						}
					}
					if (Up(c.cards)) {
						return c.depth + 1;
					}
					if (Down(c.cards)) {
						return c.depth + 1;
					}
					int[] card1 = new int[card.length / 2];
					int[] card2 = new int[card.length / 2];
					divide(card1, card2, c.cards);
					q.offer(new Card(c.cards, depth + 1, card1, card2));

				}
			}
			depth++;
		}
		return -1;

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

