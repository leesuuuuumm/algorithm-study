package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main_1414_불우이웃돕기 {

	static class Edge {
		int r;
		int c;
		int len;

		public Edge(int r, int c, int len) {
			this.r = r;
			this.c = c;
			this.len = len;
		}
	}
	static int N;
	static int[] parents;
	static List<Edge> edges = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				char ch = str.charAt(j);

				if (ch == '0')
					continue;

				int len = 0;

				if (ch >= 'a' && ch <= 'z')
					len = ch - 'a' + 1;
				else if (ch >= 'A' && ch <= 'Z')
					len = ch - 'A' + 27;

				edges.add(new Edge(i, j, len));
				result += len;
			}
		}
		edges.sort(Comparator.comparing(o -> o.len));

		int count = 0;
		for (Edge edge : edges) {
			if (edge.r == edge.c)
				continue;

			if (union(edge.r, edge.c)) {
				result -= edge.len;
				count++;
			}
		}
		System.out.print(count == N - 1 ? result : -1);
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);

		if (pa == pb)
			return false;

		parents[pb] = pa;
		return true;
	}
}
