package boj;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main_14466_소가길을건너간이유6 {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static int N, K, R, result;
	static List<Point>[][] edges;
	static Point[] cows;

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(result);
	}

	private static void solve() {
		for (int i = 0; i < K; i++) {
			for (int j = i + 1; j < K; j++) {
				bfs(cows[i], cows[j]);
			}
		}
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	private static void bfs(Point s, Point e) {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][] visit = new boolean[N + 1][N + 1];
		q.add(new Point(s.r, s.c));
		visit[s.r][s.c] = true;

		while (!q.isEmpty()) {
			Point cur = q.poll();

			if (cur.r == e.r && cur.c == e.c)
				return;

			for (int dir = 0; dir < 4; dir++) {
				int nr = cur.r + dr[dir];
				int nc = cur.c + dc[dir];

				if (nr < 1 || nr > N || nc < 1 || nc > N)
					continue;
				if (visit[nr][nc])
					continue;
				if (edges[cur.r][cur.c].stream().anyMatch(p -> p.r == nr && p.c == nc))
					continue;

				q.add(new Point(nr, nc));
				visit[nr][nc] = true;
			}
		}
		result++;
	}

	private static void input() throws Exception {
		N = read();
		K = read();
		R = read();
		edges = new List[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				edges[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < R; i++) {
			int r1 = read();
			int c1 = read();
			int r2 = read();
			int c2 = read();

			edges[r1][c1].add(new Point(r2, c2));
			edges[r2][c2].add(new Point(r1, c1));
		}
		cows = new Point[K];
		for (int i = 0; i < K; i++) {
			int r = read();
			int c = read();
			cows[i] = new Point(r, c);
		}
	}

	private static int read() throws Exception {
	    int c, n = System.in.read() & 15;
	    while ((c = System.in.read()) > 32) {
	        n = (n << 3) + (n << 1) + (c & 15);
	    }
	    return n;
	}
}
