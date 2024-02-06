package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_2252_줄세우기 {

	static int N, M;
	static int[] degree;
	static List<Integer>[] edges;
	static List<Integer> result = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		input();
		bfs();
		output();
	}

	private static void output() {
		StringBuilder sb = new StringBuilder();
		for (Integer i : result) {
			sb.append(i).append(" ");
		}
		System.out.print(sb);
	}

	private static void input() throws Exception {
		N = read();
		M = read();
		degree = new int[N + 1];
		edges = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			int a = read();
			int b = read();

			edges[a].add(b);
			degree[b]++;
		}
	}

	private static void bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if (degree[i] == 0)
				q.add(i);
		}

		while (!q.isEmpty()) {
			Integer cur = q.poll();

			result.add(cur);

			for (Integer next : edges[cur]) {
				degree[next]--;

				if (degree[next] != 0)
					continue;

				q.add(next);
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
