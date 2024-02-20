package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_14496_그대그머가되어 {

	static int a, b, N, M;
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		a = read();
		b = read();
		N = read();
		M = read();
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int s = read();
			int e = read();
			edges[s].add(e);
			edges[e].add(s);
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N + 1];
		q.add(a);
		visit[a] = true;

		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Integer cur = q.poll();

				if (cur == b)
					return count;

				for (Integer next : edges[cur]) {
					if (visit[next])
						continue;

					q.add(next);
					visit[next] = true;
				}
			}
			count++;
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
