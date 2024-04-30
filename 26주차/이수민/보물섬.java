import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int r;
		int c;
		int cnt;

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static String[][] map;
	static int L, W;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new String[L][W];

		for (int r = 0; r < L; r++) {
			map[r] = br.readLine().split("");
		}
		max = 1;

		v = new boolean[L][W];
		que = new LinkedList<>();

		for (int r = 0; r < L; r++) {
			for (int c = 0; c < W; c++) {
				if (map[r][c].equals("L")) {
					v = new boolean[L][W];
					v[r][c] = true;
					que.offer(new Point(r, c, 0));
					bfs();

				}

			}
		}
		System.out.println(max);

	}

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };
	static boolean[][] v;
	static Queue<Point> que;

	private static void bfs() {
		int cnt = 0;
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || v[nr][nc])
					continue;

				if (map[nr][nc].equals("L")) {
					cnt = cur.cnt + 1;

					que.offer(new Point(nr, nc, cur.cnt + 1));
					v[nr][nc] = true;

				}

			}

		}
		max = Math.max(max, cnt);

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < L && nc >= 0 && nc < W;
	}
}
