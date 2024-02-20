package boj;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main_17071_숨바꼭질5 {

	static int N, K;

	public static void main(String[] args) throws Exception {
		N = read();
		K = read();
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[2][500001];
		q.add(N);
		visit[0][N] = true;

		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			if (K > 500000)
				return -1;
			if (visit[count % 2][K])
				return count;

			for (int i = 0; i < size; i++) {
				Integer cur = q.poll();

				if (cur == K)
					return count;

				for (int j = -1; j <= 1; j += 2) {
					int next = cur + j;

					if (next < 0 || next > 500000)
						continue;
					if (visit[(count + 1) % 2][next])
						continue;

					q.add(next);
					visit[(count + 1) % 2][next] = true;
				}

				int next = 2 * cur;

				if (next < 0 || next > 500000)
					continue;
				if (visit[(count + 1) % 2][next])
					continue;

				q.add(next);
				visit[(count + 1) % 2][next] = true;
			}
			count++;
			K += count;
		}
		return -1;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
