package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_19598_최소회의실개수 {
	static class Meeting {
		int s;
		int e;

		public Meeting(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

	static int N;
	static PriorityQueue<Meeting> meetings = new PriorityQueue<>(Comparator.comparing(o -> o.s));
	static PriorityQueue<Integer> endTimes = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		int max = 0;
		while (!meetings.isEmpty()) {
			Meeting cur = meetings.poll();
			endTimes.add(cur.e);
			while (!endTimes.isEmpty() && endTimes.peek() <= cur.s) {
				endTimes.poll();
			}
			max = Math.max(max, endTimes.size());
		}
		System.out.println(max);
	}

	private static void input() throws Exception {
		N = read();
		for (int i = 0; i < N; i++) {
			int s = read();
			int e = read();
			meetings.add(new Meeting(s, e));
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
