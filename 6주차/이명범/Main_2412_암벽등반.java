package boj;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main_2412_암벽등반 {
	static class Location {
		int x;
		int y;

		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static int n, T;
	static Set<Integer>[] holes;
	static Set<Integer>[] visit;

	public static void main(String[] args) throws Exception {
		n = read();
		T = read();
		holes = new Set[T + 1];
		visit = new Set[T + 1];
		for (int i = 0; i <= T; i++) {
			holes[i] = new HashSet<>();
			visit[i] = new HashSet<>();
		}
		for (int i = 0; i < n; i++) {
			int x = read();
			int y = read();
			holes[y].add(x);
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Location> q = new ArrayDeque<>();
		q.add(new Location(0, 0));
		visit[0].add(0);

		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Location cur = q.poll();

				if (cur.y == T)
					return count;

				for (int y = -2; y <= 2; y++) {
					int ny = cur.y + y;
					if (ny < 0 || ny > T)
						continue;

					for (int x = -2; x <= 2; x++) {
						int nx = cur.x + x;

						if (!holes[ny].contains(nx))
							continue;
						if (visit[ny].contains(nx))
							continue;

						q.add(new Location(nx, ny));
						visit[ny].add(nx);
					}
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
