package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_2056_작업 {

	static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	static int N, result;
	static List<Edge>[] edges;
	static int[] degree;
	static int[] cost;


	public static void main(String[] args) throws Exception {
		input();
		topologySort();
		output();
	}

	private static void output() {
		System.out.println(result);
	}

	private static void topologySort() {
		Queue<Edge> q = new ArrayDeque<>();
		int[] max = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0)
				q.add(new Edge(i, 0));
		}
		while (!q.isEmpty()) {
			Edge cur = q.poll();

			for (Edge next : edges[cur.v]) {
				degree[next.v]--;

				max[next.v] = Math.max(max[next.v], max[cur.v] + next.w);

				if (degree[next.v] > 0)
					continue;

				q.add(new Edge(next.v, max[next.v]));
			}
		}
		for (int i = 1; i <= N; i++) {
			result = Math.max(result, max[i] + cost[i]);
		}
	}

	private static void input() throws Exception {
		N = read();
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		degree = new int[N + 1];
		cost = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			cost[i] = read();
			int pre = read();
			for (int j = 0; j < pre; j++) {
				int num = read();
				edges[num].add(new Edge(i, cost[num]));
				degree[i]++;
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
