package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_25516_사과수확하기 {

	static int N, K;
	static List<Integer>[] edges;
	static int[] apples;

	public static void main(String[] args) throws Exception {
		input();
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N];
		q.add(0);
		visit[0] = true;

		int result = 0;
		int distance = 0;
		while (!q.isEmpty()) {
			if (distance > K)
				break;

			int size = q.size();

			for (int i = 0; i < size; i++) {
				Integer cur = q.poll();
				result += apples[cur];

				for (Integer next : edges[cur]) {
					if (visit[next])
						continue;

					q.add(next);
					visit[next] = true;
				}
			}
			distance++;
		}
		return result;
	}

	private static void input() throws Exception {
		N = read();
		K = read();
		edges = new List[N];
		apples = new int[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			int s = read();
			int e = read();
			edges[s].add(e);
		}
		for (int i = 0; i < N; i++) {
			apples[i] = read();
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
