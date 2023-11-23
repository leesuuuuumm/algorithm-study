import java.io.*;
import java.util.*;

public class Main {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int R, C, N;
	static String[][] map;

	static Queue<Point> que;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new String[R][C];
		que = new LinkedList<>();

		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().split("");

		}

		for (int t = 2; t <= N; t++) {
			if (t % 2 == 1) { // 폭탄 저장시켜서 터드리기!
				for (int r = 0; r < R; r++) {
					for (int c = 0; c < C; c++) {
						if (map[r][c].equals("O")) {
							que.offer(new Point(r, c));
						}
						map[r][c] = "O";

					}
				}
				bomb();
			}

		}

		if (N % 2 == 0) {
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					map[r][c] = "O";
				}
			}
		}
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				System.out.print(map[r][c]);
			}
			System.out.println();
		}

	}

	static int[] dr = { 0, 1, -1, 0 };
	static int[] dc = { 1, 0, 0, -1 };

	private static void bomb() {
		while (!que.isEmpty()) {
			Point cur = que.poll();
			map[cur.r][cur.c] = ".";

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (!check(nr, nc))
					continue;

				map[nr][nc] = ".";

			}

		}

	}

	private static boolean check(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
