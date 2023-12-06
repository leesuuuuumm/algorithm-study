package boj;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main_18405_경쟁적전염 {

	static class Location {
		int r;
		int c;

		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, K, S, X, Y;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		input();
		System.out.println(bfs());
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	private static int bfs() {
		Queue<Location>[] queues = new Queue[K + 1];
		boolean[][] visit = new boolean[N][N];
		for (int i = 1; i <= K; i++) {
			queues[i] = new ArrayDeque<>();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				queues[map[i][j]].add(new Location(i, j));
				visit[i][j] = true;
			}
		}
		for (int i = 0; i <= S; i++) {
			for (int j = 1; j <= K; j++) {
				Queue<Location> q = queues[j];
				int size = q.size();
				for (int k = 0; k < size; k++) {
					Location cur = q.poll();
					map[cur.r][cur.c] = j;

					for (int dir = 0; dir < 4; dir++) {
						int nr = cur.r + dr[dir];
						int nc = cur.c + dc[dir];

						if (isArrayOutOfBound(nr, nc))
							continue;
						if (visit[nr][nc])
							continue;

						q.add(new Location(nr, nc));
						visit[nr][nc] = true;
					}
				}
			}
		}
		return map[X - 1][Y - 1];
	}

	private static boolean isArrayOutOfBound(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= N;
	}

	private static void input() throws Exception {
		N = read();
		K = read();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = read();
			}
		}
		S = read();
		X = read();
		Y = read();
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
