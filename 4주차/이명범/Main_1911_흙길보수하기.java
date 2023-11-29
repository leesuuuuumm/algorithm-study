package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_1911_흙길보수하기 {

	static class Interval {
		int s;
		int e;

		public Interval(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

	static int N, L;
	static PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparing(o -> o.s));

	public static void main(String[] args) throws Exception {
		input();
		int count = 0;
		int index = 0;
		while (!pq.isEmpty()) {
			Interval interval = pq.poll();

			if (index < interval.s)
				index = interval.s;

			if (index > interval.e)
				continue;

			int value = (interval.e - index) / L;
			if ((interval.e - index) % L != 0)
				value++;

			count += value;
			index += L * value;
		}
		System.out.println(count);
	}

	private static void input() throws Exception {
		N = read();
		L = read();
		for (int i = 0; i < N; i++) {
			int s = read();
			int e = read();
			pq.add(new Interval(s, e));
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
