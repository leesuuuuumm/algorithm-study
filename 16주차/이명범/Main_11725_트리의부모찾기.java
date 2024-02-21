package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_11725_트리의부모찾기 {

	static int N;
	static List<Integer>[] edges;
	static int[] parents;

	public static void main(String[] args) throws Exception {
		N = read();
		edges = new List[N + 1];
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			int a = read();
			int b = read();

			edges[a].add(b);
			edges[b].add(a);
		}
		bfs();
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N + 1];
		q.add(1);
		visit[1] = true;

		while (!q.isEmpty()) {
			Integer cur = q.poll();

			for (Integer next : edges[cur]) {
				if (visit[next])
					continue;

				q.add(next);
				visit[next] = true;
				parents[next] = cur;
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
