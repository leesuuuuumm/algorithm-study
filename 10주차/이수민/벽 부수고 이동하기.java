import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;
		int dist;
		boolean flag;

		public Point(int r, int c, int dist, boolean flag) {
			this.r = r;
			this.c = c;
			this.dist = dist;
			this.flag = flag;
		}
	}

	static int[][] map;
	static int N, M, answer;
	static boolean[][][] v;
	static Queue<Point> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		answer = Integer.MAX_VALUE;
		v = new boolean[N][M][2];

		for (int r = 0; r < N; r++) {
			char[] ch = br.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				map[r][c] = ch[c] - '0';

			}
		}
		
		que = new LinkedList<>();

		que.offer(new Point(0, 0, 1, false));
		// [r][c][0]: 벽 안 뚫은 것, [r][c][1]: 벽 뚫은 것
		v[0][0][0] = true;
		v[0][0][1] = true;
		bfs();

		System.out.println(answer==Integer.MAX_VALUE?-1:answer);
	}

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	private static void bfs() {
		while (!que.isEmpty()) {
			Point cur = que.poll();
			if (cur.r == N - 1 && cur.c == M-1) {
				answer = Math.min(cur.dist, answer);
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc))
					continue;
				// v[r][c][0]: 벽 안 뚫은 것, v[r][c][1]: 벽 뚫은 것

				if (map[nr][nc] == 1 && !cur.flag && !v[nr][nc][1]) {
					v[nr][nc][1] = true;
					que.offer(new Point(nr, nc, cur.dist + 1, true));

				} else if (map[nr][nc] == 0) {

					if (cur.flag && !v[nr][nc][1]) {
						que.offer(new Point(nr, nc, cur.dist + 1, true));
						v[nr][nc][1] = true;
					} else if (!cur.flag && !v[nr][nc][0]) {
						v[nr][nc][0] = true;
						que.offer(new Point(nr, nc, cur.dist + 1, false));

					}
				}

			}
		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
