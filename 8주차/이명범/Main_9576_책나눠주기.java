package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_9576_책나눠주기 {

	static int T, N, M;

	public static void main(String[] args) throws Exception {
		T = read();
		for (int tc = 0; tc < T; tc++) {
			N = read();
			M = read();
			boolean[] visit = new boolean[N + 1];
			PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
			for (int i = 0; i < M; i++) {
				int s = read();
				int e = read();
				pq.add(new int[]{s, e});
			}
			int count = 0;
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				for (int i = cur[0]; i <= cur[1]; i++) {
					if (visit[i])
						continue;

					visit[i] = true;
					count++;
					break;
				}
			}
			System.out.println(count);
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
