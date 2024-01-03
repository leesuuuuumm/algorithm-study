import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int r;
		int c;
		int addAir;

		public Point(int r, int c, int addAir) {
			this.r = r;
			this.c = c;
			this.addAir = addAir;
		}

	}

	static Queue<Point> que;
	static int[][] map;
	static boolean[][] v;
	static int R, C, T;
	static int uR, uC, dR; // 상,하단 청정기 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		que = new LinkedList<>();

		boolean flag = false;
		for (int r = 0; r < R; r++) {

			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == -1 && !flag) {
					uR = r;
					uC = c + 1;
					dR = r + 1;
					flag = true;
				}
			}
		}

		while (T-- > 0) {

			v = new boolean[R][C];
			// 미세먼지 늘리기
			badAirAdd();

			// 미세먼지 움직이기
			badAirMove();

		}
		int ans = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1)
					continue;

				ans += map[i][j];
			}
		}
		System.out.println(ans);

	}

	static int[] dr = { 0, -1, 0, 1 }; // 동북서남
	static int[] dc = { 1, 0, -1, 0 };

	static int[] ddr = { 0, 1, 0, -1 }; // 동 남 서 북

	private static void badAirAdd() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != 0 || map[r][c] != -1) {
					que.offer(new Point(r, c, map[r][c] / 5));
				}
			}
		}

		while (!que.isEmpty()) {
			Point cur = que.poll();

			int addCnt = 0;
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc) || map[nr][nc] == -1)
					continue;

				map[nr][nc] += cur.addAir;
				addCnt++;
			}
			map[cur.r][cur.c] -= (cur.addAir * addCnt);

		}

	}

	private static void badAirMove() {
		upMove();
		downMove();

	}

	private static void upMove() {
		int curVal = map[uR][uC];
		int nextVal = map[uR][uC];
		int cr = uR;
		int cc = uC;
		map[cr][cc] = 0;

		for (int d = 0; d < 4; d++) {

			if (d == 0) { // 동

				for (int i = 0; i < C - 2; i++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];

					nextVal = map[nr][nc];
					map[nr][nc] = curVal;
					curVal = nextVal;
					cr = nr;
					cc = nc;

				}

			} else if (d == 1) { // 북
				for (int i = cr; i > 0; i--) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];

					nextVal = map[nr][nc];
					map[nr][nc] = curVal;
					curVal = nextVal;
					cr = nr;
					cc = nc;

				}

			} else if (d == 2) { // 서
				for (int i = cc; i > 0; i--) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];

					nextVal = map[nr][nc];
					map[nr][nc] = curVal;
					curVal = nextVal;
					cr = nr;
					cc = nc;

				}

			} else if (d == 3) { // 남
				for (int i = 0; i < uR - 1; i++) {
					int nr = cr + dr[d];
					int nc = cc + dc[d];

					nextVal = map[nr][nc];
					map[nr][nc] = curVal;
					curVal = nextVal;
					cr = nr;
					cc = nc;

				}

			}

		}
	}

	private static void downMove() {
		int curVal = map[dR][uC];
		int nextVal = map[dR][uC];
		int cr = dR;
		int cc = uC;
		map[cr][cc] = 0;

		// 상단
		for (int d = 0; d < 4; d++) {

			if (d == 0) { // 동

				for (int i = 0; i < C - 2; i++) {
					int nr = cr + ddr[d];
					int nc = cc + dc[d];

					nextVal = map[nr][nc];
					map[nr][nc] = curVal;
					curVal = nextVal;
					cr = nr;
					cc = nc;

				}

			} else if (d == 1) { // 남
				for (int i = cr; i < R - 1; i++) {
					int nr = cr + ddr[d];
					int nc = cc + dc[d];

					nextVal = map[nr][nc];
					map[nr][nc] = curVal;
					curVal = nextVal;
					cr = nr;
					cc = nc;

				}

			} else if (d == 2) { // 서
				for (int i = cc; i > 0; i--) {
					int nr = cr + ddr[d];
					int nc = cc + dc[d];

					nextVal = map[nr][nc];
					map[nr][nc] = curVal;
					curVal = nextVal;
					cr = nr;
					cc = nc;

				}

			} else if (d == 3) { // 북

				for (int i = cr; i > dR + 1; i--) {

					int nr = cr + ddr[d];
					int nc = cc + dc[d];

					nextVal = map[nr][nc];
					map[nr][nc] = curVal;
					curVal = nextVal;
					cr = nr;
					cc = nc;

				}
			}

		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;

	}

	private static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("=================");

	}
}
