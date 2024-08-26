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

	static int N, M, K;
	static int[][] map;
	static boolean[][] v;
	static Queue<Point> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		v = new boolean[N][N];
		que = new LinkedList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 0 && !v[r][c]) {
					que.offer(new Point(r, c));
					v[r][c] = true;

					int num = bfs();

					if (num % K != 0) {
						cnt += (num / K) + 1;
					} else {
						cnt += (num / K);

					}

				}
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!v[r][c] && map[r][c] == 0) {
					System.out.println("IMPOSSIBLE");
					return;
				}
			}
		}
		if (cnt >= 1 && cnt <= M) {
			System.out.println("POSSIBLE");
			System.out.println(M - cnt);
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}

	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static int bfs() {
		int cnt = 1;
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || v[nr][nc] || map[nr][nc] == 1)
					continue;
				v[nr][nc] = true;
				que.offer(new Point(nr, nc));

				cnt++;

			}

		}

		return cnt;
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}
