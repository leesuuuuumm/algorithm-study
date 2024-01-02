package boj;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main_11084_나이트의염탐 {

	static class Location {
		int r;
		int c;
		int distance;
		int count;

		public Location(int r, int c, int distance, int count) {
			this.r = r;
			this.c = c;
			this.distance = distance;
			this.count = count;
		}

		public void addCount() {
			count++;
		}
	}

	static final int DIVISION = 1_000_000_009;

	static int N, M;
	static int[] dr = {-2, -1, -1, -2, 2, 1, 1, 2};
	static int[] dc = {-1, -2, 2, 1, -1, -2, 2, 1};

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		Location result = bfs();

		System.out.println(result == null ? "None" : result.distance + " " + result.count);
	}

	public static Location bfs() {
		Queue<Location> q = new ArrayDeque<>();
		Location[][] locations = new Location[N + 1][M + 1];
		locations[1][1] = new Location(1, 1, 0, 1);
		q.add(locations[1][1]);

		while (!q.isEmpty()) {
			Location cur = q.poll();

			if (cur.r == N && cur.c == M)
				return cur;

			for (int dir = 0; dir < 8; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];

				if (isArrayOutOfBounds(nr, nc))
					continue;
				if (locations[nr][nc] != null) {
					if (locations[nr][nc].distance == cur.distance + 1) {
						locations[nr][nc].count = (locations[nr][nc].count + cur.count) % DIVISION;
						continue;
					} else {
						continue;
					}
				}

				locations[nr][nc] = new Location(nr, nc, cur.distance + 1, cur.count);
				q.add(locations[nr][nc]);
			}
		}
		return null;
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r < 1 || r > N || c < 1 || c > M;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
