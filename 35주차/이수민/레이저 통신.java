import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;
		int cnt;
		int dir;

		public Point(int r, int c, int cnt, int dir) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.dir = dir;
		}
	}

	static int H, W;
	static char[][] map;
	static int sr, sc, er, ec;
	static int[][][] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new char[H][W];
		sr = 0;
		sc = 0;
		er = 0;
		ec = 0;

		for (int i = 0; i < H; i++) {
			map[i] = br.readLine().toCharArray();
			for (int c = 0; c < W; c++) {
				if (map[i][c] == 'C') {
					if (sr == 0 && sc == 0) {
						sr = i;
						sc = c;
					} else {
						er = i;
						ec = c;
					}
				}
			}
		}
		v = new int[H][W][4];

		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				for (int d = 0; d < 4; d++) {
					v[i][j][d] = Integer.MAX_VALUE;
				}
			}
		}
		min = Integer.MAX_VALUE;
		que = new LinkedList<>();
		for (int d = 0; d < 4; d++) {
			que.offer(new Point(sr, sc, 0, d));
		}
		bfs();

		for (int d = 0; d < 4; d++) {
			min = Math.min(v[er][ec][d], min);
		}
		System.out.println(min);

	}

	static int[] dr = { 0, -1, 0, 1 };
	static int[] dc = { 1, 0, -1, 0 };
	static int min;
	static Queue<Point> que;

	private static void bfs() {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (nr == er && nc == ec) {
					if (cur.dir == d && v[nr][nc][d] > cur.cnt) {
						que.offer(new Point(nr, nc, cur.cnt, d));
						v[nr][nc][d] = cur.cnt;
					} else if (cur.dir != d && v[nr][nc][d] > cur.cnt + 1) {
						que.offer(new Point(nr, nc, cur.cnt + 1, d));
						v[nr][nc][d] = cur.cnt + 1;
					}
					continue;

				}

				if (!check(nr, nc) || map[nr][nc] == '*') {
					continue;
				}

				if (cur.dir == d && v[nr][nc][d] > cur.cnt) {
					que.offer(new Point(nr, nc, cur.cnt, d));
					v[nr][nc][d] = cur.cnt;
				} else if (cur.dir != d && v[nr][nc][d] > cur.cnt + 1) {
					que.offer(new Point(nr, nc, cur.cnt + 1, d));
					v[nr][nc][d] = cur.cnt + 1;
				}

			}

		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < H && nc >= 0 && nc < W;
	}
}
