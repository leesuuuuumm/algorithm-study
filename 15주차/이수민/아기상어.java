import java.io.*;
import java.util.*;

public class Main {
	static class Point implements Comparable<Point> {
		int r;
		int c;
		int size;
		int sub;
		int cnt;

		public Point(int r, int c, int size, int sub) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.sub = sub;
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

	static int[][] map;
	static int N, time;
	static Point sk;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					sk = new Point(r, c, 2, 0);
					map[r][c] = 0;
				}
			}
		}

		time = 0;
		while (true) {
			if (!decideDir())
				break;
		}
		System.out.println(time);
	}

	static PriorityQueue<Point> pq;

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	private static boolean decideDir() {
		pq = new PriorityQueue<>();
		pq.offer(new Point(sk.r, sk.c, 0));
		boolean[][] v = new boolean[N][N];
		v[sk.r][sk.c] = true;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (map[cur.r][cur.c] > 0 && map[cur.r][cur.c] < sk.size) {
				time += cur.cnt;
				map[cur.r][cur.c] = 0;
				if (sk.sub + 1 == sk.size) {
					sk = new Point(cur.r, cur.c, sk.size + 1, 0);
				} else {
					sk = new Point(cur.r, cur.c, sk.size, sk.sub + 1);
				}
				return true;
			}
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || map[nr][nc] > sk.size || v[nr][nc])
					continue;

				pq.offer(new Point(nr, nc, cur.cnt + 1));
				v[nr][nc] = true;

			}

		}
		return false;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
