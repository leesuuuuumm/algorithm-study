package boj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_13549_숨바꼭질3 {
	static class Location {
		int position;
		int time;

		public Location(int position, int time) {
			this.position = position;
			this.time = time;
		}
	}

	static final int MAX_INDEX = 200_000;
	static int N, K;

	public static void main(String[] args) throws Exception {
		N = read();
		K = read();

		System.out.println(bfs());
	}

	private static int bfs() {
		PriorityQueue<Location> q = new PriorityQueue<>(Comparator.comparing(o -> o.time));
		int[] dist = new int[MAX_INDEX + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		q.add(new Location(N, 0));
		dist[N] = 0;

		while (!q.isEmpty()) {
			Location cur = q.poll();

			if (cur.position == K)
				return cur.time;

			for (int dir = -1; dir <= 1; dir += 2) {
				int next = cur.position + dir;

				if (next < 0 || next > MAX_INDEX)
					continue;
				if (dist[next] <= dist[cur.position] + 1)
					continue;

				q.add(new Location(next, dist[cur.position] + 1));
				dist[next] = dist[cur.position] + 1;
			}

			int next = 2 * cur.position;

			if (next < 0 || next > 2 * K)
				continue;
			if (dist[next] <= dist[cur.position])
				continue;

			q.add(new Location(next, dist[cur.position]));
			dist[next] = dist[cur.position];
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
