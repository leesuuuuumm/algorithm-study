import java.io.*;
import java.util.*;
public class Main {
	static class Point {
		int r;
		int c;
		int num;

		public Point(int r, int c, int num) {
			this.r = r;
			this.c = c;
			this.num = num;
		}

	}

	static int L, N, Q;
	static Point[] p;
	static int[] life;
	static int[] dlife;
	static int[][] map;
	static int[][] gmap;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		p = new Point[N + 1];
		map = new int[L][L];
		gmap = new int[L][L];
		life = new int[N + 1];
		dlife = new int[N + 1];
		ans = 0;
		for (int r = 0; r < L; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < L; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= N; i++) {
		    st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			int nr = r;
			for (int rr = 0; rr < h; rr++) {
				int nc = c;
				for (int cc = 0; cc < w; cc++) {
					gmap[nr][nc] = i;
					nc += 1;

				}
				nr += 1;

			}
			life[i] = k;
			dlife[i] = k;

		}

		for (int j = 0; j < Q; j++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			moving(i, d);
		}

		for (int i = 1; i <= N; i++) {
			if (life[i] <= 0)
				continue;
			ans += dlife[i] - life[i];

		}
		System.out.println(ans);

	}

	static Queue<Point> que;
	static boolean[][] v;
	static boolean f;

	private static void moving(int i, int dir) {
		que = new LinkedList<>();
		v = new boolean[L][L];
		tmp = new LinkedList<>();

		e: for (int r = 0; r < L; r++) {
			for (int c = 0; c < L; c++) {
				if (gmap[r][c] == i && !v[r][c]) {
					que.offer(new Point(r, c, i));
					v[r][c] = true;
					tmp.offer(new Point(r, c, i));
					bfs(i, dir);
					break e;

				}

			}
		}

	}

	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };
	static Queue<Point> tmp;

	private static void bfs(int now, int dir) {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (dir == d) {
					if (!check(nr, nc) || map[nr][nc] == 2) {
						return;
					}

					if (v[nr][nc])
						continue;

				} else {
					if (!check(nr, nc) || v[nr][nc] || map[nr][nc] == 2 || gmap[nr][nc] != cur.num)
						continue;
				}

				if (gmap[nr][nc] != 0) {

					que.offer(new Point(nr, nc, gmap[nr][nc]));
					v[nr][nc] = true;
					tmp.offer(new Point(nr, nc, gmap[nr][nc]));
				}

			}
		}

		if (tmp.size() == 0) {
			return;
		}
		int size = tmp.size();
		for (int i = 0; i < size; i++) {
			Point cur = tmp.poll();

			gmap[cur.r][cur.c] = 0;
			int nr = cur.r + dr[dir];
			int nc = cur.c + dc[dir];
			tmp.offer(new Point(nr, nc, cur.num));
			if (now == cur.num)
				continue;
			if (map[nr][nc] == 1) {
				life[cur.num] -= 1;
			}
		}

		while (!tmp.isEmpty()) {
			Point cur = tmp.poll();

			if (life[cur.num] <= 0)
				continue;
			gmap[cur.r][cur.c] = cur.num;
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < L && nc >= 0 && nc < L;
	}

}
