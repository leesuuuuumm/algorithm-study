package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14442_벽부수고이동하기2 {

	static class Point {
		int r;
		int c;
		int count;

		public Point(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
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

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	private static int bfs() {
		Queue<Point> q = new ArrayDeque<>();
		boolean[][][] visit = new boolean[N][M][K + 1];
		q.add(new Point(0, 0, 0));
		visit[0][0][0] = true;

		int result = 1;
		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Point cur = q.poll();

				if (cur.r == N - 1 && cur.c == M - 1)
					return result;

				for (int dir = 0; dir < 4; dir++) {
					int nr = cur.r + dr[dir];
					int nc = cur.c + dc[dir];

					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;

					if (map[nr][nc] == 0) {
						if (visit[nr][nc][cur.count])
							continue;

						q.add(new Point(nr, nc, cur.count));
						visit[nr][nc][cur.count] = true;
					} else {
						if (cur.count == K)
							continue;
						if (visit[nr][nc][cur.count + 1])
							continue;

						q.add(new Point(nr, nc, cur.count + 1));
						visit[nr][nc][cur.count + 1] = true;
					}
				}
			}
			result++;
		}
		return -1;
	}
}
