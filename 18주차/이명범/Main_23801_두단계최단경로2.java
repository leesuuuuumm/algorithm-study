package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main_23801_두단계최단경로2 {

	static class Edge {
		int v;
		long w;

		public Edge(int v, long w) {
			this.v = v;
			this.w = w;
		}
	}
	private static long INF = 300_000L * 1_000_000L + 1L;

	static int N, M, s, e, P;
	static int[] stopovers;
	static List<Edge>[] edges;
	static long result = INF;



	public static void main(String[] args) throws Exception {
		input();
		solve();
		output();
	}

	private static void output() {
		System.out.println(result == INF ? -1 : result);
	}

	private static void solve() {
		long[] from = dijkstra(s);
		long[] to = dijkstra(e);

		for (int stopover : stopovers) {
			if (from[stopover] == INF)
				continue;
			if (to[stopover] == INF)
				continue;

			result = Math.min(result, from[stopover] + to[stopover]);
		}
	}

	private static long[] dijkstra(int s) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
		long[] dist = new long[N + 1];
		Arrays.fill(dist, INF);
		pq.add(new Edge(s, 0));
		dist[s] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.w > dist[cur.v]) continue;

			for (Edge next : edges[cur.v]) {
				if (dist[next.v] <= dist[cur.v] + next.w)
					continue;

				dist[next.v] = dist[cur.v] + next.w;
				pq.add(new Edge(next.v, dist[next.v]));
			}
		}
		return dist;
	}

	private static void input() throws Exception {
		N = read();
		M = read();
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int u = read();
			int v = read();
			int w = read();

			edges[u].add(new Edge(v, w));
			edges[v].add(new Edge(u, w));
		}
		s = read();
		e = read();
		P = read();
		stopovers = new int[P];
		for (int i = 0; i < P; i++) {
			stopovers[i] = read();
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
