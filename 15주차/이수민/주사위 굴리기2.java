import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;
		int d;

		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[] dice;
	static int[][] map;
	static int N, M, K;

	static Point pd;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		answer = 0;

		dice = new int[] { 2, 4, 1, 3, 5, 6 };
		que = new LinkedList<>();

		pd = new Point(0, 0, 0);
		while (K-- > 0) {
			move();
		}

		System.out.println(answer);

	}

	private static void move() {
		int d = pd.d;
		int r = pd.r;
		int c = pd.c;

		int nr = r + dr[d];
		int nc = c + dc[d];

		if (!check(nr, nc)) {
			if (d == 0 || d == 2) {
				d += 1;

			} else if (d == 1 || d == 3) {
				d -= 1;
			}
			nr = r + dr[d];
			nc = c + dc[d];
		}
		rollTheDice(d);
		getScore(nr, nc);
		decideTheDirection(nr, nc, d);

	}

	private static void decideTheDirection(int r, int c, int d) {
		if (dice[5] > map[r][c]) { // 90도 회전
			if (d == 0) {
				d = 2;
			} else if (d == 1) {
				d = 3;
			} else if (d == 2) {
				d = 1;
			} else if (d == 3) {
				d = 0;
			}
		} else if (dice[5] < map[r][c]) {
			if (d == 0) {
				d = 3;
			} else if (d == 1) {
				d = 2;
			} else if (d == 2) {
				d = 0;
			} else if (d == 3) {
				d = 1;
			}
		}
		pd = new Point(r, c, d);
	}

	static Queue<Point> que;

	private static void getScore(int r, int c) {
		que.offer(new Point(r, c));
		boolean[][] v = new boolean[N][M];
		v[r][c] = true;
		int C = 1;

		while (!que.isEmpty()) {
			Point cur = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc))
					continue;

				if (map[r][c] == map[nr][nc] && !v[nr][nc]) {
					C++;
					que.offer(new Point(nr, nc));
					v[nr][nc] = true;
				}
			}
		}
		answer += (C * map[r][c]);
	}

	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };

	private static void rollTheDice(int d) {

		int tmp = dice[5];
		if (d == 0) { // 동
			dice[5] = dice[3];
			dice[3] = dice[2];
			dice[2] = dice[1];
			dice[1] = tmp;

		} else if (d == 1) { // 서
			dice[5] = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = tmp;

		} else if (d == 2) { // 남
			dice[5] = dice[4];
			dice[4] = dice[2];
			dice[2] = dice[0];
			dice[0] = tmp;

		} else if (d == 3) { // 북
			dice[5] = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[4];
			dice[4] = tmp;
		}
	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < M;
	}
}
