package boj;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_21924_도시건설 {

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

	static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(o -> o.w));

	static int N, M;
	static int[] group;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		group = new int[N];
		for (int i = 0; i < N; i++) {
			group[i] = i;
		}
		long totalWeight = 0;
		for (int i = 0; i < M; i++) {
			int v1 = read() - 1;
			int v2 = read() - 1;
			int w = read();
			pq.add(new Edge(v1, v2, w));
			totalWeight += w;
		}
		int count = 0;
		long connectedWeight = 0;
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			if (union(cur.v1, cur.v2)) {
				count++;
				connectedWeight += cur.w;
			}
		}

		if (count == N - 1)
			System.out.println(totalWeight - connectedWeight);
		else
			System.out.println(-1);
	}

	private static int find(int a) {
		if (group[a] == a)
			return a;

		return group[a] = find(group[a]);
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;

		group[pb] = pa;
		return true;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
