package boj;

import java.util.ArrayDeque;
import java.util.Queue;

public class Main_16948_데스나이트 {
	static int[] dr = {-2, -2, 0, 0, 2, 2};
	static int[] dc = {-1, 1, -2, 2, -1, 1};

	static int N;
	static int r1, c1, r2, c2;

	public static void main(String[] args) throws Exception {
		N = read();
		r1 = read();
		c1 = read();
		r2 = read();
		c2 = read();
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N][N];
		q.add(new int[] {r1, c1});
		visit[r1][c1] = true;

		int count = 0;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();

				if (cur[0] == r2 && cur[1] == c2)
					return count;

				for (int dir = 0; dir < 6; dir++) {
					int nr = cur[0] + dr[dir];
					int nc = cur[1] + dc[dir];

					if (nr < 0 || nr >= N || nc < 0 || nc >= N)
						continue;
					if (visit[nr][nc])
						continue;

					q.add(new int[] {nr, nc});
					visit[nr][nc] = true;
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
