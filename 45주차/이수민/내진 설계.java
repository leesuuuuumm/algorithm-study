import java.io.*;
import java.util.*;

public class Main {

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
	static int[][] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		v = new int[N][M];
		que = new LinkedList<>();

		int sr = 0;
		int sc = 0;
		for (int r = 0; r < N; r++) {
			String[] s = br.readLine().split("");
			for (int c = 0; c < M; c++) {
				if (s[c].equals("*")) {
					map[r][c] = 1;
					v[r][c] = 1;
				} else if (s[c].equals("#")) {
					map[r][c] = 2;
					v[r][c] = 2;
				} else if (s[c].equals("|")) {
					map[r][c] = -1;
					v[r][c] = -1;
				} else if (s[c].equals("@")) {
					sr = r;
					sc = c;

				}

			}
		}

		for (int d = 0; d < 4; d++) {
			int nr = sr;
			int nc = sc;

			for (int i = 0; i < 2; i++) {
				nr += dr[d];
				nc += dc[d];

				if (!check(nr, nc) || map[nr][nc] == -1)
					break;
				if (map[nr][nc] >= 1) {
					map[nr][nc]--;
					if (map[nr][nc] == 0)
						que.offer(new Point(nr, nc));
				}
			}

		}

		bfs();
		int b = 0;
		int bb = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (v[r][c] >= 1 && map[r][c] == 0) {
					bb++;
				} else if (v[r][c] >= 1 && map[r][c] > 0) {
					b++;
				}
			}
		}

		System.out.println(bb + " " + b);

	}

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };
	static Queue<Point> que;

	private static void bfs() {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || map[nr][nc] <= 0)
					continue;

				if (map[nr][nc] >= 1) {
					map[nr][nc]--;
					if (map[nr][nc] == 0) {
						que.offer(new Point(nr, nc));

					}
				}
			}

		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
