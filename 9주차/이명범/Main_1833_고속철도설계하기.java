package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1833_고속철도설계하기 {

	static class Edge{
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
	static int[] parents;
	static List<Edge> edges;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N];
		edges = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		int AC = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (i <= j)
					continue;
				int w = Integer.parseInt(st.nextToken());
				if (w < 0) {
					AC -= w;
					union(i, j);
					continue;
				}
				edges.add(new Edge(i, j, w));
			}
		}
		Collections.sort(edges, Comparator.comparing(o -> o.w));
		int C = AC;
		int M = 0;
		List<Edge> useEdges = new ArrayList<>();
		for (Edge edge : edges) {
			if (union(edge.v1, edge.v2)) {
				C += edge.w;
				M++;
				useEdges.add(edge);
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(C).append(" ").append(M).append("\n");
		for (int i = 0; i < M; i++) {
			Edge edge = useEdges.get(i);
			sb.append(edge.v1 + 1).append(" ").append(edge.v2 + 1).append("\n");
		}
		System.out.print(sb);
	}

	private static int find(int a) {
		if (parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb) {
			return false;
		}

		parents[pb] = pa;
		return true;
	}
}
