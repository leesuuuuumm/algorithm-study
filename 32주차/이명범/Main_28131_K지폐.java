package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main_28131_K지폐 {

	static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static int N, M, K, S, T;
	static List<Edge>[] elist;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		K = read();
		elist = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			elist[i] = new ArrayList<>();
		}
		S = read();
		T = read();
		for (int i = 0; i < M; i++) {
			int from = read();
			int to = read();
			int w = read();

			elist[from].add(new Edge(to, w));
		}
		int result = dijkstra();
		System.out.println(result == -1 ? "IMPOSSIBLE" : result);
	}

	private static int dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
		int[][] dist = new int[N + 1][K];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		pq.add(new Edge(S, 0));
		dist[S][0] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.v == T && cur.w % K == 0)
				return cur.w;

			for (Edge next : elist[cur.v]) {
				if (dist[next.v][(next.w + cur.w) % K] <= next.w + cur.w)
					continue;
				dist[next.v][(next.w + cur.w) % K] = next.w + cur.w;
				pq.add(new Edge(next.v, next.w + cur.w));
			}
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
