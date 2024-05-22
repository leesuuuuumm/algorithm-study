package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main_17396_백도어 {

	static class Edge {
		int v;
		long w;

		public Edge(int v, long w) {
			this.v = v;
			this.w = w;
		}
	}

	static int N, M;
	static int[] sight;
	static List<Edge>[] edges;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		sight = new int[N];
		edges = new List[N];
		for (int i = 0; i < N; i++) {
			sight[i] = read();
		}
		sight[N - 1] = 0;
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int a = read();
			int b = read();
			int t = read();

			if (sight[a] == 1 || sight[b] == 1)
				continue;

			edges[a].add(new Edge(b, t));
			edges[b].add(new Edge(a, t));
		}
		System.out.println(dijkstra());
	}

	private static long dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
		long[] dist = new long[N];
		boolean[] visited = new boolean[N];
		Arrays.fill(dist, Long.MAX_VALUE);
		pq.add(new Edge(0, 0));
		dist[0] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (visited[cur.v]) continue;
			visited[cur.v] = true;

			if (cur.v == N - 1)
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
