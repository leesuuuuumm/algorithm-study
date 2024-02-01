package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_1368_물대기 {

	static class Edge {
		int v1;
		int v2;
		int w;

		public Edge(int v1, int v2, int w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}

	static int N;
	static boolean[] visit;
	static int[] groups;
	static PriorityQueue<Edge> pq;

	public static void main(String[] args) throws Exception {
		input();
		int result = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (union(cur.v1, cur.v2)) {
				result += cur.w;
			}
		}
		System.out.println(result);
	}

	private static int find(int a) {
		if (groups[a] == a)
			return a;
		return groups[a] = find(groups[a]);
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;

		groups[pb] = pa;
		return true;
	}

	private static void input() throws Exception {
		N = read();
		visit = new boolean[N + 1];
		groups = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			groups[i] = i;
		}
		pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));
		for (int i = 1; i <= N; i++) {
			pq.add(new Edge(0, i, read()));
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int cost = read();
				if (i >= j)
					continue;
				pq.add(new Edge(i, j, cost));
			}
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
