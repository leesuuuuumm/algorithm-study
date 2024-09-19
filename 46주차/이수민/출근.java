import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int cnt;

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		public int compareTo(Point o) {
			if (o.cnt == this.cnt) {
				return Integer.compare(o.r, this.r);
			}

			return Integer.compare(this.cnt, o.cnt);
		}
	}

	static int R, C;
	static int[][] map;
	static PriorityQueue<Point> pq;
	static int N;
	static boolean[][] v;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		pq = new PriorityQueue<>();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		N = Integer.parseInt(br.readLine());

		dr = new int[N];
		dc = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			dr[i] = Integer.parseInt(st.nextToken());
			dc[i] = Integer.parseInt(st.nextToken());
		}
		v = new boolean[R][C];

		for (int c = 0; c < C; c++) {
			if (map[0][c] == 1) {
				pq.offer(new Point(0, c, 0));
				v[0][c] = true;
			}
		}
		ans = -1;

		bfs();
		System.out.println(ans);

	}

	static int[] dr;
	static int[] dc;

	private static void bfs() {
		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.r == R - 1) {
				ans = cur.cnt;
				return;
			}

			for (int d = 0; d < N; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || map[nr][nc] == 0 || v[nr][nc])
					continue;

				pq.offer(new Point(nr, nc, cur.cnt + 1));
				v[nr][nc] = true;

			}

		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
