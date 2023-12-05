package boj;

import java.util.PriorityQueue;

public class Main_2836_수상택시 {

	static class Client {
		int s;
		int e;

		public Client(int s, int e) {
			this.s = s;
			this.e = e;
		}
	}

	static int N, M;
	static PriorityQueue<Client> pq = new PriorityQueue<>(
		(o1, o2) -> o1.s - o2.s == 0 ? Integer.compare(o1.e, o2.e) : Integer.compare(o1.s, o2.s)
	);

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();

		long result = M;
		for (int i = 0; i < N; i++) {
			int s = read();
			int e = read();

			if (s > e)
				pq.add(new Client(e, s));
		}

		int ps = 0;
		int pe = 0;

		while (!pq.isEmpty()) {
			Client cur = pq.poll();

			if (cur.s < pe) {
				pe = Math.max(pe, cur.e);
			} else {
				result += (pe - ps) * 2L;
				ps = cur.s;
				pe = cur.e;
			}
		}
		System.out.println(result + (pe - ps) * 2L);
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
