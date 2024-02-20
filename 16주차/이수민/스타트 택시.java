import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int cnt;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		public int compareTo(Point o) {
			if (this.cnt == o.cnt) {
				if (this.r == o.r) {
					return Integer.compare(this.c, o.c);
				}
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.cnt, o.cnt);
		}

	}

	static int N, M, F;
	static int[][] map;
	static int tr, tc, tn;
	static Point[] goal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()) * -1;
			}
		}

		st = new StringTokenizer(br.readLine());
		tr = Integer.parseInt(st.nextToken()) - 1;
		tc = Integer.parseInt(st.nextToken()) - 1;

		goal = new Point[M + 1];

		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = m;
			goal[m] = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
		}

		for (int m = 1; m <= M; m++) {
			if (!takeATaxi() || !arrival()) {
				F = -1;
				break;
			}
		}

		System.out.println(F);

	}

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };
	static PriorityQueue<Point> pq;
	static boolean[][] v;

	private static boolean takeATaxi() {
		init();
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (map[cur.r][cur.c] > 0) {
				if (F - cur.cnt < 0)
					return false;

				F -= cur.cnt;
				tr = cur.r;
				tc = cur.c;
				tn = map[cur.r][cur.c];
				map[cur.r][cur.c] = 0;
				return true;
			}
			bfs(cur.r, cur.c, cur.cnt);

		}
		return false;
	}

	private static boolean arrival() {
		init();
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.r == goal[tn].r && cur.c == goal[tn].c) {

				if (F - cur.cnt < 0)
					return false;

				F += cur.cnt;
				tr = cur.r;
				tc = cur.c;
				return true;
			}

			bfs(cur.r, cur.c, cur.cnt);
		}
		return false;
	}

	private static void bfs(int cr, int cc, int cnt) {
		for (int d = 0; d < 4; d++) {
			int nr = cr + dr[d];
			int nc = cc + dc[d];

			if (!check(nr, nc) || v[nr][nc] || map[nr][nc] == -1)
				continue;

			pq.offer(new Point(nr, nc, cnt + 1));
			v[nr][nc] = true;
		}
	}

	private static void init() {
		pq = new PriorityQueue<>();
		v = new boolean[N][N];
		pq.offer(new Point(tr, tc, 0));
		v[tr][tc] = true;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
