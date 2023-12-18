package boj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Main_13911_집구하기 {
	static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	static int V, E, M, x, S, y;
	static List<Edge>[] edges;
	static Set<Integer> mcdonald;
	static Set<Integer> starbucks;

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void input() throws Exception {
		V = read();
		E = read();
		edges = new List[V + 1];
		for (int i = 1; i <= V; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			int from = read();
			int to = read();
			int w = read();
			edges[from].add(new Edge(to, w));
			edges[to].add(new Edge(from, w));
		}
		M = read();
		x = read();
		mcdonald = new HashSet<>();
		for (int i = 0; i < M; i++) {
			mcdonald.add(read());
		}
		S = read();
		y = read();
		starbucks = new HashSet<>();
		for (int i = 0; i < S; i++) {
			starbucks.add(read());
		}
	}

	private static int read() throws Exception {
		int c, n = System.in.read() & 15;
		while ((c = System.in.read()) > 32) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}

	private static void solve() {
		int[] mcdonaldArea = findArea(mcdonald, x);
		int[] starbucksArea = findArea(starbucks, y);
		int result = Integer.MAX_VALUE;
		for (int i = 1; i <= V; i++) {
			if (mcdonaldArea[i] == 0 || starbucksArea[i] == 0)
				continue;
			result = Math.min(result, mcdonaldArea[i] + starbucksArea[i]);
		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static int[] findArea(Set<Integer> store, int limit) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for (Integer i : store) {
			pq.add(new Edge(i, 0));
			dist[i] = 0;
		}
		int[] result = new int[V + 1];
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (cur.w > limit)
				break;

			if (result[cur.v] != 0)
				continue;

			result[cur.v] = cur.w;

			for (Edge next : edges[cur.v]) {
				if (dist[next.v] <= dist[cur.v] + next.w)
					continue;
				dist[next.v] = dist[cur.v] + next.w;
				pq.add(new Edge(next.v, dist[next.v]));
			}
		}
		return result;
	}
}
