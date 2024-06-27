package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_14247_나무자르기 {

	static class Index {
		long value;
		int index;

		public Index(long value, int index) {
			this.value = value;
			this.index = index;
		}
	}

	static int N;
	static int[] H;
	static PriorityQueue<Index> pq;

	public static void main(String[] args) throws Exception {
		N = read();
		H = new int[N];
		pq = new PriorityQueue<>(Comparator.comparing(o -> o.value));
		for (int i = 0; i < N; i++) {
			H[i] = read();
		}
		for (int i = 0; i < N; i++) {
			pq.add(new Index(read(), i));
		}
		long result = 0;
		for (int i = 0; i < N; i++) {
			Index cur = pq.poll();
			result += H[i] + cur.value * i;
		}
		System.out.println(result);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
