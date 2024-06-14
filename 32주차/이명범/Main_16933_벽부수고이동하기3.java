package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16933_벽부수고이동하기3 {

	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class CountPoint {
		Point point;
		int count;

		public CountPoint(int r, int c, int count) {
			this.point = new Point(r, c);
			this.count = count;
		}

		public int r() {
			return point.r;
		}

		public int c() {
			return point.c;
		}
	}

	static int N, M, K;
	static int[][] map;

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<CountPoint> q = new ArrayDeque<>();
		boolean[][][] visit = new boolean[N][M][K + 1];
		q.add(new CountPoint(0, 0, 0));
		visit[0][0][0] = true;

		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		int time = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int o = 0; o < size; o++) {
				CountPoint cur = q.poll();

				if (cur.r() == N - 1 && cur.c() == M - 1)
					return time;

				for (int i = 0; i < 4; i++) {
					int nr = cur.r() + dr[i];
					int nc = cur.c() + dc[i];

					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;

					int nCount = cur.count;
					if (map[nr][nc] == 1) {
						if (time % 2 == 0) {
							q.add(cur);
							continue;
						}
						if (nCount >= K)
							continue;
						nCount++;
					}
					if (visit[nr][nc][nCount])
						continue;

					q.add(new CountPoint(nr, nc, nCount));
					visit[nr][nc][nCount] = true;
				}
			}
			time++;
		}
		return -1;
	}
}
