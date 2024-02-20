package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main_5972_택배배송 {
	static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	static int N, M;
	static List<Edge>[] edges;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int a = read();
			int b = read();
			int w = read();
			edges[a].add(new Edge(b, w));
			edges[b].add(new Edge(a, w));
		}
		System.out.println(dijkstra());
	}
	private static int dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.add(new Edge(1, 0));
		dist[1] = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.v == N)
				return cur.w;

			for (Edge next : edges[cur.v]) {
				if (dist[next.v] <= dist[cur.v] + next.w)
					continue;
				dist[next.v] = dist[cur.v] + next.w;
				pq.add(new Edge(next.v, dist[next.v]));
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
