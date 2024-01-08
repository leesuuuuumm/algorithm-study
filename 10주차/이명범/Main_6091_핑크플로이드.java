package boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main_6091_핑크플로이드 {

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
	static int[] groups;

	public static void main(String[] args) throws Exception {
		N = read();
		groups = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			groups[i] = i;
		}
		PriorityQueue<Edge> edges = new PriorityQueue<>(
			Comparator.comparingInt((Edge o) -> o.w)
				.thenComparingInt(o -> o.v1)
				.thenComparingInt(o -> o.v2)
		);
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				edges.add(new Edge(i, j, read()));
			}
		}
		List<Integer>[] graph = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		while (!edges.isEmpty()) {
			Edge e = edges.poll();

			if (union(e.v1, e.v2)) {
				graph[e.v1].add(e.v2);
				graph[e.v2].add(e.v1);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(graph[i].size()).append(" ");
			Collections.sort(graph[i]);
			for (Integer num : graph[i]) {
				sb.append(num).append(" ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		System.out.print(sb);
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

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
