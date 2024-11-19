package algorithm.study;

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

	static int map[][], che[][], v[][];
	static int T, N;
	static int dr[] = { 0, 0, 1, -1 };
	static int dc[] = { 1, -1, 0, 0 };

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			v = new int[N][N];
			int k = 0;
			int m = map[0][0];
			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] > m) {
						m = map[r][c];
					}
					if (map[r][c] == m) {
						k++;
					}
				}
			}
			if (k == N * N) {
				System.out.println("#" + t + " " + 1);
			} else {
				int max = Integer.MIN_VALUE;
				Queue<Point> que = new LinkedList<>();
				a: while (true) {
					che = new int[N][N];
					v = new int[N][N];
					if (m == 0) {
						break a;
					}
					int cnt = 0;
					for (int r = 0; r < N; r++) {
						for (int c = 0; c < N; c++) {

							if (map[r][c] > m) {
								che[r][c] = 1;
							}
						}
					}
					for (int r = 0; r < N; r++) {
						for (int c = 0; c < N; c++) {
							if (che[r][c] == 1 && v[r][c] == 0) {
								que.offer(new Point(r,c));
								cnt++;
								while (!que.isEmpty()) {
									Point cur = que.poll();

									for (int d = 0; d < 4; d++) {
										int nr = cur.r + dr[d];
										int nc = cur.c + dc[d];
										if (!check(nr, nc)) {
											continue;
										}
										if (che[nr][nc] == 1 && v[nr][nc] == 0) {
											v[nr][nc] = 1;
											que.offer(new Point(nr,nc));
										}
									}
								}
							}
						}
					}
					max = Math.max(max, cnt);

					m--;
				}

				System.out.println("#" + t + " " + max);
			}
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
