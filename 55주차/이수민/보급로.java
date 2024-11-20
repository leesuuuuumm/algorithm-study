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

	static int map[][];
	static int dist[][];
	static int dr[] = { 1, -1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static int N, T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			dist = new int[N][N];
			for (int r = 0; r < N; r++) {
				String st = br.readLine();
				for (int c = 0; c < N; c++) {
					map[r][c] = st.charAt(c) - '0';
					dist[r][c] = Integer.MAX_VALUE;
				}
			}
			dist[0][0] = 0;

			bfs();
			System.out.println("#" + t + " " + dist[N - 1][N - 1]);
		}
	}

	static void bfs() {
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(0,0));

		while (!que.isEmpty()) {
			Point cur = que.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (!check(nr, nc)) {
					continue;
				}
				int temp = dist[cur.r][cur.c] + map[nr][nc];
				if (temp >= 0 && dist[nr][nc] > temp) {
					dist[nr][nc] = temp;
					que.offer(new Point(nr,nc));
				}
			}
		}

	}

	static boolean check(int nr, int nc) {

		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
