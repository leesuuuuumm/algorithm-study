package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main_11779_최소비용구하기2 {
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
	static int[] path;
	static List<Integer> minPath = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		input();
		int s = read();
		int e = read();

		StringBuilder result = new StringBuilder();
		result.append(dijkstra(s, e)).append("\n");
		int cur = e;
		while (true) {
			if (cur == 0)
				break;
			minPath.add(cur);
			cur = path[cur];
		}
		result.append(minPath.size()).append("\n");
		for (int i = minPath.size() - 1; i >= 0; i--) {
			result.append(minPath.get(i)).append(" ");
		}
		System.out.println(result);
	}

	private static int dijkstra(int s, int e) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.add(new Edge(s, 0));
		dist[s] = 0;

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.v == e)
				return cur.w;

			for (Edge next : edges[cur.v]) {
				if (dist[next.v] <= dist[cur.v] + next.w)
					continue;
				dist[next.v] = dist[cur.v] + next.w;
				pq.add(new Edge(next.v, dist[next.v]));
				path[next.v] = cur.v;
			}
		}
		return -1;
	}

	private static void input() throws Exception {
		N = read();
		M = read();
		edges = new List[N + 1];
		path = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int from = read();
			int to = read();
			int w = read();
			edges[from].add(new Edge(to, w));
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
