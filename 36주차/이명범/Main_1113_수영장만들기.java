package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1113_수영장만들기 {

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

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 2][M + 2];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j - 1) - '0';
			}
		}
		int result = 0;
		for (int i = 1; i < 9; i++) {
			boolean[][] visit = new boolean[N + 2][M + 2];
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= M; k++) {
					if (visit[j][k])
						continue;
					if (map[j][k] != i)
						continue;

					int min = 9;
					List<Point> p = new ArrayList<>();

					Queue<Point> q = new ArrayDeque<>();
					q.add(new Point(j, k));
					visit[j][k] = true;

					while (!q.isEmpty()) {
						Point cur = q.poll();
						p.add(cur);

						for (int l = 0; l < 4; l++) {
							int nr = cur.r + dr[l];
							int nc = cur.c + dc[l];

							if (visit[nr][nc])
								continue;
							if (map[nr][nc] != i) {
								min = Math.min(min, map[nr][nc]);
								continue;
							}
							q.add(new Point(nr, nc));
							visit[nr][nc] = true;
						}
					}

					if (min > i) {
						for (Point po : p) {
							map[po.r][po.c] = min;
						}
						result += p.size() * (min - i);
					}

				}
			}
		}
		System.out.println(result);
	}

	private static boolean isArrayOutOfBounds(int r, int c) {
		return r < 1 || r > N || c < 1 || c > M;
	}
}
