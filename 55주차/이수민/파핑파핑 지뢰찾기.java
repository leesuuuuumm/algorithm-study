import java.util.*;
import java.io.*;

public class Solution {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int T, cnt;
	static char map[][];
	static int sMap[][];
	static int N;
	static int minenum;
	static int v[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			sMap = new int[N][N];
			v = new int[N][N];
			cnt = 0;
			for (int r = 0; r < N; r++) {
				String mp = br.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = mp.charAt(c);
					if (map[r][c] == '*') {
						sMap[r][c] = 9;
					}

				}
			}
			search();
			bfs();
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (sMap[r][c] != 9 && v[r][c] == 0)
						cnt++;
				}
			}

			System.out.println("#" + t + " " + cnt);

		}
	}

	static int dr[] = { 0, 0, 1, -1, 1, -1, 1, -1 };
	static int dc[] = { 1, -1, 0, 0, -1, 1, 1, -1 };

	private static void bfs() {
		Queue<Point> que = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (sMap[r][c] == 0 && v[r][c] == 0) {
					v[r][c] = 1;
					que.offer(new Point(r, c));
					cnt++;
					while (!que.isEmpty()) {
						minenum = 0;

						Point cur = que.poll();
						for (int d = 0; d < 8; d++) {
							int nr = cur.r + dr[d];
							int nc = cur.c + dc[d];
							if (!check(nr, nc)) {
								continue;
							}
							if (sMap[nr][nc] == 9) {
								minenum++;
							}

						}
						if (minenum > 0) {
							sMap[cur.r][cur.c] = 1;
						} else {
							for (int d = 0; d < 8; d++) {
								int nr = cur.r + dr[d];
								int nc = cur.c + dc[d];
								if (!check(nr, nc)) {
									continue;
								}
								if (v[nr][nc] == 0) {
									que.offer(new Point(nr, nc));
									v[nr][nc] = 1;
								}
							}
						}
					}
				}
			}
		}

	}

	private static void search() {

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == '*') {

					for (int d = 0; d < 8; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						if (!check(nr, nc)) {
							continue;
						}
						if (map[nr][nc] == '.') {

							sMap[nr][nc] += 1;
						}

					}

				}
			}
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
