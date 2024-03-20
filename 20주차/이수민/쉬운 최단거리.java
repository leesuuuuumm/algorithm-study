import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;
		int dist;

		public Point(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] ans;
	static Queue<Point> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		ans = new int[N][M];
		que = new LinkedList<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 2) {
					que.offer(new Point(r, c, 0));
					ans[r][c] = 0;

				} else if (map[r][c] == 1) {
					ans[r][c] = Integer.MAX_VALUE;
				}
			}
		}

		bfs();

		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (ans[r][c] == Integer.MAX_VALUE) {
					sb.append("-1 ");
				} else {
					sb.append(ans[r][c]).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	static int[] dr = { 0, -1, 1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	private static void bfs() {
		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || map[nr][nc] == 0 || ans[nr][nc] <= cur.dist + 1)
					continue;

				que.offer(new Point(nr, nc, cur.dist + 1));
				ans[nr][nc] = cur.dist + 1;
			}

		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}

}
