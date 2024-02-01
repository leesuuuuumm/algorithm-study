package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main_20182_골목대장호석 {

	static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	static int N, M, A, B, C;
	static List<Edge>[] edges;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		A = read();
		B = read();
		C = read();
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int s = read();
			int e = read();
			int w = read();
			edges[s].add(new Edge(e, w));
			edges[e].add(new Edge(s, w));
		}
		int result = binarySearch(A, B, C);
		System.out.println(result == 21 ? -1 : result);
	}

	private static int binarySearch(int s, int e, int cost) {
		int l = 1;
		int r = 21;
		while (l < r) {
			int m = (l + r) / 2;

			if (dijkstra(s, e, cost, m))
				r = m;
			else
				l = m + 1;
		}
		return r;
	}

	private static boolean dijkstra(int s, int e, int cost, int limit) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.add(new Edge(s, 0));
		dist[s] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.w > cost)
				return false;
			if (cur.v == e)
				return true;

			for (Edge next : edges[cur.v]) {
				if (dist[next.v] <= dist[cur.v] + next.w)
					continue;
				if (next.w > limit)
					continue;
				dist[next.v] = dist[cur.v] + next.w;
				pq.add(new Edge(next.v, dist[next.v]));
			}
		}
		return false;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
