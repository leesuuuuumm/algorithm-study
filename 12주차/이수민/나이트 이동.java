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

	static int[][] map;
	static int[][] v;
	static int L;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			L = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int er = Integer.parseInt(st.nextToken());
			int ec = Integer.parseInt(st.nextToken());

			que = new LinkedList<>();
			v = new int[L][L];
			v[sr][sc] = 0;
			que.offer(new Point(sr, sc));
			answer = 0;

			bfs(sr, sc, er, ec);
			System.out.println(answer);

		}
	}

	static int[] dr = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int[] dc = { 1, 2, 2, 1, -1, -2, -2, -1 };

	static Queue<Point> que;
	static int answer;

	private static void bfs(int sr, int sc, int er, int ec) {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 8; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || v[nr][nc] != 0 || nr == sr && nc == sc)
					continue;
				if (nr == er && nc == ec) {
					answer = v[cur.r][cur.c] + 1;
					return;
				}

				v[nr][nc] = v[cur.r][cur.c] + 1;
				que.offer(new Point(nr, nc));

			}

		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < L && nc >= 0 && nc < L;
	}
}
