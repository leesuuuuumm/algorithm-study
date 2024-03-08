package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_2665_미로만들기 {

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

	private final static int[] dr = {-1, 1, 0, 0};
	private final static int[] dc = {0, 0, -1, 1};

	static int N, result;
	static int[][] map;


	public static void main(String[] args) throws Exception {
		input();
		result = bfs();
		System.out.println(result);
	}

	private static int bfs() {
		PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparing(o -> o.count));
		boolean[][][] visit = new boolean[N][N][N * N];
		pq.add(new Point(0, 0, 0));
		visit[0][0][0] = true;

		while (!pq.isEmpty()) {
			Point cur = pq.poll();

			if (cur.r == N - 1 && cur.c == N - 1)
				return cur.count;

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;

				int ncount = map[nr][nc] == 0 ? cur.count + 1 : cur.count;

				if (visit[nr][nc][ncount])
					continue;

				pq.add(new Point(nr, nc, ncount));
				visit[nr][nc][ncount] = true;
			}
		}
		return -1;
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
	}
}
