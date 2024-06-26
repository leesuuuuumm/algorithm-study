package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_2151_거울설치 {

	static class Point {
		int r;
		int c;
		int dir;
		int count;

		public Point(int r, int c, int dir, int count) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.count = count;
		}
	}

	static int N;
	static char[][] map;
	static Point s, e;

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};

	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);

				if (map[i][j] == '#') {
					if (s == null)
						s = new Point(i, j, 0, 0);
					else
						e = new Point(i, j, 0, 0);
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			dijkstra(s, e, i);
			dijkstra(e, s, i);
		}
		System.out.println(result);
	}

	private static void dijkstra(Point s, Point e, int dir) {
		PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparing(o -> o.count));
		int[][][] dist = new int[N][N][4];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					dist[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}
		pq.add(new Point(s.r, s.c, dir, 0));
		dist[s.r][s.c][dir] = 0;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.r == e.r && cur.c == e.c) {
				result = Math.min(result, cur.count);
				return;
			}

			if (map[cur.r][cur.c] == '!') {
				if (cur.dir == 0 || cur.dir == 1) {
					for (int i = 0; i < 2; i++) {
						int nr = cur.r + dr[i + 2];
						int nc = cur.c + dc[i + 2];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;
						if (map[nr][nc] == '*')
							continue;
						if (dist[nr][nc][i + 2] <= cur.count + 1)
							continue;

						pq.add(new Point(nr, nc, i + 2, cur.count + 1));
						dist[nr][nc][i + 2] = cur.count + 1;
					}
				} else {
					for (int i = 0; i < 2; i++) {
						int nr = cur.r + dr[i];
						int nc = cur.c + dc[i];

						if (nr < 0 || nr >= N || nc < 0 || nc >= N)
							continue;
						if (map[nr][nc] == '*')
							continue;
						if (dist[nr][nc][i] <= cur.count + 1)
							continue;

						pq.add(new Point(nr, nc, i, cur.count + 1));
						dist[nr][nc][i] = cur.count + 1;
					}
				}
			}

			int nr = cur.r + dr[cur.dir];
			int nc = cur.c + dc[cur.dir];

			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (map[nr][nc] == '*')
				continue;
			if (dist[nr][nc][cur.dir] <= cur.count)
				continue;

			pq.add(new Point(nr, nc, cur.dir, cur.count));
			dist[nr][nc][cur.dir] = cur.count;
		}
	}
}
