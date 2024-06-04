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
	static ArrayList<Point> hp; 
	static Point[] selc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		hp = new ArrayList<>();
		map = new int[N][N];
		selc = new Point[M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					hp.add(new Point(i, j));
				}
			}
		}

		min = Integer.MAX_VALUE;
		que = new LinkedList<>();
		nCr(0, 0);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);

	}

	static int min;
	static int[][] v;
	static Queue<Point> que;
	static boolean ck;

	private static void nCr(int cnt, int start) {
		if (cnt == M) {
			v = new int[N][N];
			bfs();
			return;
		}

		for (int i = start; i < hp.size(); i++) {
			selc[cnt] = hp.get(i);
			nCr(cnt + 1, i + 1);
		}

	}

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { -1, 0, 0, 1 };
	static int max;

	private static void bfs() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(v[i], -1);
		}
		for (int i = 0; i < selc.length; i++) {
			que.offer(new Point(selc[i].r, selc[i].c));
			v[selc[i].r][selc[i].c] = 0;
		}
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || map[nr][nc] == 1 || v[nr][nc] != -1)
					continue;

				v[nr][nc] = v[cur.r][cur.c] + 1;
				que.offer(new Point(nr, nc));

			}
		}
		max = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1)
					continue;

				if (map[r][c] == 0) {
					if (v[r][c] == -1)
						return;
					max = Math.max(max, v[r][c]);
				}

			}
		}
		min = Math.min(min, max);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
