package boj;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;

public class Main_13913_숨바꼭질4 {

	static int N, K;
	static int[] visit;

	public static void main(String[] args) throws Exception {
		N = read();
		K = read();
		visit = new int[100_001];
		Arrays.fill(visit, Integer.MIN_VALUE);

		StringBuilder sb = new StringBuilder();
		sb.append(bfs()).append("\n");
		Deque<Integer> stack = new ArrayDeque<>();
		while (K != -1) {
			stack.push(K);
			K = visit[K];
		}
		for (Integer i : stack) {
			sb.append(i).append(" ");
		}
		System.out.print(sb);
	}

	static int[] dx = {-1, 1};
	private static int bfs() {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(N);
		visit[N] = -1;

		int count = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Integer cur = q.poll();

				if (cur == 50000)
					System.out.println();

				if (cur == K)
					return count;

				for (int dir = 0; dir < 3; dir++) {
					int nx;

					if (dir == 2)
						nx = 2 * cur;
					else
						nx = cur + dx[dir];

					if (nx < 0 || nx > 100_000)
						continue;
					if (visit[nx] != Integer.MIN_VALUE)
						continue;

					q.add(nx);
					visit[nx] = cur;
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
