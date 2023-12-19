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
	static boolean[][] v;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		v = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		que = new LinkedList<>();
		max = Integer.MIN_VALUE;
		cnt = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 1 && !v[r][c]) {
					que.offer(new Point(r, c));
					v[r][c] = true;
					bfs(1);
					cnt++;
				}

			}
		}
		System.out.println(cnt);
		System.out.println(max == Integer.MIN_VALUE ? 0 : max);

	}

	static Queue<Point> que;
	static int max, cnt;
	static int[] dr = { 0, 1, 0, -1 };
	static int[] dc = { 1, 0, -1, 0 };

	private static void bfs(int cnt) {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || v[nr][nc] || map[nr][nc] == 0)
					continue;

				que.offer(new Point(nr, nc));
				v[nr][nc] = true;
				cnt++;

			}
		}
		max = Math.max(max, cnt);
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
