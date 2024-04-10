import java.io.*;
import java.util.*;
public class Main { 

	static class Point implements Comparable<Point> {
		int r;
		int c;
		int dist;

		int pr;
		int pc;
		int num;

		public Point(int pr, int pc, int r, int c, int num) {
			this.pr = pr;
			this.pc = pc;
			this.r = r;
			this.c = c;
			this.num = num;
		}

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		public int compareTo(Point o) {
			if (this.dist == o.dist) {
				if (this.r == o.r) {
					return Integer.compare(this.c, o.c);
				}
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}

	static int[][] map; // 베이스 캠프, 편의점 위치 표시
	static boolean[][] f; // 해당 지역을 지나갈지 못지나갈지 표시
	static int N, M;
	static Point[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		f = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()) * -1;
			}
		}

		p = new Point[M + 1];

		int t = 0;
		que = new LinkedList<>();

		e: while (true) {
			t++;
			if (t >= 2) {
				gotoConv();
			}
			if (t <= M) {
				que.clear();
				v = new boolean[N][N];

				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken()) - 1;
				int c = Integer.parseInt(st.nextToken()) - 1;
				map[r][c] = t;

				que.offer(new Point(r, c, 0));
				v[r][c] = true;
				gotoBaseCamp(t);
			}


			for (int i = 1; i <= M; i++) {
				if (p[i] != null)
					continue e;
			}
			break;
		}
		System.out.println(t);

	}

	static Queue<Point> que;
	static PriorityQueue<Point> pq;
	static boolean[][] v;

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	static Queue<Point> tmp;

	private static void gotoConv() {
		tmp = new LinkedList<>();
		for (int i = 1; i <= M; i++) {
			que.clear();
			if (p[i] != null) {
				v = new boolean[N][N];
				v[p[i].r][p[i].c] = true;
				for (int d = 0; d < 4; d++) {
					int nr = p[i].r + dr[d];
					int nc = p[i].c + dc[d];
					if (!check(nr, nc) || f[nr][nc])
						continue;
					que.offer(new Point(nr, nc, nr, nc, i));
					v[nr][nc] = true;
				}
				bfs(i);
			}
		}

		while (!tmp.isEmpty()) {
			Point cur = tmp.poll();

			if (cur.pr == cur.r && cur.c == cur.pc) {
				p[cur.num] = null;
				f[cur.r][cur.c] = true;
				continue;
			}
			p[cur.num] = new Point(cur.pr, cur.pc);
		}
	}

	private static void bfs(int i) {
		while (!que.isEmpty()) {
			Point cur = que.poll();
			if (map[cur.r][cur.c] == i) {
				tmp.offer(new Point(cur.r, cur.c, cur.r, cur.c, i));
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || v[nr][nc] || f[nr][nc])
					continue;

				if (map[nr][nc] == i) {
					tmp.offer(new Point(cur.pr, cur.pc, nr, nc, i));
					return;
				} else {
					que.offer(new Point(cur.pr, cur.pc, nr, nc, i));
					v[nr][nc] = true;
				}
			}

		}

	}

	private static void gotoBaseCamp(int t) {
		pq = new PriorityQueue<>();
		// 베이스캠프 찾으면 행 작은지 큰지 찾기
		while (!que.isEmpty()) {
			Point cur = que.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || v[nr][nc] || f[nr][nc])
					continue;
				if (map[nr][nc] == -1) {
					pq.offer(new Point(nr, nc, cur.dist + 1));
				} else {
					que.offer(new Point(nr, nc, cur.dist + 1));
				}
				v[nr][nc] = true;
			}
		}
		Point now = pq.poll();
		p[t] = new Point(now.r, now.c);
		f[now.r][now.c] = true;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
