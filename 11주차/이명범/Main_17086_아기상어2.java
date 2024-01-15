package boj;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main_17086_아기상어2 {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, M;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		N = read();
		M = read();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = read();
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				max = Math.max(max, bfs(i, j));
			}
		}
		System.out.println(max);
	}

	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

	private static int bfs(int r, int c) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][M];
		q.add(new Point(r, c));
		visit[r][c] = true;

		int result = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point cur = q.poll();

				if (map[cur.r][cur.c] == 1)
					return result;

				for (int dir = 0; dir < 8; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];

					if (isArrayOutOfBounds(nr, nc))
						continue;
					if (visit[nr][nc])
						continue;

					q.add(new Point(nr, nc));
					visit[nr][nc] = true;
				}
			}
			result++;
		}
		return -1;
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
