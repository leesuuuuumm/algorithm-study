package boj;

import java.util.ArrayList;
import java.util.List;

public class Main_10159_저울 {

	static int N, M;
	static List<Integer>[] edges;
	static List<Integer>[] recursiveEdges;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		edges = new List[N + 1];
		recursiveEdges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			recursiveEdges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int s = read();
			int e = read();
			edges[s].add(e);
			recursiveEdges[e].add(s);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			int result = dfs(edges, i, new boolean[N + 1]);
			result += dfs(recursiveEdges, i, new boolean[N + 1]);
			sb.append(N + 1 - result).append("\n");
		}
		System.out.println(sb);
	}

	private static int dfs(List<Integer>[] edges, int cur, boolean[] visit) {
		int result = 1;
		for (Integer next : edges[cur]) {
			if (visit[next])
				continue;
			visit[next] = true;
			result += dfs(edges, next, visit);
		}
		return result;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
