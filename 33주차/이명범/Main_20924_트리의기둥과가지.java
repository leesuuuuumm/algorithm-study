package boj;

import java.util.ArrayList;
import java.util.List;

public class Main_20924_트리의기둥과가지 {

	static class Edge {
		int v;
		int w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static int N, R, G, glen, xlen;
	static List<Edge>[] elist;
	static boolean[] visit;

	public static void main(String[] args) throws Exception {
		N = read();
		R = read();
		elist = new List[N + 1];
		visit = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			elist[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			int a = read();
			int b = read();
			int d = read();
			elist[a].add(new Edge(b, d));
			elist[b].add(new Edge(a, d));
		}
		if (N == 1) {
			System.out.println("0 0");
			return;
		}
		getGigaNode(R, 0);
		getMaxLength(G, 0);
		System.out.println(glen + " " + xlen);
	}

	private static void getGigaNode(int cur, int len) {
		visit[cur] = true;
		if (elist[cur].size() > 2 || (elist[cur].size() == 1 && cur != R) || (elist[cur].size() == 2 && cur == R)) {
			G = cur;
			glen = len;
			return;
		}

		for (Edge next : elist[cur]) {
			if (visit[next.v])
				continue;
			getGigaNode(next.v, len + next.w);
		}
	}

	private static void getMaxLength(int cur, int len) {
		visit[cur] = true;

		if (elist[cur].size() == 1) {
			xlen = Math.max(xlen, len);
			return;
		}

		for (Edge next : elist[cur]) {
			if (visit[next.v])
				continue;
			getMaxLength(next.v, len + next.w);
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
