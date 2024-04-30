package boj;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main_11060_점프점프 {

	static int N;
	static int[] A;

	public static void main(String[] args) throws Exception {
		N = read();
		A = new int[N];
		for (int i = 0; i < N; i++) {
			A[i] = read();
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visit = new boolean[N];
		q.add(0);
		visit[0] = true;

		int result = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Integer cur = q.poll();

				if (cur == N - 1)
					return result;

				for (int j = 1; j <= A[cur]; j++) {
					Integer next = cur + j;

					if (next >= N)
						break;
					if (visit[next])
						continue;

					q.add(next);
					visit[next] = true;
				}
			}
			result++;
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
