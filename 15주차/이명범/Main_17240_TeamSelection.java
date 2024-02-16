package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_17240_TeamSelection {
	static class Player {
		int[] scores;

		public Player(int[] scores) {
			this.scores = scores;
		}
	}
	static int N;
	static PriorityQueue<Player>[] pq;
	static int[] scores;

	public static void main(String[] args) throws Exception {
		input();
		boolean[] isChecked = new boolean[5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (isChecked[j])
					continue;

				Player cur = pq[j].poll();
				int score = cur.scores[j];
				int index = j;

				for (int k = 0; k < 5; k++) {
					if (j == k)
						continue;
					if (isChecked[k])
						continue;
					if (pq[k].isEmpty())
						continue;
					if (pq[k].peek() != cur)
						continue;

					Player o = pq[k].poll();
					int oScore = o.scores[k];

					int diff = score - (pq[j].isEmpty() ? 0 : pq[j].peek().scores[j]);
					int oDiff = oScore - (pq[k].isEmpty() ? 0 : pq[k].peek().scores[k]);

					if (diff < oDiff) {
						score = oScore;
						index = k;
					}
				}
				scores[index] = Math.max(scores[index], score);
				isChecked[index] = true;
			}
		}

		int sum = 0;
		for (int score : scores) {
			sum += score;
		}
		System.out.println(sum);
	}

	private static void input() throws Exception {
		N = read();
		pq = new PriorityQueue[5];
		scores = new int[5];
		for (int i = 0; i < 5; i++) {
			int f = i;
			pq[i] = new PriorityQueue<>(Comparator.comparing(o -> -o.scores[f]));
		}
		for (int i = 0; i < N; i++) {
			int a = read();
			int b = read();
			int c = read();
			int d = read();
			int e = read();
			Player player = new Player(new int[]{a, b, c, d, e});

			for (int j = 0; j < 5; j++) {
				pq[j].add(player);
			}
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
