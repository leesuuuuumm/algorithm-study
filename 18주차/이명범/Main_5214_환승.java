package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_5214_환승 {

	private static final int LENGTH = 100_000;
	static int N, K, M;
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		N = read();
		K = read();
		M = read();
		edges = new List[LENGTH + N + 1];
		for (int i = 1; i <= LENGTH + N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < K; j++) {
				int v = read();

				edges[i].add(v + LENGTH);
				edges[v + LENGTH].add(i);
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[LENGTH + N + 1];
		q.add(LENGTH + 1);
		visit[LENGTH + 1] = true;

		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Integer cur = q.poll();

				if (cur == LENGTH + N)
					return count / 2 + 1;

				for (Integer next : edges[cur]) {
					if (visit[next])
						continue;

					q.add(next);
					visit[next] = true;
				}
			}
			count++;
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
