package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_1939_중량제한 {

	static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static final int MAX_WEIGHT_LIMIT = 1_000_000_000;
	static int N, M, S, E;
	static List<Edge>[] edges;

	public static void main(String[] args) throws Exception {
		input();
		System.out.println(binarySearch());
	}

	private static int binarySearch() {
		int l = 1;
		int r = MAX_WEIGHT_LIMIT;

		while (l <= r) {
			int m = (l + r) / 2;

			if (bfs(m))
				l = m + 1;
			else
				r = m - 1;
		}
		return r;
	}

	private static boolean bfs(int m) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N + 1];
		q.add(S);
		visit[S] = true;

		while (!q.isEmpty()) {
			Integer cur = q.poll();

			if (cur == E)
				return true;

			for (Edge next : edges[cur]) {
				if (visit[next.v])
					continue;
				if (next.w < m)
					continue;

				q.add(next.v);
				visit[next.v] = true;
			}
		}
		return false;
	}

	private static void input() throws Exception {
		N = read();
		M = read();
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int from = read();
			int to = read();
			int w = read();
			edges[from].add(new Edge(to, w));
			edges[to].add(new Edge(from, w));
		}
		S = read();
		E = read();
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
