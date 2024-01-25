package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_1240_노드사이의거리 {

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
		for (int i = 0; i < N - 1; i++) {
			int s = read();
			int e = read();
			int w = read();
			edges[s].add(new Edge(e, w));
			edges[e].add(new Edge(s, w));
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int s = read();
			int e = read();
			sb.append(bfs(s, e)).append("\n");
		}
		System.out.print(sb);
	}

	private static int bfs(int s, int e) {
		Queue<Edge> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N + 1];
		q.add(new Edge(s, 0));
		visit[s] = true;

		while (!q.isEmpty()) {
			Edge cur = q.poll();

			if (cur.v == e)
				return cur.w;

			for (Edge next : edges[cur.v]) {
				if (visit[next.v])
					continue;
				q.add(new Edge(next.v, cur.w + next.w));
				visit[next.v] = true;
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
