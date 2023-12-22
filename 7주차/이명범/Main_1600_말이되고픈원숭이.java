package boj;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main_1600_말이되고픈원숭이 {

	static class Location {
		int r;
		int c;
		int count;

		public Location(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}
	static int K, W, H;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		K = read();
		W = read();
		H = read();
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = read();
			}
		}
		System.out.println(bfs());
	}

	static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1, -1, 1, 0, 0};
	static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2, 0, 0, -1, 1};

	private static int bfs() {
		Queue<Location> q = new ArrayDeque<>();
		boolean[][][] visit = new boolean[H][W][K + 1];
		q.add(new Location(0, 0, 0));
		visit[0][0][0] = true;

		int result = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Location cur = q.poll();

				if (cur.r == H - 1 && cur.c == W - 1)
					return result;

				for (int dir = 0; dir < 8; dir++) {
					if (cur.count == K)
						break;

					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];

					if (isArrayOutOfBounds(nr, nc))
						continue;
					if (visit[nr][nc][cur.count + 1])
						continue;
					if (map[nr][nc] == 1)
						continue;

					q.add(new Location(nr, nc, cur.count + 1));
					visit[nr][nc][cur.count + 1] = true;
				}

				for (int dir = 8; dir < 12; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];

					if (isArrayOutOfBounds(nr, nc))
						continue;
					if (visit[nr][nc][cur.count])
						continue;
					if (map[nr][nc] == 1)
						continue;

					q.add(new Location(nr, nc, cur.count));
					visit[nr][nc][cur.count] = true;
				}
			}
			result++;
		}
		return -1;
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r < 0 || r >= H || c < 0 || c >= W;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
