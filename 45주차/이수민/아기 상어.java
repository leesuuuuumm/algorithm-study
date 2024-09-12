import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int t;

		public Point(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

		public int compareTo(Point o) {
			if (this.t == o.t) {
				if (this.r == o.r) {
					return Integer.compare(this.c, o.c);
				}
				return Integer.compare(this.r, o.r);
			}
			return Integer.compare(this.t, o.t);

		}
	}

	static int N;
	static int[][] map;
	static int sr, sc, sm, size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		sr = 0;
		sc = 0;
		sm = 0;
		size = 2;

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					sr = r;
					sc = c;
					map[r][c] = 0;
				}
			}
		}

		ans = 0;
		while (true) {
			v = new boolean[N][N];
			que = new PriorityQueue<>();

			que.offer(new Point(sr, sc, 0));
			v[sr][sc] = true;

			if (!bfs())
				break;
		}
		System.out.println(ans);

	}

	static PriorityQueue<Point> que;
	static boolean[][] v;
	static int ans;

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	private static boolean bfs() {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			if (map[cur.r][cur.c] < size && map[cur.r][cur.c] != 0) {
				ans += (cur.t);
				map[cur.r][cur.c] = 0;
				sm++;
				if (sm == size) {
					size++;
					sm = 0;
				}
				sr = cur.r;
				sc = cur.c;
				return true;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || map[nr][nc] > size || v[nr][nc])
					continue;

				if (map[nr][nc] <= size || map[nr][nc] == 0) {
					que.offer(new Point(nr, nc, cur.t + 1));
					v[nr][nc] = true;
				}
			}

		}

		return false;

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
