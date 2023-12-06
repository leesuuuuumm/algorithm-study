package boj;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main_16398_행성연결 {

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
	static int[][] map;
	static int[] groups;
	static List<Edge> edges = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		input();
		solve();
	}

	private static void solve() {
		long sum = 0;
		for (Edge edge : edges) {
			if (union(edge.v1, edge.v2))
				sum += edge.w;
		}
		System.out.println(sum);
	}

	private static void input() throws Exception {
		N = read();
		map = new int[N][N];
		groups = new int[N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = read();
			}
		}
		for (int i = 0; i < N; i++) {
			groups[i] = i;
		}
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				edges.add(new Edge(i, j, map[i][j]));
			}
		}
		edges.sort(Comparator.comparing(o -> o.w));
	}

	private static int find(int a) {
		if (a == groups[a])
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

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
