package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_5567_결혼식 {

	static int N, M;
	static List<Integer>[] edges;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int a = read();
			int b = read();
			edges[a].add(b);
			edges[b].add(a);
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N + 1];
		q.add(1);
		visit[1] = true;

		int depth = 0;
		int result = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Integer cur = q.poll();
				result++;

				for (Integer next : edges[cur]) {
					if (visit[next])
						continue;
					q.add(next);
					visit[next] = true;
				}
			}
			if (depth++ == 2)
				break;
		}
		return result - 1;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
