package boj;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_16137_견우와직녀 {

	static class Location {
		int r;
		int c;
		int time;
		boolean isGenerated;
		boolean isPreviousPassed;

		public Location(int r, int c, int time, boolean isGenerated, boolean isPreviousPassed) {
			this.r = r;
			this.c = c;
			this.time = time;
			this.isGenerated = isGenerated;
			this.isPreviousPassed = isPreviousPassed;
		}
	}
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = read();
			}
		}
		System.out.println(dijkstra());
	}

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	private static int dijkstra() {
		PriorityQueue<Location> pq = new PriorityQueue<>(Comparator.comparing(o -> o.time));
		int[][][] dist = new int[N][N][2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(dist[i][j], Integer.MAX_VALUE);
			}
		}
		pq.add(new Location(0, 0, 0, false, false));
		dist[0][0][0] = 0;

		while (!pq.isEmpty()) {
			Location cur = pq.poll();

			if (cur.r == N - 1 && cur.c == N - 1)
				return cur.time;

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (isArrayIndexOutOfBounds(nr, nc))
					continue;

				if (map[nr][nc] == 0) {
					if (cur.isPreviousPassed)
						continue;

					if (cur.isGenerated)
						continue;

					if (!isAllowedPlace(nr, nc))
						continue;

					int nTime = (cur.time + 1) % M == 0 ? cur.time + 1 : cur.time + 1 + M - (cur.time + 1) % M;

					if (dist[nr][nc][1] < nTime)
						continue;

					dist[nr][nc][1] = nTime;
					pq.add(new Location(nr, nc, nTime, true, true));
				}

				if (map[nr][nc] == 1) {
					int nTime = cur.time + 1;

					if (dist[nr][nc][cur.isGenerated ? 1 : 0] < nTime)
						continue;

					dist[nr][nc][cur.isGenerated ? 1 : 0] = nTime;
					pq.add(new Location(nr, nc, nTime, cur.isGenerated, false));
				}

				if (map[nr][nc] >= 2) {
					if (cur.isPreviousPassed)
						continue;

					int nTime = (cur.time + 1) % map[nr][nc] == 0 ? cur.time + 1 : cur.time + 1 + map[nr][nc] - (cur.time + 1) % map[nr][nc];

					if (dist[nr][nc][1] < nTime)
						continue;

					dist[nr][nc][1] = nTime;
					pq.add(new Location(nr, nc, nTime, cur.isGenerated, true));
				}
			}
		}
		return -1;
	}

	private static boolean isAllowedPlace(int r, int c) {
		for (int dir = 0; dir < 4; dir++) {
			if (isArrayIndexOutOfBounds(r + dr[dir], c + dc[dir]))
				continue;
			if (map[r + dr[dir]][c + dc[dir]] != 0)
				continue;

			if (!isArrayIndexOutOfBounds(r + dr[(dir + 1) % 4], c + dc[(dir + 1) % 4]) && map[r + dr[(dir + 1) % 4]][c + dc[(dir + 1) % 4]] == 0) {
				return false;
			}
			if (!isArrayIndexOutOfBounds(r + dr[(dir + 3) % 4], c + dc[(dir + 3) % 4]) && map[r + dr[(dir + 3) % 4]][c + dc[(dir + 3) % 4]] == 0) {
				return false;
			}
		}
		return true;
	}

	private static boolean isArrayIndexOutOfBounds(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}

